package com.spring.socialising.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by: IntelliJ IDEA
 * User      : thangpx
 * Date      : 3/31/21
 * Time      : 09:53
 * Filename  : FileStorageProperties
 */
@Getter
@Setter
@Configuration
public class FileStorageProperties {

    @Value("${file.download_dir}")
    private String downloadDir;


    @Value("${file.upload_dir}")
    private String uploadDir;
}

