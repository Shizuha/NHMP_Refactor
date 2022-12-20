package com.kmj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class NhmpSpringBootApplication {
	
	@GetMapping("/")
	public String index () {
		return "Springboot Hello World";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(NhmpSpringBootApplication.class, args);
	}

}
