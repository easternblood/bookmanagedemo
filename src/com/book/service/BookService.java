package com.book.service;

import com.book.eneity.Book;
import com.book.eneity.BookCase;

import java.util.List;

public interface BookService {
    //返回所有数据库书的数据数组
    public List<Book> findAll();
    //删除并返回是否删除
    public int deleteFindById(int id);
    //根据id查找一本书的数据
    public Book findBookById(int id);
    public int updateOneBook(Book book);
    public int addOneBook(Book book);
    public List<BookCase> zhongLeiFind();
    public List<BookCase> chuBanSheFind();
    public int updatePhoto(Book book,String position);
}
