package com.example.SwipeRest;

import com.example.SwipeRest.controller.auth.AuthenticationRequest;
import com.example.SwipeRest.controller.auth.AuthenticationService;
import com.example.SwipeRest.enums.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SwipeRestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SwipeRestApplication.class, args);
	}

}
