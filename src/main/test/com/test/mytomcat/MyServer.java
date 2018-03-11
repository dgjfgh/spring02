package com.test.mytomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	
	private ServerSocket serverSocket;
	private int port;
	
	private boolean shutdown;
	
	public MyServer(int port) {
		super();
		this.port = port;
	}

	private void startup() {
		try {
			serverSocket=new ServerSocket(port);
			while(!shutdown){
				Socket socket = serverSocket.accept();
				System.out.println("一个请求来了");
				
				//启动一个县城提供服务
				new Thread(new DispatcherRequest(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		MyServer myServer = new MyServer(8080);
		myServer.startup();
	}

}
