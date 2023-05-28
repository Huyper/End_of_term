package server;
import java.net.*;
import java.io.*;

public class ServerThread implements Runnable{
	ServerSocket	serverSocket;
	MainView		view;
	int				isRunning = 1;
	
	public ServerThread(int port, MainView view) {
		try {
			this.view		=	view;
			serverSocket	=	new ServerSocket(port);
			System.out.println("Server started");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void run() {
		isRunning = 1;
		try {
			Socket socket	=	serverSocket.accept();
			
			
			
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void stop() {
		try {
			serverSocket.close();
			isRunning = 2;
			System.out.println("Server is closed");
			System.exit(0);
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
