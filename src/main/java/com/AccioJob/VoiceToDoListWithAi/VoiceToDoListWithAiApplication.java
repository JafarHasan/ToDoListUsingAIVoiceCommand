package com.AccioJob.VoiceToDoListWithAi;

import com.AccioJob.VoiceToDoListWithAi.util.MyAnalyzer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;

@SpringBootApplication
public class VoiceToDoListWithAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoiceToDoListWithAiApplication.class, args);
//		HashSet<String> strings=new HashSet<>();
//			MyAnalyzer myAnalyzer = new MyAnalyzer(strings);
//			String response = myAnalyzer.stem("Nike shoes for mens");
//			System.out.println(response);
		}
	}




/*Factory DESIGNED PATTERN
interface SendNotification{
	public void send();
}
class SendWhatsappNotification implements SendNotification{

	@Override
	public void send() {
		System.out.println("Send through Whatsapp");
	}
}
class SendEmailNotification implements  SendNotification{
	@Override
	public void send() {
		System.out.println("Send through Email");
	}
}
class NotificationFactory{
	public SendNotification getInstance(String type){
		if(type.equals("whatsapp")) {
			SendNotification obj = new SendWhatsappNotification();
			return obj;
		}
		else if(type.equals("Email")){
			SendNotification obj = new SendEmailNotification();
			return obj;
		}
		return  null;
	}
}
public class VoiceToDoListWithAiApplication {
	public static void main(String[] args) {
		String type="Email";
		NotificationFactory notificationFactory=new NotificationFactory();
		SendNotification sendNotification=notificationFactory.getInstance(type);
	    sendNotification.send();

	}
}*/