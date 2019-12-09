package com.sgitario.rsocket.spring.client;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestEchoController {
	@Autowired
	private RSocketRequester rSocket;

	@GetMapping(value = "/echo/{message}")
	public Publisher<String> echo(@PathVariable("message") String message) {
		return rSocket.route("requestresponse").data(message).retrieveMono(String.class);
	}
}
