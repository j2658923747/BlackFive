package com.socket.test.socketdemo;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTest {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        System.out.println("连接成功！");
        InputStream is = socket.getInputStream();
        // 4.把字节输入流转换成字符输入流
        Reader isr = new InputStreamReader(is);
        // 5.把字符输入流包装成缓冲字符输入流。
        BufferedReader br = new BufferedReader(isr);

        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os);



        // 6. 按照行读取游总。
        String line;
        while ((line = br.readLine()) != null) {

            System.out.println(line);
            ps.println("收到消息！"+line);
            ps.flush();
        }
    }

    @Test
    public void tes1() throws IOException {
        Socket socket = new Socket("127.0.0.1",9999);
        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os);
        ps.println("我是客户端！");
        ps.flush();
    }
}
