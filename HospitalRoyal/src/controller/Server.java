package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import controller.Hospital;
public class Server {

	static DataInputStream inputStream;
	static DataOutputStream outputStream;
	static Methods method;
	public static void main(String[] args) {
		method = new Methods();
		try {
			ServerSocket server;
			server = new ServerSocket(5000);
			Hospital hospital = new Hospital();
			while (true) {
				Socket client = new Socket();
				client = server.accept();
				ThreadServer hilo = new ThreadServer(client, hospital, method);
				hilo.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
