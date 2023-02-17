package com.example.assignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Assignment2Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Assignment2Application.class, args);
		String csvFile = "/home/david/furniture stores pages.csv";
		CSVReader.read(csvFile);

	}

}
