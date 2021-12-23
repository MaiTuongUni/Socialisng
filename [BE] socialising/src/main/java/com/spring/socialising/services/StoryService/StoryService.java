package com.spring.socialising.services.StoryService;

import com.spring.socialising.entities.block.ImageStory;
import com.spring.socialising.entities.response.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface StoryService {
   ResponseEntity<ResponseData> createStory(MultipartFile multipartFile);
   ResponseEntity<ResponseData> deteleStory();
   ResponseEntity<ResponseData> deleteAImageStory(ImageStory imageStory);

   ResponseEntity<ResponseData> getMyStory();

   ResponseEntity<ResponseData> getListStoryForFriend();
}
