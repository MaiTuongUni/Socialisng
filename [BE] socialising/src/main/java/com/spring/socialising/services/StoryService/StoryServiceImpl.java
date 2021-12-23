package com.spring.socialising.services.StoryService;

import com.spring.socialising.components.CloudinaryService;
import com.spring.socialising.entities.Story;
import com.spring.socialising.entities.User;
import com.spring.socialising.entities.block.FriendApprove;
import com.spring.socialising.entities.block.ImageStory;
import com.spring.socialising.entities.response.ResponseData;
import com.spring.socialising.repositories.StoryRepository.StoryRepository;
import com.spring.socialising.repositories.UserRepository.UserRepository;
import com.spring.socialising.securities.JwtUserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Service
@Transactional
public class StoryServiceImpl implements StoryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public ResponseEntity<ResponseData> createStory(MultipartFile multipartFile) {
        if(!StringUtils.isEmpty(multipartFile.getName())){
            if(multipartFile.getContentType().substring(0,5).equals("image")){
                JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = userRepository.findByPhoneNumber(userDetails.getUsername());

                Story story = storyRepository.findStoriesByUserId(user.getId());
                if(story == null){
                    story = new Story();
                    story.setUserAvatar(user.getUrlImage());
                    story.setUserName(user.getFullName());
                    story.setUserId(user.getId());
                }

                Map uploadResult = cloudinaryService.uploadImageStory(multipartFile);
                ImageStory imageStory = new ImageStory();
                imageStory.setCreateTime(LocalDateTime.now());
                imageStory.setUrl_public_id(uploadResult.get("public_id").toString());
                imageStory.setUrl(uploadResult.get("url").toString());

                story.addImage(imageStory);
                storyRepository.save(story);

                return new ResponseEntity<>(ResponseData.builder()
                        .success(true)
                        .message("Story created")
                        .data(story)
                        .build(), OK);
            }
            else
            {
                return new ResponseEntity<>(ResponseData.builder()
                        .success(false)
                        .message("The file did not a image file")
                        .data(null)
                        .build(), BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message("The request has file is empty")
                    .data(null)
                    .build(), BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseData> deteleStory() {
        JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByPhoneNumber(userDetails.getUsername());

        Story story = storyRepository.findStoriesByUserId(user.getId());

        if(story == null){
            return new ResponseEntity<>(ResponseData.builder()
                    .success(true)
                    .message("You don't have any story")
                    .data(null)
                    .build(), OK);
        }

        return new ResponseEntity<>(ResponseData.builder()
                .success(true)
                .message("Story deleted")
                .data(null)
                .build(), OK);
    }

    @Override
    public ResponseEntity<ResponseData> deleteAImageStory(ImageStory imageStory) {
        JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByPhoneNumber(userDetails.getUsername());

        Story story = storyRepository.findStoriesByUserId(user.getId());

        if(story==null){
            return new ResponseEntity<>(ResponseData.builder()
                    .success(false)
                    .message("You don't have any story")
                    .data(null)
                    .build(), BAD_REQUEST);
        }

        cloudinaryService.deleteImageStory(imageStory.getUrl_public_id());

        story.removeImage(imageStory);

        if(story.getImages().size() ==0){
            storyRepository.delete(story);
        }else {
            storyRepository.save(story);
        }

        return new ResponseEntity<>(ResponseData.builder()
                .success(true)
                .message("Story deteled image")
                .data(story)
                .build(), OK);
    }

    @Override
    public ResponseEntity<ResponseData> getMyStory() {
        JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByPhoneNumber(userDetails.getUsername());

        Story story = storyRepository.findStoriesByUserId(user.getId());

        return new ResponseEntity<>(ResponseData.builder()
                .success(true)
                .message("Story")
                .data(story)
                .build(), OK);
    }

    @Override
    public ResponseEntity<ResponseData> getListStoryForFriend() {
        JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByPhoneNumber(userDetails.getUsername());

        List<Story> stories = new ArrayList<>();
        for (FriendApprove item:user.getFriend()) {
            Story story = storyRepository.findStoriesByUserId(item.getUser_id());
            if(story != null){
                stories.add(story);
            }
        }
        return new ResponseEntity<>(ResponseData.builder()
                .success(true)
                .message("Story list by friend")
                .data(stories)
                .build(), OK);
    }
}
