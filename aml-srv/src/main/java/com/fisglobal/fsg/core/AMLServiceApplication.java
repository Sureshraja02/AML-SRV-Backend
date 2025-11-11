package com.fisglobal.fsg.core;

import org.springframework.boot.SpringApplication;
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
@EntityScan({"com.fisglobal.fsg.core"})
@ComponentScan({"com.fisglobal.fsg.core"})
@EnableJpaRepositories({"com.fisglobal.fsg.core"})
@SpringBootApplication
@EnableScheduling
public class AMLServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AMLServiceApplication.class, args);
		System.out.println("Hello World!");
	}
}
