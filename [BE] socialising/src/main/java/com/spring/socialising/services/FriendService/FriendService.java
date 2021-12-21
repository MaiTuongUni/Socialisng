package com.spring.socialising.services.FriendService;
import com.spring.socialising.entities.block.FriendApprove;
import com.spring.socialising.entities.block.FriendRequest;
import com.spring.socialising.entities.response.DataTemplatePaging;
import com.spring.socialising.entities.response.ResponseData;
import org.springframework.http.ResponseEntity;

public interface FriendService {
    DataTemplatePaging findUserByNickNameNear(String nickname, int page, int size);

    ResponseEntity<ResponseData> sendFriendRequest(FriendRequest friendRequest);

    ResponseEntity<ResponseData> approveFriendRequest(FriendApprove friendApprove);

    ResponseEntity<ResponseData> rejectFriendRequest(FriendApprove friendApprove);

    ResponseEntity<ResponseData> getFriendList();

    ResponseEntity<ResponseData> getFriendRequestList();
}
