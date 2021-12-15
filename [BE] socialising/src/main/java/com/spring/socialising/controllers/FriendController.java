package com.spring.socialising.controllers;

import com.spring.socialising.entities.response.ResponseData;
import com.spring.socialising.services.FriendService.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/rest/user")
public class FriendController {
    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/find-by-nickname/near")
    public ResponseEntity<ResponseData> findUserByNickNamePaging(@RequestParam int page, @RequestParam int size, @RequestParam String nickname) {
        return new ResponseEntity<>(ResponseData.builder()
                .success(true)
                .message("List user by nickname")
                .data(friendService.findUserByNickNameNear(nickname,page,size))
                .build(), OK);
    }
}
