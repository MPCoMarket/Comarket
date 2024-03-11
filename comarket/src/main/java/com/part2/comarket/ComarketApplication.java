package com.part2.comarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ComarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComarketApplication.class, args);
	}

}
