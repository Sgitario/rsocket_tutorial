package com.sgitario.rsocket.spring.server;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Mono;

@Controller
public class EchoController {
	@MessageMapping("/requestresponse")
	public Mono<String> requestResponse(String request) {
		return Mono.just(request);
	}
}
