package ru.yellow.noteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"ru.yellow.noteservice"})
@EntityScan("ru.yellow.entities")
@EnableJpaRepositories("ru.yellow.repos")
public class NoteserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteserviceApplication.class, args);
	}

}
