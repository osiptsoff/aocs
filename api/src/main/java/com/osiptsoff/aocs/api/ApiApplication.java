package com.osiptsoff.aocs.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiApplication {
	@Bean
	Logger logger() {
		return LogManager.getLogger("");
	}
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
