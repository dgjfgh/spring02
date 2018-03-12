package com.test.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receive {

    public static void main(String[] args) {
        try {
            int port = 7001;
            InetAddress iaddress = InetAddress.getByName("255.255.255.255");
            MulticastSocket socket = new MulticastSocket(port);
            while (true) {
                byte data[] = new byte[1024];
                DatagramPacket packet = new DatagramPacket(data, data.length, iaddress, port);
                socket.receive(packet);
                String msg = new String(data);
                System.out.println(packet.getAddress().getHostAddress() + "  " + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
