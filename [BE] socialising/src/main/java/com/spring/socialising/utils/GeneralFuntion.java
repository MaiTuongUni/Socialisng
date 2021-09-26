package com.spring.socialising.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class GeneralFuntion {
    public static boolean isTimeOutOTP(LocalDateTime start, long timeOut){
        Duration duration = Duration.between(start, LocalDateTime.now());
        if(duration.getSeconds() > timeOut){
            return false;
        }
        return true;
    }
}
