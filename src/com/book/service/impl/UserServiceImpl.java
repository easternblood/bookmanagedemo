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
}
