package com.socket.test.socketdemo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.socket.test.socketdemo.dao.TRoomsMapper;
import com.socket.test.socketdemo.dao.TUserMapper;
import com.socket.test.socketdemo.domain.TRooms;
import com.socket.test.socketdemo.domain.TUser;
import com.socket.test.socketdemo.utils.UTIL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Random;

@Service
public class RoomService {

    @Autowired
    TRoomsMapper trm;
    @Autowired
    TUserMapper tum;
    @Value("${setting.create-gold}")
    Integer createGold;

    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public String createRoom(String token, Integer gameMax, String remoteAddr){
        TUser t=tum.selectByToken(token);
        if (t.getGold()<createGold){
            return "{\"code\":310,\"msg\":\"金币不足\"}";
        }
        t.setGold(t.getGold()-createGold);
        tum.updateByPrimaryKey(t);
        TRooms tr = new TRooms();
        tr.setUserNum(1);
        tr.setId(UTIL.getRandomNum());
        tr.setCreateTime((int) (System.currentTimeMillis()/1000));
        tr.setIp(remoteAddr);
//        生成房间初始信息
        String roomInfo="{\n" +
                "\t\"roomNum\": "+tr.getId()+",\n" +
                "\t\"baseScore\": 1,\n" +
                "\t\"userNum\": 1,\n" +
                "\t\"gameMax\": "+gameMax+",\n" +
                "\t\"creater\": "+t.getId()+",\n" +
                "\t\"users\":["+JSON.toJSONString(t.getId())+"]\n" +
                "}";
        tr.setUserInfo(roomInfo);

        trm.insert(tr);
        return "{\"code\":300,\"msg\":\"创建成功\",\"data\":"+roomInfo+"}";
    }
}
