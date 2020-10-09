package com.book.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTools {
    private static DataSource dataSource;//新建数据池
    static {
        dataSource=new ComboPooledDataSource("testc3p0");
        //连接池c3p0数据连接池
    }
    public static Connection getConnection(){
        Connection connection=null;

        try {
            connection=dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(connection!=null){
                connection.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
