package com.test.udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SSocket {


	public static void main(String[] args) {
		try {
			DatagramSocket socket;
			InetAddress iaddress;
			int port = 8848;
			iaddress = InetAddress.getByName("255.255.255.255");
			socket = new DatagramSocket(port);


			while (true) {
				byte[] buf = "haha".getBytes();
				DatagramPacket packet = new DatagramPacket(buf, buf.length,
						iaddress, port);
				socket.send(packet);
				Thread.sleep(3000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
