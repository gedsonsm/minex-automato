package br.com.minexautomato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MinexAutomatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinexAutomatoApplication.class, args);
	}

}
