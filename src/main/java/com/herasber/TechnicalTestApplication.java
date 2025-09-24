package com.herasber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.herasber")
public class TechnicalTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalTestApplication.class, args);
	}

}
