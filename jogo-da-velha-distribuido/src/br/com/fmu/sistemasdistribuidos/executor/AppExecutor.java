package br.com.fmu.sistemasdistribuidos.executor;

import java.io.IOException;
import java.net.UnknownHostException;

import br.com.fmu.sistemasdistribuidos.client.Client;

public class AppExecutor {

	public static void main(String[] args) throws UnknownHostException, IOException {
		while (true) {
			String serverAddress = (args.length == 0) ? "localhost" : args[1];
			new Client(serverAddress, 12345).connect();
		}
	}

}
