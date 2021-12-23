package com.spring.socialising.components;

import com.spring.socialising.entities.block.UserActiveInfo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {

    private static UserStorage intance;

    private HashMap<String,UserActiveInfo> users;

    private UserStorage(){
        users = new HashMap<String,UserActiveInfo>();
    }

    public static synchronized UserStorage getInstance(){
        if(intance==null){
            intance = new UserStorage();
        }
        return intance;
    }

    public HashMap<String, UserActiveInfo> getUserActiveInfo(){
        return users;
    }

    public boolean updateUserActive(String id, String socketAddress){
        if(users.containsKey(id)) {
           users.get(id).setActive(true);
           users.get(id).setTime(LocalDateTime.now());
        }
        else {
            UserActiveInfo userActiveInfo = new UserActiveInfo();
            userActiveInfo.setActive(true);
            userActiveInfo.setSocketAddress(socketAddress);
            userActiveInfo.setTime(LocalDateTime.now());
            userActiveInfo.setUserId(id);
            this.users.put(id,userActiveInfo);
        }
        return true;
    }

    public UserActiveInfo getActiveInfoByKey(String id){
        return users.get(id);

    }

    public void reCheckUserActive() {
        if(this.users == null) return;

        for(Map.Entry<String, UserActiveInfo> entry : users.entrySet()) {
            UserActiveInfo userActiveInfo = entry.getValue();
            Duration duration = Duration.between(userActiveInfo.getTime(),LocalDateTime.now());
            if(duration.getSeconds() >= 300){
                userActiveInfo.setActive(false);
            }
        }
    }
}