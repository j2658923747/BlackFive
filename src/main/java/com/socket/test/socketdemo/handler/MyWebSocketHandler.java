package com.socket.test.socketdemo.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.socket.test.socketdemo.dao.TUserMapper;
import com.socket.test.socketdemo.domain.TUser;
import com.socket.test.socketdemo.webSocket.SendMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class MyWebSocketHandler implements WebSocketHandler {

    @Autowired
    private TUserMapper tum;
    @Value("${setting.gold}")
    private Integer gold;

    //保存用户链接
    public static ConcurrentHashMap<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();

    // 连接 就绪时
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("已连接:"+webSocketSession.getId());
        users.put(webSocketSession.getId(),webSocketSession);
        nowOnline();
    }
    // 处理信息
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws IOException {
        makeCode(webSocketSession,webSocketMessage);
    }
    // 处理传输时异常
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.out.println("发生异常:"+throwable.toString());
    }
    // 关闭 连接时
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("关闭连接:"+closeStatus.toString());
        users.remove(webSocketSession.getId());
        try{
            webSocketSession.close();
        } catch (IOException e) {
            System.out.println("关闭错误！");
        }
        nowOnline();
    }
    //是否支持分包
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    public void nowOnline(){
        System.out.println("当前在线人数:"+users.size());
    }



    //处理code
    private void makeCode(WebSocketSession webSocketSession,WebSocketMessage<?> webSocketMessage) throws IOException {
        String message = webSocketMessage.getPayload().toString();
        try{
            JSONObject parse = JSON.parseObject(message);
            System.out.println("客户端:"+webSocketSession.getId()+" 发来消息:"+message);

            Integer code = (Integer) parse.get("code");
            if (code==100){//登录
                String username = (String) parse.get("username");
                String password = (String) parse.get("password");
                System.out.println(username);
                System.out.println(password);
                TUser t = new TUser();
                t = tum.selectByAll(username,password);
                TextMessage tm;
                if (t==null){
                    tm = new TextMessage("{\"code\":201,\"msg\":\"用户名或密码错误\"}");
                }else{
                    tm = new TextMessage("{\"code\":200,\"token\":\""+t.getToken()+"\"}");
                }
                SendMessage.sendOne(webSocketSession,tm);
            }else if(code==101){//注册
                String username = (String) parse.get("username");
                String password = (String) parse.get("password");
                TUser t1 = new TUser();
                t1=tum.selectByUsername(username);
                TextMessage tm;
                if (t1!=null){
                    tm = new TextMessage("{\"code\":202,\"msg\":\"用户名已存在\"}");
                    SendMessage.sendOne(webSocketSession,tm);
                    return;
                }

                String tk = UUID.randomUUID().toString();
                tk=tk.replace("-","");
                TUser t = new TUser(username,password,tk,gold);
                System.out.println(t);
                int res = tum.insert(t);
                System.out.println(res);
                if (res > 0){
                    tm = new TextMessage("{\"code\":210,\"msg\":\"注册成功\"}");
                }else{
                    tm = new TextMessage("{\"code\":203,\"msg\":\"注册失败\"}");
                }
                SendMessage.sendOne(webSocketSession,tm);
            }else{
                String token = (String) webSocketSession.getAttributes().get("token");

            }

        }catch (Exception e){
            System.out.println("json转换错误，信息为:"+message);
            e.printStackTrace();
        }finally {
            TextMessage tm = new TextMessage(message);
            //SendMessage.sendAll(tm);
        }
    }
}
