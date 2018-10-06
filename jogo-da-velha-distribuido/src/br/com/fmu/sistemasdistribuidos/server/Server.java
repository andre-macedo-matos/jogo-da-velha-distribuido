package br.com.fmu.sistemasdistribuidos.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import br.com.fmu.sistemasdistribuidos.player.Player;

public class Server {
	
	private int port;
	private List<PrintStream> clients;
	
	public Server(int port) {
		this.port = port;
		this.clients = new ArrayList<PrintStream>();
	}
	
	public void execute() throws IOException {
		ServerSocket server = new ServerSocket(this.port);
		System.out.println("Servidor online!!!");
		
		while (true) {
			Player playerOne = new Player('X', server.accept(), this);
			Player playerTwo = new Player('O', server.accept(), this);
			
			playerOne.setOpponent(playerTwo);
			playerTwo.setOpponent(playerOne);
			
			playerOne.start();
			playerTwo.start();
			
			this.clients.add(playerOne.getOutput());
			this.clients.add(playerTwo.getOutput());
		}
	}

	public void castMessages(String message) {
		System.out.println(message);
		for (PrintStream client : clients) {
			client.println(message);
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
