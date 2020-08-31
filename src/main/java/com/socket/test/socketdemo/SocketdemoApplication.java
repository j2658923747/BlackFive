package com.socket.test.socketdemo;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.client.utils.HttpClientUtils;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class SocketdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketdemoApplication.class, args);
    }


    @Test
    public void test1(){
        HttpClient h = new HttpClient();
//        HashMap

    }
}
