package echo_stream_java;

import java.io.*;
import java.net.*;

public class EchoServer {
	public static void main(String args[]) {
		ServerSocket echoServer = null;
		String line;
		BufferedReader in;
		PrintStream out;
		Socket clientSocket = null;

		try {
		   echoServer = new ServerSocket(10000);
		}
		catch (IOException e) {
		   System.out.println(e);
		}

		try {
			System.out.println("echoServer started...");
			clientSocket = echoServer.accept();
			System.out.println("echoServer: connection accpeted from "+clientSocket.getInetAddress().getHostName());
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintStream(clientSocket.getOutputStream());
			boolean loop = true;

			while (loop) {
				line = in.readLine();
				System.out.println(line);
				out.println(line);
				if (line.equalsIgnoreCase("quit")){
					loop = false;
					in.close();
					out.close();
					clientSocket.close();
					echoServer.close();
					break;
				}
			}
		}
		catch (IOException e) {
			System.out.println(e);
		}

		System.out.println("echoServer closed...");
	}
}
