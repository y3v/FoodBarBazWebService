package com.foodbarbaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StartingPointApp {
	
	//creates the servlet container and everything -- amazing
	public static void main(String[] args) {
		
		SpringApplication.run(StartingPointApp.class , args);
        
		
	}

	

}
