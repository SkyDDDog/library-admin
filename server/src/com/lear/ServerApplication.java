package com.lear;

import com.lear.server.RequestPipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 服务器启动类
 * @author 天狗
 */
public class ServerApplication {

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8888);
        } catch (IOException e) {
            System.out.println("服务器启动失败");
            e.printStackTrace();
        }
        ExecutorService executorService = new ThreadPoolExecutor(1, 30, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(30));
        while (true) {
            Socket client = null;
            try {
                client = server.accept();
                InputStreamReader inputStreamReader = new InputStreamReader(client.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String path = bufferedReader.readLine();
                executorService.submit(new RequestPipe(client, path));
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        executorService.shutdown();
    }



}
