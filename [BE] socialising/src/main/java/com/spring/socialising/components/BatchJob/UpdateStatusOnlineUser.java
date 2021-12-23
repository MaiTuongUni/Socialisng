package com.spring.socialising.components.BatchJob;

import com.spring.socialising.components.UserStorage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateStatusOnlineUser {
    @Scheduled(cron = "0 */3 * ? * *")
    public void updateStatusOnlineForUser() {
        UserStorage.getInstance().reCheckUserActive();
    }

    @Scheduled(cron = "0 */30 * ? * *")
    public void deleteStoryOver24h(){
        //Something
    }
}
