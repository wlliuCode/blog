package com.wlliu.blog.service.user.service.impl;

public class Fallback {
    public static String fallback(Throwable e){
        return "fallback"+e.toString();
    }
}
