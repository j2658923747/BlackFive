package com.socket.test.socketdemo.utils;

import com.socket.test.socketdemo.domain.TUser;

import java.util.Random;

public class UTIL {
    public static boolean isLetterDigit(String str) {
        String regex = "^[a-z0-9A-Z]+$";
        return str.matches(regex);
    }
    public static TUser isSQL(TUser t){
        String str;
        str=t.getUsername();
        if (str==null){
            return t;
        }
        if (isLetterDigit(str)==false){
            t.setUsername(null);
        }
        str=t.getPassword();
        if (str==null){
            return t;
        }
        if (isLetterDigit(str)==false){
            t.setPassword(null);
        }
        return t;
    }
    public static Integer getRandomNum(){
        Random r = new Random();
        Integer ran = r.nextInt(899990)+100001;
        return ran;
    }
}
