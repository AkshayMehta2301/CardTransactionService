package com.test.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardTransactionServiceApplication {

	public static void main(String[] args) {
		System.out.println("Running Good");
		SpringApplication.run(CardTransactionServiceApplication.class, args);
	}

}
