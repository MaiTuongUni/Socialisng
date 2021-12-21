package com.spring.socialising.services.FriendService;

import com.spring.socialising.entities.User;
import com.spring.socialising.entities.block.FriendApprove;
import com.spring.socialising.entities.block.FriendRequest;
import com.spring.socialising.entities.response.DataTemplatePaging;
import com.spring.socialising.entities.response.ResponseData;
import com.spring.socialising.repositories.UserRepository.UserRepository;
import com.spring.socialising.securities.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public DataTemplatePaging findUserByNickNameNear(String nickname, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nick_name").descending());

        Page<User> users = userRepository.findUserByNickNameNear(nickname, pageable);

        DataTemplatePaging dataTemplatePaging = new DataTemplatePaging();
        dataTemplatePaging.setPage(page);
        dataTemplatePaging.setSize(size);
        dataTemplatePaging.setTotal_page(users.getTotalPages());
        dataTemplatePaging.setTotal_count(users.getTotalElements());
        dataTemplatePaging.setData(users.getContent());
        return dataTemplatePaging;
    }

    @Override
    public ResponseEntity<ResponseData> sendFriendRequest(FriendRequest friendRequest) {
        JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByPhoneNumber(userDetails.getUsername());

        Optional<User> userRequest = userRepository.findById(friendRequest.getUser_id());

        if (!userRequest.isPresent()) {
            return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message("invalid user")
                    .data(null)
                    .build(), BAD_REQUEST);
        }

        friendRequest.setTime_request(LocalDateTime.now());
        user.addFriendRequest(friendRequest);

        userRepository.save(user);

        //Socket

        return new ResponseEntity<>(ResponseData.builder()
                .success(true)
                .message("frient request sent")
                .data(null)
                .build(), OK);
    }

    @Override
    public ResponseEntity<ResponseData> approveFriendRequest(FriendApprove friendApprove) {
        JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByPhoneNumber(userDetails.getUsername());

        Optional<User> userRequest = userRepository.findById(friendApprove.getUser_id());

        if (!userRequest.isPresent()) {
            return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message("invalid user")
                    .data(null)
                    .build(), BAD_REQUEST);
        }

        user.addFriend(friendApprove);
        user.removeFriendRequest(friendApprove);

        userRepository.save(user);
        return new ResponseEntity<>(ResponseData.builder()
                .success(true)
                .message("accepted friend")
                .data(null)
                .build(), OK);
    }

    @Override
    public ResponseEntity<ResponseData> rejectFriendRequest(FriendApprove friendApprove) {
        JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByPhoneNumber(userDetails.getUsername());

        Optional<User> userRequest = userRepository.findById(friendApprove.getUser_id());

        if (!userRequest.isPresent()) {
            return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message("invalid user")
                    .data(null)
                    .build(), BAD_REQUEST);
        }

        user.removeFriendRequest(friendApprove);

        userRepository.save(user);
        return new ResponseEntity<>(ResponseData.builder()
                .success(true)
                .message("rejected friend")
                .data(null)
                .build(), OK);
    }

    @Override
    public ResponseEntity<ResponseData> getFriendList() {
        JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByPhoneNumber(userDetails.getUsername());

        List<User> friends = new ArrayList<>();

        for (FriendApprove item: user.getFriend()) {
            User userFound = userRepository.findById(item.getUser_id()).get();
            friends.add(userFound);
        }

        return new ResponseEntity<>(ResponseData.builder()
                .success(true)
                .message("friend list")
                .data(friends)
                .build(), OK);
    }

    @Override
    public ResponseEntity<ResponseData> getFriendRequestList() {
        JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByPhoneNumber(userDetails.getUsername());

        List<User> friends = new ArrayList<>();

        for (FriendRequest item: user.getFriend_request()) {
            User userFound = userRepository.findById(item.getUser_id()).get();
            friends.add(userFound);
        }

        return new ResponseEntity<>(ResponseData.builder()
                .success(true)
                .message("friend request list")
                .data(friends)
                .build(), OK);
    }
}
