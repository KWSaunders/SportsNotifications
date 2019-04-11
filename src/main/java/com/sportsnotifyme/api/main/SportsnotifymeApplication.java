package com.sportsnotifyme.api.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = { "com.sportsnotifyme.api" })
@EntityScan("com.sportsnotifyme.api")
public class SportsnotifymeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsnotifymeApplication.class, args);
	}

}
