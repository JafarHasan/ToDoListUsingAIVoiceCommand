package com.AccioJob.VoiceToDoListWithAi.util;

import com.AccioJob.VoiceToDoListWithAi.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;

public class UserHelper {

    @Autowired
    MyAnalyzer myAnalyzer; //using this library to check email and password
    public boolean isValidUser(String email, String password, Users user){
        String processedEmail=myAnalyzer.stem(email);
       // if(user.getEmail().toLowerCase().trim().equals(email) && user.getPassword().toLowerCase().trim().equals(password)){
        if(processedEmail.equals(email) &&  user.getPassword().equals(password)){
            return true;

        }
        return  false;
    }
}
