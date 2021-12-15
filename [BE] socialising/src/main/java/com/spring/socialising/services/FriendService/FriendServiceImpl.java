package com.spring.socialising.services.FriendService;

import com.spring.socialising.entities.User;
import com.spring.socialising.entities.response.DataTemplatePaging;
import com.spring.socialising.entities.response.ResponseData;
import com.spring.socialising.repositories.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public DataTemplatePaging findUserByNickNameNear(String nickname, int page, int size) {
        Pageable pageable =  PageRequest.of(page,size, Sort.by("nick_name").descending());

        Page<User> users = userRepository.findUserByNickNameNear(nickname, pageable );

        DataTemplatePaging dataTemplatePaging = new DataTemplatePaging();
        dataTemplatePaging.setPage(page);
        dataTemplatePaging.setSize(size);
        dataTemplatePaging.setTotal_page(users.getTotalPages());
        dataTemplatePaging.setTotal_count(users.getTotalElements());
        dataTemplatePaging.setData(users.getContent());
        return  dataTemplatePaging;
    }
}
