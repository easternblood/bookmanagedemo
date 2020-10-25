package com.book.repository.impl;

import com.book.eneity.Reader;
import com.book.repository.ReaderRepository;
import com.book.utils.JdbcTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReaderRepositoryImpl implements ReaderRepository {
    Reader reader = null;
    @Override
    public Reader login(String username, String password) {
        //之前定义的包装类用于c3p0连接池的使用
        Connection connection = JdbcTools.getConnection();
        String sql = "select * from reader where username=? and password=?";
        //执行sql语句
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Reader reader = null;

        try {
            statement = connection.prepareStatement(sql);
            //参数代替问号
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                //单个数据的替代,
                reader = new Reader(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8),resultSet.getString(10));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcTools.release(connection, statement, resultSet);
        }
        return reader;
    }

    @Override
    public int registered(String username, String password, String tel) {
        //之前定义的包装类用于c3p0连接池的使用
        Connection connection = JdbcTools.getConnection();
        String sql = "insert into reader(username,password,tel) values(?,?,?);";
        //执行sql语句
        PreparedStatement statement = null;
        try {
            if (sql != null) {
                statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, tel);
                //执行更新语句
                statement.executeUpdate();
                System.out.println("方法Registered更新数据成功");
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int hasUsername(String username) {
        //之前定义的包装类用于c3p0连接池的使用
        Connection connection = JdbcTools.getConnection();
        String sql = "select * from reader where username=?";
        //执行sql语句
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            //参数代替问号
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                //单个数据的替代
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcTools.release(connection, statement, resultSet);
        }
        return 0;
    }

    @Override
    public List<Reader> findAllUser() {
        List<Reader> list = new ArrayList<>();

        //之前定义的包装类用于c3p0连接池的使用
        Connection connection = JdbcTools.getConnection();
        String sql = "select * from reader";
        //执行sql语句
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            //执行语句
            resultSet = statement.executeQuery();
            //返回结果为数组时的用法
            while (resultSet.next()) {
                list.add(new Reader(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8),resultSet.getString(9)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcTools.release(connection, statement, resultSet);
        }

        return list;
    }

    @Override
    public Reader findUserById(int id) {
        //之前定义的包装类用于c3p0连接池的使用
        Connection connection = JdbcTools.getConnection();
        String sql = "select * from reader where id=?";
        //执行sql语句
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Reader reader = null;

        try {
            statement = connection.prepareStatement(sql);
            //参数代替问号
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                //单个数据的替代,
                reader = new Reader(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getInt(8), resultSet.getString(10));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcTools.release(connection, statement, resultSet);
        }
        return reader;
    }

    @Override
    public int deleteFindById(int id) {
        Connection connection = JdbcTools.getConnection();
        String sql = "delete from reader where id=?";
        //执行sql语句
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Reader reader = null;

        try {
            statement = connection.prepareStatement(sql);
            //参数代替问号
            statement.setInt(1,id);
            if (statement.executeUpdate()==1) {
                //单个数据的替代
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcTools.release(connection, statement, resultSet);
        }
        return 0;
    }

    @Override
    public int updateOneUser(Reader reader) {
        //之前定义的包装类用于c3p0连接池的使用
        Connection connection= JdbcTools.getConnection();
        String sql="update reader set id=?,username=?,password=?,name=?,tel=?,cardid=?,gender=?,grade=? where id=?;";
        //执行sql语句
        PreparedStatement statement=null;
        try {
            if(sql!=null) {
                statement = connection.prepareStatement(sql);

                statement.setInt(1,reader.getId());
                statement.setString(2,reader.getUsername());
                statement.setString(3,reader.getPassword());
                statement.setString(4,reader.getName());
                statement.setString(5,reader.getTel());
                statement.setString(6,reader.getCardid());
                statement.setString(7,reader.getGender());
                statement.setInt(8,reader.getGrade());
                statement.setInt(9,reader.getId());

                if (statement.executeUpdate()==1) {
                    //单个数据的替代
                    System.out.println("方法updateOneUser更新数据成功");
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int addOneUser(Reader reader) {
        //之前定义的包装类用于c3p0连接池的使用
        Connection connection= JdbcTools.getConnection();
        String sql="insert into reader(id,username,password,name,tel,cardid,gender,grade) values(?,?,?,?,?,?,?,?);";
        //执行sql语句
        PreparedStatement statement=null;
        try {
            if(sql!=null) {
                statement = connection.prepareStatement(sql);

                statement.setInt(1,reader.getId());
                statement.setString(2,reader.getUsername());
                statement.setString(3,reader.getPassword());
                statement.setString(4,reader.getName());
                statement.setString(5,reader.getTel());
                statement.setString(6,reader.getCardid());
                statement.setString(7,reader.getGender());
                statement.setInt(8,reader.getGrade());


                if (statement.executeUpdate()==1) {
                    //单个数据的替代
                    System.out.println("方法updateOneUser更新数据成功");
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updatePhoto(Reader reader, String position) {
        //之前定义的包装类用于c3p0连接池的使用
        Connection connection= JdbcTools.getConnection();
        String sql="update reader set imageurl=? where id=?;";
        //执行sql语句
        PreparedStatement statement=null;
        try {
            if(sql!=null) {
                statement = connection.prepareStatement(sql);


                statement.setString(1,position);
                statement.setInt(2,reader.getId());

                if (statement.executeUpdate()==1) {
                    //单个数据的替代
                    System.out.println("方法updatePhoto更新数据成功");
                    return 1;
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
