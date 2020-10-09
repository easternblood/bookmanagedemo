package com.book.repository;

import com.book.eneity.Book;
import com.book.eneity.BookCase;

import java.util.List;

public interface BookRepository {
    public List<Book> findAll();
    public int deleteFindById(int id);
    public Book  findBookById(int id);
    public int updateOneBook(Book book);
    public int addOneBook(Book book);
    public List<BookCase> zhongLeiFind();
    public List<BookCase> chuBanSheFind();
}
