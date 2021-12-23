package com.spring.socialising.entities;

import com.spring.socialising.entities.block.ImageStory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "stories")
public class Story {
    @Id
    private String id;

    private String userId;

    private String userName;

    private String userAvatar;

    List<ImageStory> images = new ArrayList<>();

    public void addImage(ImageStory imageStory){
        this.images.add(imageStory);
    }

    public void removeImage(ImageStory imageStory){
        for (ImageStory items:images) {
            if(items.getUrl_public_id().equals(imageStory.getUrl_public_id())){
                this.images.remove(items);
            }
        }
    }
}
