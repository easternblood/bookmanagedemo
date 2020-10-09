package com.book.controller;

import com.book.eneity.Book;
import com.book.eneity.BookCase;
import com.book.service.BookService;
import com.book.service.impl.BookServiceImpl;
import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前book路由下的方法
        String method = req.getParameter("method");
        System.out.println("BookServlet中使用方法为：" + method);
        Gson gson = new Gson();
        //如果为空默认使用findAll方法
        if (method == null) {
            method = "findAll";
        }
        switch (method) {
            case "findAll":
                // super.doGet(req, resp);
                String json;
                String id = req.getParameter("id");
                System.out.println(id);
                if(id == "" || id == null){
                    List<Book> list = bookService.findAll();
                    json = gson.toJson(list);
                }
                else {
                    Book book = bookService.findBookById(Integer.parseInt(id));
                    if(book == null){
                        List<Book> list = new ArrayList<>();
                        json = gson.toJson(list);
                    }
                    else {
                        List<Book> list = new ArrayList<>();
                        list.add(book);
                        json = gson.toJson(list);
                    }
                }

                // 输出到界面
                // System.out.println(json);
                resp.setContentType("text/plain");
                resp.setCharacterEncoding("gb2312");
                PrintWriter out = new PrintWriter(resp.getOutputStream());
                out.print(json);
                out.flush();//把内存中的数据刷写到硬盘
                break;
            case "deleteFindById":
                //获取数据域中的参数名称为id的数据
                Integer id1 = Integer.parseInt(req.getParameter("id").toString());
                System.out.println("BookServlet中使用方法deleteFindById获取参数为：" + id1);
                if (bookService.deleteFindById(id1) == 1) {

                    //请求转发，服务器内部
                    req.getRequestDispatcher("/bookshow.jsp").forward(req, resp);
                    //重定向，可用于服务器外部
                    // resp.sendRedirect("/bookshow.jsp");
                }
                break;
            case "findBookById":
                //获取数据域中的参数名称为id的数据
                Integer bookid = Integer.parseInt(req.getParameter("id"));
                System.out.println("BookServlet中使用方法findBookById获取参数为：" + bookid);
                Book book = bookService.findBookById(bookid);
                // System.out.println(book);
                //存储值到req域
                req.setAttribute("book", book);
                req.getRequestDispatcher("/edit.jsp").forward(req, resp);
                break;
            case "zhongLeiFind":
                List<BookCase> list= bookService.zhongLeiFind();
//                System.out.println("list="+list);
                json = gson.toJson(list);
                // 输出到界面
                // System.out.println(json);
                resp.setContentType("text/plain");
                resp.setCharacterEncoding("gb2312");
                PrintWriter outone = new PrintWriter(resp.getOutputStream());
                outone.print(json);
                outone.flush();//把内存中的数据刷写到硬盘
                //存储值到req域
                break;
            case "chuBanSheFind":
                List<BookCase> listone= bookService.chuBanSheFind();
//                 System.out.println("listone="+listone);
                json = gson.toJson(listone);
                // 输出到界面
                // System.out.println(json);
                resp.setContentType("text/plain");
                resp.setCharacterEncoding("gb2312");
                PrintWriter outtwo = new PrintWriter(resp.getOutputStream());
                outtwo.print(json);
                outtwo.flush();//把内存中的数据刷写到硬盘
                //存储值到req域
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前book路由下的方法
        String method = req.getParameter("method");
        System.out.println("BookServlet中使用方法为：" + method);
        Gson gson = new Gson();
        switch (method) {
            case "updateOneBook":
                // super.doPost(req, resp);
                // System.out.println("数据更新开始");

                // System.out.println("获取到的字符串"+req.getParameter("book").toString());
                String book2=req.getParameter("book").toString();
                // System.out.println("未改变的"+book2);
                Book book1 = gson.fromJson(book2, Book.class);//对于javabean直接给出class实例
                // System.out.println("改变了的"+book1.toString());

                // System.out.println("updateOneBook更新数据为" + book1);
                if (bookService.updateOneBook(book1) == 1) {
                    System.out.println("updateOneBook数据更新成功");
                }
                break;
            case "addOneBook":

                String book3=req.getParameter("book").toString();
                Book book4 = gson.fromJson(book3, Book.class);//对于javabean直e接给出class实例
                if (bookService.addOneBook(book4) == 1) {
                    System.out.println("addOneBook数据更新成功");
                }
                break;
        }
    }
}
