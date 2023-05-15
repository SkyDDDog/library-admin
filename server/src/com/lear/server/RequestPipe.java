package com.lear.server;

import com.lear.router.Router;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 请求管道
 * @author 天狗
 */
public class RequestPipe implements Runnable {

    private Socket client;
    private String path;

    public RequestPipe(Socket client, String path) {
        this.client = client;
        this.path = path;
    }

    @Override
    public void run() {
        try {
            OutputStream os = client.getOutputStream();
            Router.invoke(this.path, os);
            os.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
