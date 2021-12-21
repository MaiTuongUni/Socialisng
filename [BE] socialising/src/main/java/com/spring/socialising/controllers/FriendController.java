package com.spring.socialising.controllers;

import com.spring.socialising.entities.block.FriendApprove;
import com.spring.socialising.entities.block.FriendRequest;
import com.spring.socialising.entities.response.ResponseData;
import com.spring.socialising.services.FriendService.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get-friend-list")
    public ResponseEntity<ResponseData> getFriendList(){
        return friendService.getFriendList();
    }

    @GetMapping("/get-friend-request-list")
    public ResponseEntity<ResponseData> getFriendRequestList(){
        return friendService.getFriendRequestList();
    }

    @PostMapping("/send-friend-request")
    public ResponseEntity<ResponseData> sendFriendRequest(@RequestBody FriendRequest friendRequest){
        return friendService.sendFriendRequest(friendRequest);
    }

    @PostMapping("/approve-friend-request")
    public ResponseEntity<ResponseData> approveFriendRequest(@RequestBody FriendApprove friendApprove){
        return friendService.approveFriendRequest(friendApprove);
    }

    @PostMapping("/reject-friend-request")
    public ResponseEntity<ResponseData> rejectFriendRequest(@RequestBody FriendApprove friendApprove){
        return friendService.rejectFriendRequest(friendApprove);
    }


}
