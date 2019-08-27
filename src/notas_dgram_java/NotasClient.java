package notas_dgram_java;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class NotasClient {

	public static void main(String[] args) throws IOException {

		String[] argus = new String[2];
		argus[0] = "localhost";
		argus[1] = "João";
		
		if	(argus.length != 2) {
			System.out.println("Uso: java NotasClient <maquina> <none>");
			System.exit(1);
		}
		// cria um socket datagrama
		DatagramSocket socket = new DatagramSocket();
		// envia um pacote
		byte[] nome = new byte[256];
		nome = argus[1].getBytes();
		InetAddress servIP = InetAddress.getByName(argus[0]);
		DatagramPacket pacEnv = new DatagramPacket(nome, nome.length, servIP, 0x8003);
		socket.send(pacEnv);
		// obtem a resposta
		byte[] bufNota = new byte[8];
		DatagramPacket pacRec = new DatagramPacket(bufNota, bufNota.length);
		socket.receive(pacRec);
		// mostra a resposta
		System.out.println("Nota do aluno '"+argus[1]+"' = "+ByteBuffer.wrap(bufNota).getDouble());
		// fecha o socket
		socket.close();
	}

}
