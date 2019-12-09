package com.sgitario.rsocket.spring.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;

// @Configuration
public class ClientConfiguration {
	@Bean
	public RSocket rSocket(@Value("${spring.rsocket.server.port}") Integer serverPort) {
		return RSocketFactory.connect()
				.mimeType(MimeTypeUtils.APPLICATION_JSON_VALUE, MimeTypeUtils.APPLICATION_JSON_VALUE)
				.frameDecoder(PayloadDecoder.ZERO_COPY).transport(TcpClientTransport.create("localhost", serverPort))
				.start().block();
	}

	@Bean
	RSocketRequester rSocketRequester(RSocket rSocket, RSocketStrategies rSocketStrategies) {
		return RSocketRequester.wrap(rSocket, MimeTypeUtils.APPLICATION_JSON, MimeTypeUtils.APPLICATION_JSON,
				rSocketStrategies);
	}
}
