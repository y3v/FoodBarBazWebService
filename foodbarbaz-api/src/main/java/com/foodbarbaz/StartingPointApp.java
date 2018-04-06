package com.foodbarbaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StartingPointApp extends SpringBootServletInitializer {
	
	//creates the servlet container and everything -- amazing
	public static void main(String[] args) {
		SpringApplication.run(StartingPointApp.class , args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(StartingPointApp.class);
	}
}
