package io.github.originalalex.twitter;

import io.github.originalalex.twitter.server.controller.NoteController;
import io.github.originalalex.twitter.server.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {UserController.class, NoteController.class})
public class TwitterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterApplication.class, args);
	}


}
