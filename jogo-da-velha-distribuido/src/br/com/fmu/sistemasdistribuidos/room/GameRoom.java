package br.com.fmu.sistemasdistribuidos.room;

import java.io.InputStream;
import java.util.Scanner;

public class GameRoom implements Runnable {
	
	private InputStream server;
	
	public GameRoom(InputStream server) {
		this.server = server;
	}

	@Override
	public void run() {
		Scanner scanner = new Scanner(this.server);
		while(scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
	}
}
