package com.spring.socialising.components;

import com.spring.socialising.entities.response.ResponseData;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.util.MultiValueMap;

@Component
public class SmsService {
    @Value(value = "${twilio.account-sid}")
    private String ACCOUNT_SID;

    @Value(value = "${twilio.account-password}")
    private String AUTH_TOKEN ;

    @Value(value = "${twilio.account-from-number}")
    private String FROM_NUMBER;

    @Autowired
    private SimpMessagingTemplate webSocket;

    private final String  TOPIC_DESTINATION = "/lesson/sms";

    public boolean sendMessageOTP(String phone_number){
        try{
            this.send(phone_number);
//            webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent!: "+phone_number);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public void send(String phone_number) throws ParseException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);


        int min = 100000;
        int max = 999999;
        int number=(int)(Math.random()*(max-min+1)+min);


        String msg ="Mã OTP của bạn là: "+number;


        Message message = Message.creator(new PhoneNumber(phone_number), new PhoneNumber(FROM_NUMBER), msg)
                .create();
    }

    private String getTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }
}