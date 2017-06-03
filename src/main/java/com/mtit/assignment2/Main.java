package com.mtit.assignment2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args) {
		System.out.println("STARTING APPLICATION");
		SpringApplication.run(Main.class, args);
	}
}
