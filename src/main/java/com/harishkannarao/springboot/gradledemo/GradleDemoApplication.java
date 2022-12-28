package com.harishkannarao.springboot.gradledemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GradleDemoApplication {

	private static final Logger LOG = LoggerFactory.getLogger(GradleDemoApplication.class);

	public static void main(String[] args) {
		LOG.info("Starting " + GradleDemoApplication.class.getName());
		SpringApplication.run(GradleDemoApplication.class, args);
	}

}
