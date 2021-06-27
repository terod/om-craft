package com.george.om.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OmOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmOrderServiceApplication.class, args);
	}

}
