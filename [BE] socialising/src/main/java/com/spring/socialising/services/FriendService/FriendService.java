package com.spring.socialising.services.FriendService;

import com.spring.socialising.entities.User;
import com.spring.socialising.entities.response.DataTemplatePaging;
import com.spring.socialising.entities.response.ResponseData;

import java.util.List;

public interface FriendService {
    DataTemplatePaging findUserByNickNameNear(String nickname, int page, int size);
}
