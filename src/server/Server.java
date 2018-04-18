package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Hosts multiplayer games of Hangman. Pairs clients together. 
 *
 */
public class Server implements Runnable, Serializable {
	private Thread server = new Thread(this);
	private ServerSocket serverSocket;
	private ArrayList<ClientHandler> clientList = new ArrayList<ClientHandler>();
	private ArrayList<Game> gameList = new ArrayList<Game>();
	private int port;
	
	public Server(int port) {
		this.port = port;
		try {
			serverSocket = new ServerSocket(port);
			server.start();
		} catch (IOException e) {}
	}
	
	public void logout(ClientHandler ch) {
		System.out.println(ch.getUsername() + " wants to disconnect.");
		clientList.remove(ch);
		ch = null; //needed?
		sendClientList();
	}
	
	private void sendClientList() {
		ArrayList<String> usernameList = new ArrayList<String>();
		for (ClientHandler ch : clientList) {
			usernameList.add(ch.getUsername());
		}
		
		for (ClientHandler ch : clientList) {
			ch.sendClientList(usernameList);
		}
	}
	
	@Override
	public void run() {
		System.out.println("Server is running on port " + port + "...");
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				
				String username = (String)ois.readObject();
				System.out.println(username + " connected.");

				clientList.add(new ClientHandler(socket, oos, this, username));
				
				sendClientList();
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}	
	}
}
