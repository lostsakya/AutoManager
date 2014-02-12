package edu.buaa.automanager.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketEngine {

	private Socket socketClient;
	private PrintWriter out;
	private BufferedReader in;

	SocketEngine(Socket socketClient) {
		this.socketClient = socketClient;
	}

	public void sendMsg(String msg) throws IOException {
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())), true);
		out.println(msg);
	}

	public String receiveMsg() throws IOException {
		in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
		StringBuffer msg = null;
		String buffer;
		while ((buffer = in.readLine()) != null) {
			msg.append(buffer);
		}
		return msg.toString();
	}

}
