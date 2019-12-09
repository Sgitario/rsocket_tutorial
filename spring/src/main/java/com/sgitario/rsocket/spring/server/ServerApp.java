package com.sgitario.rsocket.spring.server;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApp {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ServerApp.class, args);
	}
}
