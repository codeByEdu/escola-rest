package com.escola.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class EscolaRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(EscolaRestApplication.class, args);
	}
}
