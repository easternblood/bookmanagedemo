package com.book.service;

public interface LoginService {
    //是否能进行登录，返回对象
    public Object login(String username, String password);
    public int registered(String username,String password,String tel);
    public int hasUsername(String username);
}
