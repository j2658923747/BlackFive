package com.socket.test.socketdemo.webSocket;

import com.socket.test.socketdemo.handler.MyWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

public class SendMessage {
    //群发消息
    public static void sendAll(TextMessage message) throws IOException {
        for (Map.Entry<String, WebSocketSession> sessionEntry: MyWebSocketHandler.users.entrySet()){
            sessionEntry.getValue().sendMessage(message);
        }
    }
    //单发消息
    public static void sendOne(WebSocketSession webSocketSession,TextMessage message) throws IOException {
        webSocketSession.sendMessage(message);
    }
}
