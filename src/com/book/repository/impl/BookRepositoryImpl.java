package com.book.repository.impl;

import com.book.eneity.Book;
import com.book.eneity.BookCase;
import com.book.eneity.Reader;
import com.book.repository.BookRepository;
import com.book.utils.JdbcTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {


    @Override
    public List<Book> findAll() {
        List<Book> list=new ArrayList<>();

        //之前定义的包装类用于c3p0连接池的使用
        Connection connection= JdbcTools.getConnection();
        String sql="select * from book,bookcase where book.bookcaseid = bookcase.id order by book.id";
        //执行sql语句
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Reader reader=null;
        try {
            statement = connection.prepareStatement(sql);
            //执行语句
            resultSet = statement.executeQuery();
            //返回结果为数组时的用法
            while (resultSet.next()) {
//                System.out.println("所有书籍="+new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(10), resultSet.getString(11),resultSet.getBytes(9)));
                list.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(11), resultSet.getString(12),resultSet.getBytes(9)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcTools.release(connection,statement,resultSet);
        }

        return list;
    }

    @Override
    public int deleteFindById(int id) {
        //之前定义的包装类用于c3p0连接池的使用
        Connection connection= JdbcTools.getConnection();
        String sql="delete from book where id=?";
        //执行sql语句
        PreparedStatement statement=null;
        try {
            if(sql!=null) {
                statement = connection.prepareStatement(sql);
                statement.setInt(1,id);
                //执行更新语句
                statement.executeUpdate();
                System.out.println("方法deleteFindById删除数据成功");
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Book findBookById(int id) {

        //之前定义的包装类用于c3p0连接池的使用
        Connection connection= JdbcTools.getConnection();
        String sql="select * from book where id=?";
        //执行查找sql语句
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Book book=null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            //执行语句
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                book=new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(7), resultSet.getString(8),resultSet.getBytes(9));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcTools.release(connection,statement,resultSet);
        }
        return book;
    }

    @Override
    public int updateOneBook(Book book) {
        //之前定义的包装类用于c3p0连接池的使用
        Connection connection= JdbcTools.getConnection();
        String sql="update book set name=?,author=?,publish=?,pages=?,price=?,bookcaseid=? where id=?;";
        //执行sql语句
        PreparedStatement statement=null;
        try {
            if(sql!=null) {
                statement = connection.prepareStatement(sql);

                statement.setString(1,book.getName());
                statement.setString(2,book.getAuthor());
                statement.setString(3,book.getPublish());
                statement.setInt(4,book.getPages());
                statement.setDouble(5,book.getPrice());
                statement.setInt(6,book.getBookcaseid());
                statement.setInt(7,book.getId());

                //执行更新语句
                statement.executeUpdate();
                System.out.println("方法updateOneBook更新数据成功");
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int addOneBook(Book book) {
        //之前定义的包装类用于c3p0连接池的使用
        Connection connection= JdbcTools.getConnection();
        String sql="insert into book(id,name,author,publish,pages,price,bookcaseid) values(?,?,?,?,?,?,?);";
        //执行sql语句
        PreparedStatement statement=null;
        try {
            if(sql!=null) {
                statement = connection.prepareStatement(sql);
                statement.setInt(1,book.getId());
                statement.setString(2,book.getName());
                statement.setString(3,book.getAuthor());
                statement.setString(4,book.getPublish());
                statement.setInt(5,book.getPages());
                statement.setDouble(6,book.getPrice());
                statement.setInt(7,book.getBookcaseid());

                //执行更新语句
                statement.executeUpdate();
                System.out.println("方法addOneBook更新数据成功");
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<BookCase> zhongLeiFind() {
        List<BookCase> list=new ArrayList<>();

        //之前定义的包装类用于c3p0连接池的使用
        Connection connection= JdbcTools.getConnection();
        String sql="SELECT count(bookcase.id),bookcase.name from book,bookcase WHERE book.bookcaseid=bookcase.id GROUP BY bookcase.id";
        //执行sql语句
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement = connection.prepareStatement(sql);
            //执行语句
            resultSet = statement.executeQuery();
            //返回结果为数组时的用法
           while(resultSet.next()) {
                list.add(new BookCase(resultSet.getInt(1), resultSet.getString(2)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcTools.release(connection,statement,resultSet);
        }

        return list;
    }

    @Override
    public List<BookCase> chuBanSheFind() {
        List<BookCase> list=new ArrayList<>();

        //之前定义的包装类用于c3p0连接池的使用
        Connection connection= JdbcTools.getConnection();
        String sql="SELECT count(book.publish),book.publish from book GROUP BY book.publish";
        //执行sql语句
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement = connection.prepareStatement(sql);
            //执行语句
            resultSet = statement.executeQuery();
            //返回结果为数组时的用法
            while(resultSet.next()) {
                list.add(new BookCase(resultSet.getInt(1), resultSet.getString(2)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcTools.release(connection,statement,resultSet);
        }

        return list;
    }
}
