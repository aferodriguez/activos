package com.prueba.activosbackend;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class ActivosBackendApplication {
	
	@PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC-5"));   // It will set UTC timezone
        System.out.println("Spring boot application running in UTC timezone :"+new Date());   // It will print UTC timezone
    }

	public static void main(String[] args) {
		SpringApplication.run(ActivosBackendApplication.class, args);
	}
}
