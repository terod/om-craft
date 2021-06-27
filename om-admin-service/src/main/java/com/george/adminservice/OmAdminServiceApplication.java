package com.george.adminservice;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class OmAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmAdminServiceApplication.class, args);
	}

}
