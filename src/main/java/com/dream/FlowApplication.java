package com.dream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {
//		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
//		org.activiti.spring.boot.SecurityAutoConfiguration.class,
//})
public class FlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowApplication.class, args);
	}
}
