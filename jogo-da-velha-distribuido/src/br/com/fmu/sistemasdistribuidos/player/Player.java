package br.com.fmu.sistemasdistribuidos.player;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import br.com.fmu.sistemasdistribuidos.client.Client;
import br.com.fmu.sistemasdistribuidos.server.Server;

public class Player extends Thread {

	private char mark;
	private Player opponent;
	private Socket port;
	private InputStream input;
	private PrintStream output;
	private Server server;

	public Player(char mark, Socket port, Server server) {
		this.mark = mark;
		this.port = port;
		this.server = server;

		try {
			this.input = port.getInputStream();
			this.output = new PrintStream(port.getOutputStream());
		} catch (IOException e) {
			System.out.println("A conexão com " + port.getInetAddress().getHostAddress() + " caiu!!!");
		}
	}

	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	public void run() {
		try {
			output.println("Jogadores conectados.\n QUE OS JOGOS COMEÇEM!!!");

			Scanner scanner = new Scanner(this.input);
			while (scanner.hasNext()) {
				server.castMessages(scanner.nextLine());
			}

		} finally {
			closePort();
		}
	}

	public PrintStream getOutput() {
		return output;
	}

	public void setOutput(PrintStream output) {
		this.output = output;
	}

	private void closePort() {
		try {
			this.port.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			new Client("127.0.0.1", 12345).connect();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InputStream getInput() {
		return this.input;
	}

}
