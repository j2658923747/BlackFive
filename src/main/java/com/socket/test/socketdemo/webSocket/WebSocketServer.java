package com.socket.test.socketdemo.webSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket服务端
 */

@ServerEndpoint("/ws") //映射WebSocket访问路径
@Component // 注册到spring容器中 相当于bean
public class WebSocketServer {

    //存放所有已连接的用户
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    //新建连接
    @OnOpen
    public void onOpen(Session session){
        System.out.println("有新的用户连接了:"+session.getId());
        //将新连接的用户存入数组
        clients.put(session.getId(),session);
    }

    //断开连接
    @OnClose
    public void onClose(Session session){
        System.out.println("用户断开连接:"+session.getId());
        //移出数组
        clients.remove(session.getId());
        try{
            session.close();
        } catch (IOException e) {
            System.out.println("关闭错误！");
        }
    }

    //发生错误
    @OnError
    public void onError(Throwable throwable){
        System.out.println("发生错误!");
        throwable.printStackTrace();
    }

    //收到客户端消息
    @OnMessage
    public void onMessage(Session session , String message){
        try{
            JSONObject parse = JSON.parseObject(message);
            System.out.println("客户端:"+session.getId()+" 发来消息:"+message);
        }catch (Exception e){
            System.out.println("json转换错误，信息为:"+message);
        }finally {
            sendAll(message);
        }
    }

    //群发消息
    private void sendAll(String message){
        for (Map.Entry<String,Session> sessionEntry:clients.entrySet()){
            sessionEntry.getValue().getAsyncRemote().sendText(message);
        }
    }
}
