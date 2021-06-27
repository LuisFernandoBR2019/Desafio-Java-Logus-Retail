package br.com.logusretail.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.logusretail.api", "br.com.logusretail.database"})
public class LogusRetailApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogusRetailApiApplication.class, args);
	}

}
