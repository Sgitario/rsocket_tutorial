package com.sgitario.rsocket.basics;

import java.io.IOException;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocketFactory;
import io.rsocket.SocketAcceptor;
import io.rsocket.transport.netty.server.TcpServerTransport;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

public class ServerApp {

	public static final int TCP_PORT = 8091;

	private final Disposable server;

	public ServerApp() {
		this.server = RSocketFactory.receive().acceptor(acceptor())
				.transport(TcpServerTransport.create("localhost", TCP_PORT)).start().subscribe();
	}

	private SocketAcceptor acceptor() {
		return (setupPayload, reactiveSocket) -> Mono.just(new AbstractRSocket() {
			@Override
			public Mono<Payload> requestResponse(Payload p) {
				try {
					return Mono.just(p);
				} catch (Exception x) {
					return Mono.error(x);
				}
			}

		});
	}

	public void dispose() {
		this.server.dispose();
	}

	public static void main(String[] args) throws IOException {
		ServerApp server = new ServerApp();
		System.out.println("\nPress any key to exit.\n");
		System.in.read();
		server.dispose();
	}
}
