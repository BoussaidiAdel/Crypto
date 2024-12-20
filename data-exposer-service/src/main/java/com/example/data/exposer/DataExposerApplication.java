package com.example.data.exposer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication(scanBasePackages = {"Controller", "Services", "Models", "utils"})
@EnableScheduling
public class DataExposerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataExposerApplication.class, args);
	}



}
