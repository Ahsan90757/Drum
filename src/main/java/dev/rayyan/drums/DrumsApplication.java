package dev.rayyan.drums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DrumsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrumsApplication.class, args);
	}

	// @GetMapping("/")
	// public String getRoot() {
	// 	return ("Hello World\n");
	// }
}
