package br.com.fmu.sistemasdistribuidos.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	
	public static void main(String[] args) {
		
		try {
			ServerSocket server = new ServerSocket(12345);
			System.out.println("Servidor rodando na porta 12345...");
			
			Socket client = server.accept();
			System.out.println("Nova connexão com " + client.getInetAddress().getHostAddress());
			
			Scanner scanner = new Scanner(client.getInputStream());
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
			
			scanner.close();
			client.close();
			server.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
