package com.book.controller;

import com.book.eneity.Reader;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.utils.PhotoUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user")
public class UserServlet  extends HttpServlet {
    private UserService userService=new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前book路由下的方法
        String method = req.getParameter("method");
        System.out.println("UserServlet中get使用方法为：" + method);
        Gson gson = new Gson();
        String json="";
        //如果为空默认使用findAll方法
        if (method == null) {
            method = "findAllUser";
        }
        switch (method) {
            case "findAllUser":
                // super.doGet(req, resp);
                String id = req.getParameter("id");
                System.out.println(id);
                if(id == "" || id == null){
                    List<Reader> list = userService.findAllUser();
                    json = gson.toJson(list);
                }
                else {
                    Reader reader = userService.findUserById(Integer.parseInt(id));
                    if(reader == null){
                        List<Reader> list = new ArrayList<>();
                        json = gson.toJson(list);
                    }
                    else {
                        List<Reader> list = new ArrayList<>();
                        list.add(reader);
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
                System.out.println("UserServlet中使用方法deleteFindById获取参数为：" + id1);
                if (userService.deleteFindById(id1) == 1) {
                    //请求转发，服务器内部
                    req.getRequestDispatcher("/user.jsp").forward(req, resp);
                    //重定向，可用于服务器外部
                    // resp.sendRedirect("/bookshow.jsp");
                }
                break;
            case "findUserById":
                //获取数据域中的参数名称为id的数据
                Integer userid = Integer.parseInt(req.getParameter("id"));
                System.out.println("UserServlet中使用方法findBookById获取参数为：" + userid);
                Reader reader = userService.findUserById(userid);
                // System.out.println(book);
                //存储值到req域
                req.setAttribute("reader", reader);
                req.getRequestDispatcher("/edituser.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前book路由下的方法
        String method = req.getParameter("method");
        System.out.println("UserServlet中post使用方法为：" + method);
        Gson gson = new Gson();
        String json="";
        switch (method) {
            case "updateOneUser":
                // super.doPost(req, resp);
                // System.out.println("数据更新开始");

                // System.out.println("获取到的字符串"+req.getParameter("book").toString());
                String reader = req.getParameter("reader").toString();
                System.out.println("未改变的reader="+reader);
                Reader reader2 = gson.fromJson(reader, Reader.class);//对于javabean直接给出class实例
                System.out.println("改变了的reader2="+reader2.toString());

                // System.out.println("updateOneBook更新数据为" + book1);
                if (userService.updateOneUser(reader2) == 1) {
                    System.out.println("updateOneBook数据更新成功");
                }
                break;
            case "addOneUser":

                String reader3 = req.getParameter("reader").toString();
                System.out.println("未改变的reader3="+reader3);
                Reader reader4 = gson.fromJson(reader3, Reader.class);//对于javabean直e接给出class实例
                System.out.println("改变了的reader4="+reader4.toString());
                if (userService.addOneUser(reader4) == 1) {
                    System.out.println("addOneBook数据更新成功");
                }
                break;
//        super.doPost(req, resp);
            case "updateUserPhoto":
                // super.doPost(req, resp);
                // System.out.println("数据更新开始");

                // System.out.println("获取到的字符串"+req.getParameter("book").toString());
                //获取数据域中的参数名称为id的数据,获取到图片地址，然后去返回格式
                //返回数据格式为：
                //{
                //  "code": 0
                //  ,"msg": ""
                //  ,"data": {
                //    "src": "http://cdn.layui.com/123.jpg"
                //  }
                //}
                Integer userid = Integer.parseInt(req.getParameter("id"));
                System.out.println("UserServlet中使用方法updateUserPhoto获取参数为：" + userid);
                Reader readerone = userService.findUserById(userid);
//                System.out.println("未改变的reader3="+readerone);
                String readerjson=gson.toJson(readerone);
                Map map = new HashMap();
                map.put("code", "0");
                map.put("msg", "");
                map.put("data", readerjson);
                json = gson.toJson(map);
//                System.out.println(json);
                resp.setContentType("text/plain");
                resp.setCharacterEncoding("gb2312");
                PrintWriter out = new PrintWriter(resp.getOutputStream());
                out.print(json);
                out.flush();//把内存中的数据刷写到硬盘
                break;
            case "updatePhoto":
                // super.doPost(req, resp);
                //这里存放图片到数据库里
                String basedata=req.getParameter("result");
                Integer useridtwo = Integer.parseInt(req.getParameter("id"));
                System.out.println("UserServlet中使用方法updatePhoto获取数据为：" + basedata);
                String position=PhotoUtils.GenerateImage(basedata,"reader");
                //通过id存储地址
                Reader readertwo = userService.findUserById(useridtwo);
                //传递两个参数去取代返回值为是否成功保存地址
                if (userService.updatePhoto(readertwo,position) == 1) {
                    System.out.println("updatePhoto数据更新成功");
                }
                break;
        }
    }
}
