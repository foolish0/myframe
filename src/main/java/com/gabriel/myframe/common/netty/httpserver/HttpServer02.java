package com.gabriel.myframe.common.netty.httpserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer02 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        while (true) {
            final Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    service(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
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
