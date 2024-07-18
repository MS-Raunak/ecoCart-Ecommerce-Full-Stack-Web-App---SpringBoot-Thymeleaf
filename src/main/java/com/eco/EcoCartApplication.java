package com.eco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcoCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoCartApplication.class, args);
		System.out.println("Server started on port: 8088");
	}

}
