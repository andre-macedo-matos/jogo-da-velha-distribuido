package br.com.fmu.sistemasdistribuidos.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import br.com.fmu.sistemasdistribuidos.room.SenderMessages;

public class Client {
	
	private String hostIP;
	private int port;
	
	public Client(String hostIP, int port) {
		this.hostIP = hostIP;
		this.port = port;
	}
	
	public void connect() throws UnknownHostException, IOException {
		Socket client = new Socket(this.hostIP, this.port);
		System.out.println("Conectando...");
		
		SenderMessages sender = new SenderMessages(client.getInputStream());
		new Thread(sender).start();
		
		Scanner scanner = new Scanner(System.in);
		PrintStream out = new PrintStream(client.getOutputStream());
		
		while(scanner.hasNextLine()) {
			out.println(scanner.nextLine());
		}
		
		out.close();
		scanner.close();
		client.close();
	}
	
}
