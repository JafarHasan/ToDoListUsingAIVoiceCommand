package com.AccioJob.VoiceToDoListWithAi.Service;

import com.AccioJob.VoiceToDoListWithAi.Models.Task;
import com.AccioJob.VoiceToDoListWithAi.Models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;
    public void sendTaskNotification(Task task, Users user, boolean isUpdate){
        if(user!=null){
            String action=isUpdate?"updated":"created";
            String emailMessage=String.format("Task %s: %s\n Urgency %s\n Due:%s",action,task.getTask(),
                    action,task.getUrgency(),task.getDateTime());
            String subject="Task"+action.substring(0,2).toUpperCase()+action.substring(2).toUpperCase();
            emailService.sendTaskNotification(user.getEmail(),subject,emailMessage);

            //if user has a contact no use sms service
            //smsService.();
        }
    }

}
