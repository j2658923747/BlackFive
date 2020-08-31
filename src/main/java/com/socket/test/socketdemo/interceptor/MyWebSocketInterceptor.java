package com.socket.test.socketdemo.interceptor;

import com.socket.test.socketdemo.dao.TUserMapper;
import com.socket.test.socketdemo.domain.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Controller
public class MyWebSocketInterceptor implements HandshakeInterceptor {

    @Autowired
    private TUserMapper tum;
    @Value("${setting.gold}")
    private Integer gold;

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        //握手前
        ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) serverHttpRequest;
        HttpSession session = servletServerHttpRequest.getServletRequest().getSession();
        TUser t = (TUser) session.getAttribute("loginUser");
        if (t!=null){
            System.out.println("登录成功！"+t.toString());
            return true;
        }else{
            System.out.println("没有登录!");
            return true;
            //return false;
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        //握手后
        System.out.println("握手成功！");
    }
}
