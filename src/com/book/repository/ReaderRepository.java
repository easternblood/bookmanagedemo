package com.book.repository;

import com.book.eneity.Reader;

import java.util.List;

public interface ReaderRepository {
    public Reader login(String username, String password);
    public int registered(String username,String password,String tel);
    public int hasUsername(String username);
    public List<Reader> findAllUser();
    public Reader findUserById(int id);
    public int deleteFindById(int id);
    public int updateOneUser(Reader reader);
    public int addOneUser(Reader reader);
    public int updatePhoto(Reader reader,String position);
}
