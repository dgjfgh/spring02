package com.test.mytomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DispatcherRequest implements Runnable {

    private Socket socket;

    public DispatcherRequest(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = socket.getInputStream();
            os = socket.getOutputStream();

            byte[] b = new byte[1024];
            is.read(b);
            System.out.println("客户端内容:" + new String(b));

            String str = "<html><head><title>Hi MyTomcat</title></head>"
                    + "<body><h1>qwertyu</h1></body></html>";
            os.write(str.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
