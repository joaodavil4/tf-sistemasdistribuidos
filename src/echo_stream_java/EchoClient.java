package echo_stream_java;

import java.io.*;
import java.net.*;

public class EchoClient {
	public static void main(String[] args) throws IOException {
		Socket echoSocket = null;
		DataOutputStream out = null;
		BufferedReader in = null;

		args[0] = "localhost";

		if (args.length != 1) {
			System.out.println("Uso: java EchoClient <maquina>");
			return;
		}

		InetAddress endereco = InetAddress.getByName(args[0]);

		try {
			echoSocket = new Socket(endereco, 10000);
			out = new DataOutputStream(echoSocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Unknown host");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to the hostname");
			System.exit(1);
		}
		if (echoSocket != null && out != null && in != null) {
			try {
				out.writeBytes("OLA\n");
				String responseLine = in.readLine();
				System.out.println("Resposta> "+responseLine);
				out.writeBytes("ECHO\n");
				responseLine = in.readLine();
				System.out.println("Resposta> "+responseLine);
				out.writeBytes("quit\n");
				responseLine = in.readLine();
				System.out.println("Resposta> "+responseLine);
				out.close();
				in.close();
				echoSocket.close();
			} catch (UnknownHostException e) {
				System.err.println("Trying to connect to unknown host: " + e);
				System.exit(1);
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
				System.exit(1);
			}
		}
	}
}
