package br.com.fmu.sistemasdistribuidos.server;

import java.io.IOException;
import java.net.ServerSocket;

import br.com.fmu.sistemasdistribuidos.player.Player;

public class Server {
	
	private int port;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void execute() throws IOException {
		ServerSocket server = new ServerSocket(this.port);
		System.out.println("Servidor online!!!");
		
		try {
			while (true) {
				Player playerOne = new Player('X', server.accept());
				Player playerTwo = new Player('O', server.accept());
				
				playerOne.setOpponent(playerTwo);
				playerTwo.setOpponent(playerOne);
				
				playerOne.start();
				playerTwo.start();
			}
			
		} finally {
			server.close();
		}
	}

	public static void main(String[] args) {
		try {
			new Server(12345).execute();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
