package com.book.service.impl;

import com.book.eneity.Reader;
import com.book.repository.ReaderRepository;
import com.book.repository.impl.ReaderRepositoryImpl;
import com.book.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    public ReaderRepository readerRepository=new ReaderRepositoryImpl();
    @Override
    public List<Reader> findAllUser() {
        return readerRepository.findAllUser();
    }

    @Override
    public Reader findUserById(int id) {
        return readerRepository.findUserById(id);
    }

    @Override
    public int deleteFindById(int id) {
        return readerRepository.deleteFindById(id);
    }

    @Override
    public int updateOneUser(Reader reader) {
        return  readerRepository.updateOneUser(reader);
    }

    @Override
    public int addOneUser(Reader reader) {
        return  readerRepository.addOneUser(reader);
    }

    @Override
    public int updatePhoto(Reader reader, String position) {
        return  readerRepository.updatePhoto(reader,position);
    }
}
