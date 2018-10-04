package br.com.fmu.sistemasdistribuidos.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			Socket client = new Socket("127.0.0.1", 12345);
			System.out.println("Conectando ao servidor...");
			
			Scanner scanner = new Scanner(System.in);
			PrintStream out = new PrintStream(client.getOutputStream());
			
			while (scanner.hasNextLine()) {
				out.println(scanner.nextLine());
			}
			
			out.close();
			scanner.close();
			client.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
