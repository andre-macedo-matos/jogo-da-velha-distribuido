package br.com.fmu.sistemasdistribuidos.player;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Player extends Thread {

	private char mark;
	private Player opponent;
	private Socket port;
	private InputStream input;
	private PrintStream output;

	public Player(char mark, Socket port) {
		this.mark = mark;
		this.port = port;

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
				this.opponent.output.println(this.mark + ": " + scanner.nextLine());
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

	public InputStream getInput() {
		return this.input;
	}

}
