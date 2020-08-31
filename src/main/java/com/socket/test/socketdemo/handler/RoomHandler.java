package com.socket.test.socketdemo.handler;

import com.socket.test.socketdemo.domain.TUser;
import com.socket.test.socketdemo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/room")
@CrossOrigin
public class RoomHandler {

    @Autowired
    RoomService rs;
    @Value("${setting.create-gold}")
    Integer createGold;

    @RequestMapping("/createRoom")
    public void createRoom(String token,Integer gameMax,HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        if (token==null){
            res.getWriter().write("{\"code\":301,\"msg\":\"未登录\"}");
            return;
        }
        String ret = rs.createRoom(token,gameMax,req.getRemoteAddr());
        res.getWriter().write(ret);
    }
}
