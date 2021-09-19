package com.socialising.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static InputStream getInputStream(String imageName) {
        File file = new File("C:/home/shoestore/"+imageName+"");
        try {
            InputStream inputStream = new FileInputStream(file);
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
