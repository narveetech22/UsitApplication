package com.narvee.usit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.narvee.usit.controller.RolesController;

@SpringBootApplication
public class UsitApplication {
	public static final Logger logger = LoggerFactory.getLogger(RolesController.class);

	public static void main(String[] args) {
		logger.info("UsitApplication.main()");
		SpringApplication.run(UsitApplication.class, args);
	}

}
