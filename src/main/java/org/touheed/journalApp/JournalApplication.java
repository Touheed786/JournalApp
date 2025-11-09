package org.touheed.journalApp;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JournalApplication {

	@Value("${app.test}")
	private String value;

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		SpringApplication.run(JournalApplication.class, args);
	}

	@PostConstruct()
	public void init(){
		System.out.println(value);
	}

}
