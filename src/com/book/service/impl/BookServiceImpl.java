package com.book.service.impl;

import com.book.eneity.Book;
import com.book.eneity.BookCase;
import com.book.repository.BookRepository;
import com.book.repository.impl.BookRepositoryImpl;
import com.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository=new BookRepositoryImpl();
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public int deleteFindById(int id) {
        return bookRepository.deleteFindById(id);
    }

    @Override
    public Book findBookById(int id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public int updateOneBook(Book book) {
        return bookRepository.updateOneBook(book);
    }

    @Override
    public int addOneBook(Book book) {
        return bookRepository.addOneBook(book);
    }

    @Override
    public List<BookCase> zhongLeiFind() {
        return bookRepository.zhongLeiFind();
    }

    @Override
    public List<BookCase> chuBanSheFind() {
        return bookRepository.chuBanSheFind();
    }

    @Override
    public int updatePhoto(Book book, String position) {
        return bookRepository.updatePhoto(book,position);
    }
}
