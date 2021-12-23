package com.spring.socialising.controllers;

import com.spring.socialising.entities.block.ImageStory;
import com.spring.socialising.entities.response.ResponseData;
import com.spring.socialising.services.StoryService.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rest/user")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @PostMapping("/create-new-story")
    public ResponseEntity<ResponseData> createStory(MultipartFile story_image){
        return storyService.createStory(story_image);
    }

    @GetMapping("/get-my-story")
    public  ResponseEntity<ResponseData> getMyStory(){
        return  storyService.getMyStory();
    }

    @PostMapping("/delete-a-image")
    public ResponseEntity<ResponseData> deleteAImage(@RequestBody ImageStory imageStory){
        return  storyService.deleteAImageStory(imageStory);
    }

    @PostMapping("/delete-story")
    public ResponseEntity<ResponseData> deleteStory(){
        return  storyService.deteleStory();
    }

    @GetMapping("/list-story")
    public ResponseEntity<ResponseData> getListStory(){
        return storyService.getListStoryForFriend();
    }
}
