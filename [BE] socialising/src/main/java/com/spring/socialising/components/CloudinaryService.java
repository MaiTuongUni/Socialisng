package com.spring.socialising.components;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Component
public class CloudinaryService {
    private final Cloudinary cloudinary ;

    public CloudinaryService() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dow0lu50x",
                "api_key", "821191592441416",
                "api_secret", "hStgEbItYDh5WtDUUBSF6qqCa0o",
                "secure", true));
    }

    public Map uploadImageProfile(MultipartFile file){
        try{
            return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder","image_profile"));
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    public Map deleteImageProfile(String public_id){
        try{
            return cloudinary.uploader().destroy(public_id, ObjectUtils.asMap("folder","image_profile"));
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    public Map uploadImageStory(MultipartFile file){
        try{
            return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder","image_story"));
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    public Map deleteImageStory(String public_id){
        try{
            return cloudinary.uploader().destroy(public_id, ObjectUtils.asMap("folder","image_story"));
        }
        catch(Exception exception)
        {
            return null;
        }
    }
}
