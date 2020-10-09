package com.book.service.impl;

import com.book.repository.ReaderRepository;
import com.book.repository.impl.ReaderRepositoryImpl;
import com.book.service.LoginService;

public class LoginServiceImpl implements LoginService {

    public ReaderRepository readerRepository=new ReaderRepositoryImpl();
    @Override
    public Object login(String username, String password) {
        return readerRepository.login(username,password);
        //返回对象
    }

    @Override
    public int registered(String username, String password,String tel) {
        return readerRepository.registered(username,password,tel);
    }

    @Override
    public int hasUsername(String username) {
        return readerRepository.hasUsername(username);
    }
}
