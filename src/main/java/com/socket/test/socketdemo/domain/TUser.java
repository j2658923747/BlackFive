package com.socket.test.socketdemo.domain;

public class TUser {
    private Integer id;

    private String username;

    private String password;

    private String token;

    private Integer gold;

    public TUser(){};
    public TUser(String username,String password,String token,Integer gold){
        this.username=username;
        this.password=password;
        this.token=token;
        this.gold=gold;
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return "TUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", gold=" + gold +
                '}';
    }
}