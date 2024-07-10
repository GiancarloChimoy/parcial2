package com.chimoy.infraccionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class InfraccionserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfraccionserviceApplication.class, args);
	}

}
