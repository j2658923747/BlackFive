package com.socket.test.socketdemo.handler;

import com.socket.test.socketdemo.dao.TUserMapper;
import com.socket.test.socketdemo.domain.TUser;
import com.socket.test.socketdemo.utils.UTIL;
import com.socket.test.socketdemo.webSocket.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Controller
@CrossOrigin
@RequestMapping("/lr")//登录l和注册r
public class RLHandler {

    @Autowired
    private TUserMapper tum;
    @Value("${setting.gold}")
    private Integer gold;

    @RequestMapping("login")
    public void login(TUser t, HttpServletRequest req, HttpServletResponse res) throws IOException {
        t= UTIL.isSQL(t);
        if (t.getUsername()==null||t.getPassword()==null||t.getUsername().length()<6||t.getPassword().length()<6){
            res.getWriter().write("{\"code\":101,\"msg\":\"用户信息错误\"}");
            return ;
        }
        HttpSession session = req.getSession();
        if (session.getAttribute("loginUser")!=null){
            session.removeAttribute("loginUser");
        }
        t=tum.selectByAll(t.getUsername(),t.getPassword());
        if (t==null){
            res.getWriter().write("{\"code\":201,\"msg\":\"用户名或密码错误\"}");
            return;
        }
        session.setAttribute("loginUser",t);
        System.out.println(session.getId());
        res.getWriter().write("{\"code\":200,\"msg\":\"登录成功\",\"token\":\""+t.getToken()+"\"}");
        return;
    }

    @RequestMapping("register")
    public void register(TUser t, HttpServletRequest req,HttpServletResponse res) throws IOException {
        t= UTIL.isSQL(t);
        if (t.getUsername()==null||t.getPassword()==null){
            res.getWriter().write("{\"code\":101,\"msg\":\"用户信息错误\"}");
            return ;
        }
        if (t.getUsername().length()<6 || t.getPassword().length()<6){
            res.getWriter().write("{\"code\":204,\"msg\":\"信息长度不符合要求\"}");
            return ;
        }
        if (tum.selectByUsername(t.getUsername())!=null){
            res.getWriter().write("{\"code\":202,\"msg\":\"用户名已存在\"}");
            return ;
        }
        String tk = UUID.randomUUID().toString();
        tk=tk.replace("-","");
        t.setId(UTIL.getRandomNum());
        t.setGold(gold);
        t.setToken(tk);
        int i = tum.insertSelective(t);
        if (i>0){
            res.getWriter().write("{\"code\":210,\"msg\":\"注册成功\"}");
            return ;
        }
        res.getWriter().write("{\"code\":203,\"msg\":\"注册失败\"}");
        return ;
    }
}
