package com.logevents.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.logevents.demo"})
public class LogEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogEventApplication.class, args);
	}
}
