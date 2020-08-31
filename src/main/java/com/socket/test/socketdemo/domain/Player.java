package com.socket.test.socketdemo.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Integer id;
    private String name;
    private Integer room;
    private List<Poker> pokers = new ArrayList<Poker>();//初始化列表

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public List<Poker> getPokers() {
        return pokers;
    }

    public void setPokers(List<Poker> pokers) {
        this.pokers = pokers;
    }
}
