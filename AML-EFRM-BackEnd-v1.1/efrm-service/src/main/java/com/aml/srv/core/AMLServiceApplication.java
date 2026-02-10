package com.aml.srv.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

/**
 * Hello world!
 *
 */
@EnableEncryptableProperties
@EntityScan({ "com.aml.srv.core" })
@ComponentScan({ "com.aml.srv.core" })
@EnableJpaRepositories({ "com.aml.srv.core" })
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
public class AMLServiceApplication {// implements CommandLineRunner

	public static void main(String[] args) {
		SpringApplication.run(AMLServiceApplication.class, args);
		System.out.println("Hello World!");
	}

	/*
	 * @Override public void run(String... args) throws Exception {
	 * System.out.println("=== Application Started ==="); }
	 */
}
