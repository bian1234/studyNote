package com.byk.characterrecognition.character_recognition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CharacterRecognitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharacterRecognitionApplication.class, args);
	}
}
