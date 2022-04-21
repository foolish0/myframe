package com.gabriel.myframe.common.netty;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer03 {
    public static void main(String[] args) throws IOException {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        final ServerSocket serverSocket = new ServerSocket(8082);
        // 创建一个线程池，用于接收客户端的连接
        while (true) {
            executorService.execute(() -> {
                Socket accept = null;
                try {
                    accept = serverSocket.accept();
                    service(accept);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }
    private static void service(Socket socket) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type: text/html; charset=UTF-8");
        printWriter.println("");
        String hello = "<html><head><title>Hello</title></head><body><h1>Hello</h1></body></html>";
        printWriter.write(hello);
        printWriter.close();
        socket.close();
    }
}
