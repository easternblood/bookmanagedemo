package com.book.controller;

import com.book.eneity.Reader;
import com.book.service.LoginService;
import com.book.service.impl.LoginServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService=new LoginServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取浏览器参数
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String tel=req.getParameter("tel");
        //获取当前login路由下的方法
        String method = req.getParameter("method");
        System.out.println("LoginServlet中使用方法为：" + method);
        switch (method){
            case "denglu":
                //用向上转型来保存
                Object object=loginService.login(username,password);
                if(object!=null) {
                    //向下转型保存
                    Reader reader = (Reader) object;
//                    System.out.println("LoginServlet的reader="+reader);
                    // 1.获取session
                    HttpSession session = req.getSession();
                    //保存数据到服务器
                    session.setAttribute("reader", reader);
                    //这里判断用户级别，来进入不同页面
                    switch (reader.getGrade()) {
                        case 1:
                            resp.sendRedirect("/bookshow.jsp");
                            System.out.println("111111111111111111");
                            break;
                        case 2:
                            resp.sendRedirect("/bookshow.jsp");
                            System.out.println("22222222222222222222222222");
                            break;
                        case 0:
                            resp.sendRedirect("/bookshow.jsp");
                            System.out.println("0000000000000000000000");
                            break;
                    }
                }
                break;
            case "denglutwo":
                //用向上转型来保存
                Object object1=loginService.login(username,password);
                if(object1!=null) {
                    //向下转型保存
                    Reader reader = (Reader) object1;
                    System.out.println("denglutwo LoginServlet的reader="+reader);
                    // 1.获取session
                    HttpSession session = req.getSession();
                    //保存数据到服务器
                    session.setAttribute("reader", reader);
                    resp.sendRedirect("/infoset.jsp");
                }
                break;
            case "zhuce":
                //判断是否有此用户名
                int panduan=loginService.hasUsername(username);
                if(panduan==1){
                    System.out.println("已有账号");
                }else{
                    //无用户
                    //用向上转型来保存
                    int i=loginService.registered(username,password,tel);
                    if(i==1){
                        System.out.println("注册账号成功");
                        resp.sendRedirect("/login.jsp");
                    }else{
                        System.out.println("注册账号失败");
                    }
                }
                break;
        }
    }
}
