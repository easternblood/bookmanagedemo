package com.book.service;

import com.book.eneity.Reader;

import java.util.List;

public interface UserService {
    public List<Reader> findAllUser();
    public Reader findUserById(int id);
    public int deleteFindById(int id);
    public int updateOneUser(Reader reader);
    public int addOneUser(Reader reader);
    public int updatePhoto(Reader reader,String position);
}
