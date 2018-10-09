package br.com.fmu.sistemasdistribuidos.executor;

import java.io.IOException;
import java.net.UnknownHostException;

import br.com.fmu.sistemasdistribuidos.client.Client;

public class AppExecutor {

	public static void main(String[] args) throws UnknownHostException, IOException {
		new Client("127.0.0.1", 12345).connect();
	}

}
