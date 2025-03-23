package com.AccioJob.VoiceToDoListWithAi;

import com.AccioJob.VoiceToDoListWithAi.util.MyAnalyzer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;

@SpringBootApplication
public class VoiceToDoListWithAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoiceToDoListWithAiApplication.class, args);
		HashSet<String> strings=new HashSet<>();
			MyAnalyzer myAnalyzer = new MyAnalyzer(strings);
			String response = myAnalyzer.stem("Nike shoes for mens");
			System.out.println(response);
		}
	}