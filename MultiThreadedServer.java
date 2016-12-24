/*Author: Abheek Gulati
For: NJIT class CS 656
*/

import java.io.*;
import java.net.*;

public class MultiThreadedServer implements Runnable {

	Socket connSocket;

	MultiThreadedServer(Socket connectionSocket) {
		this.connSocket = connectionSocket;
	}

	public void run() {

		try {

			String clientSentence;
			String capitalizedSentence;

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));  //get input from client
			clientSentence = inFromClient.readLine();

			System.out.println("Recieved: " + clientSentence);
			capitalizedSentence = clientSentence.toUpperCase() + '\n';

			DataOutputStream outToClient = new DataOutputStream(connSocket.getOutputStream());   // send result to client
			outToClient.writeBytes(capitalizedSentence);

			connSocket.close();

		}
			catch (Exception e) {
				System.out.println("Exception as: " + e);
			}

	}

	public static void main(String argv[]) throws Exception {
		

		ServerSocket welcomeSocket = new ServerSocket(6789);   //Create socket

		System.out.println("Listening...");

		while(true) {

			Socket connectionSocket = welcomeSocket.accept();   //Accept from socket

			System.out.println("Connected...");

			new Thread(new MultiThreadedServer(connectionSocket)).start();

		}

	}
}
