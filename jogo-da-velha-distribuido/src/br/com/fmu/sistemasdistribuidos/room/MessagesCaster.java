package br.com.fmu.sistemasdistribuidos.room;

import java.io.InputStream;
import java.util.Scanner;

import br.com.fmu.sistemasdistribuidos.server.Server;

public class MessagesCaster implements Runnable {

	private InputStream client;
	private Server server;
	
	public MessagesCaster(InputStream client, Server server) {
		this.client = client;
		this.server = server;
	}


	@Override
	public void run() {
		Scanner scanner = new Scanner(this.client);
		while (scanner.hasNextLine()) {
			server.castMessages(scanner.nextLine());
		}
		
		scanner.close();
	}

}
