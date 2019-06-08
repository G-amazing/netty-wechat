package com.gamazing.nettychat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gamazing"})
public class NettyChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(NettyChatApplication.class, args);
	}

}
