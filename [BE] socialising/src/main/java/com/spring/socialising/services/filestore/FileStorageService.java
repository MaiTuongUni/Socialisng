package com.spring.socialising.services.filestore;

import com.spring.socialising.dtos.FileStoreResult;
import org.springframework.core.io.Resource;

import java.io.File;

/**
 * Created by: IntelliJ IDEA
 * User      : thangpx
 * Date      : 3/31/21
 * Time      : 13:05
 * Filename  : FileStorageService
 */
public interface FileStorageService {
    FileStoreResult storeFile(File file, String subFolder);

    Resource loadFile(String location);
}
