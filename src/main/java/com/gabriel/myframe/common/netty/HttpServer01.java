package com.gabriel.myframe.common.netty;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer01 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            service(socket);
        }
    }

    private static void service(Socket socket) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html");
        String html = "<html><head><title>Hello</title></head><body><h1>Hello World</h1></body></html>";
        writer.println("Content-Length: " + html.length());
        writer.println();
        writer.write(html);
        writer.close();
        socket.close();
    }
}
