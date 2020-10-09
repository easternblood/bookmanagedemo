package com.book.repository;

import com.book.eneity.Reader;

import java.util.List;

public interface ReaderRepository {
    public Reader login(String username, String password);
    public int registered(String username,String password,String tel);
    public int hasUsername(String username);
    public List<Reader> findAllUser();
}
