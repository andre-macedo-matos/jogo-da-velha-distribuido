package br.com.fmu.sistemasdistribuidos.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.com.fmu.sistemasdistribuidos.room.MessagesCaster;

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
			Socket client = server.accept();
			System.out.println("Novo usuario conectado: " + client.getLocalPort());
			
			PrintStream clientInput = new PrintStream(client.getOutputStream());
			this.clients.add(clientInput);
			
			MessagesCaster messagesCast = new MessagesCaster(client.getInputStream(), this);
			new Thread(messagesCast).start();
		}
	}

	public void castMessages(String message) {
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
