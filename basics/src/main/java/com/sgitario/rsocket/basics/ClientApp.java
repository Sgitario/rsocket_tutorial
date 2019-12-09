package com.sgitario.rsocket.basics;

import java.io.IOException;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;

public class ClientApp {

	public static void main(String[] args) throws IOException {
		RSocket socket = RSocketFactory.connect().transport(TcpClientTransport.create("localhost", ServerApp.TCP_PORT))
				.start().block();

		socket.requestResponse(DefaultPayload.create("Hello")).map(Payload::getDataUtf8).onErrorReturn("error")
				.doOnNext(System.out::println).block();
	}
}
