<%@ page import="com.book.eneity.Reader" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
//    System.out.println(session.getAttribute("reader").toString());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <title>用户信息</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css" media="all">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.10.0/jquery.js"></script>
    <!-- 你必须先引入jQuery1.8或以上版本 -->
    <script src="/layer/layer.js"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
    <style>
        .photo_item {
            position: relative;
            left: 100px;
        }
    </style>
</head>

<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">万书</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <!-- <li class="layui-nav-item"><a href="#">书籍管理</a></li>
    <li class="layui-nav-item"><a href="">数据分析</a></li> -->
        </ul>
        <c:if test="${reader.username==null}">
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item"><a href="login.jsp">请登录</a></li>
            </ul>
        </c:if>
        <c:if test="${reader.username!=null}">
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <c:if test="${reader.imageurl!=null}">
                            <img src="${reader.imageurl}" class="layui-nav-img">${reader.username}
                        </c:if>
                        <c:if test="${reader.imageurl==null}">
                            <img src="https://gitee.com/eastern_blood/dongxuetu/raw/master/image/20201014101729.jpg" class="layui-nav-img">${reader.username}
                        </c:if>
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="infoset.jsp">修改资料</a></dd>
                        <dd><a href="">安全设置</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="bookshow.jsp">首页</a></li>
                <li class="layui-nav-item"><a href="login.jsp">退出登录</a></li>
            </ul>
        </c:if>
    </div>
</div>
<div class="layui-body">
    <div style="height: 80px;"> </div>
    <div style="width: 440px;">
        <form class="layui-form" action="" lay-filter="example">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名:</label>
                <div class="layui-input-block">
                    <input type="text" name="username" lay-verify="title" autocomplete="off" placeholder="请输入用户名" class="layui-input" value="${reader.username}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">真实姓名:</label>
                <div class="layui-input-block">
                    <input type="text" name="name" lay-verify="title" autocomplete="off" placeholder="请输入真实姓名" class="layui-input" value="${reader.name}">
                </div>
            </div>
            <label class="layui-form-label">手机号:</label>
            <div class="layui-input-inline">
                <input type="tel" name="tel" lay-verify="required|phone" placeholder="请输入手机号" autocomplete="off" class="layui-input" value="${reader.tel}">
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别:</label>
                <div class="layui-input-block">
                    <input type="radio" name="gender" value="男" title="男" checked="">
                    <input type="radio" name="gender" value="女" title="女">
                </div>
            </div>
            <div class="photo_item">
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="test1">上传图片</button>
                    <div class="layui-upload-list">
                        <c:if test="${reader.imageurl!=null}">
                            <img src="${reader.imageurl}" style="width:100px ;height: 100px;" class="layui-upload-img" id="demo1">
                        </c:if>
                        <c:if test="${reader.imageurl==null}">
                            <img src="https://gitee.com/eastern_blood/dongxuetu/raw/master/image/20201014101729.jpg" style="width:100px ;height: 100px;" class="layui-upload-img" id="demo1">
                        </c:if>
                        <p id="demoText"></p>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" id="LAY-component-form-setval">重置</button>
<%--                    <button type="button" class="layui-btn layui-btn-normal" id="LAY-component-form-getval">取值</button>--%>
                    <button type="submit" class="layui-btn" id="testListAction" lay-submit="" lay-filter="demo1">修改</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="layui/layui.js" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function() {
        var element = layui.element;

    });
</script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    var id=${reader.id};
    console.log("id为="+id);
    layui.use('upload', function() {
        var $ = layui.jquery;
        var upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1',
            //根据id上传图片
            url: 'http://localhost:8080/user?method=updateUserPhoto&id='+id, //改成您自己的上传接口
            method: 'post' , //可选项。HTTP类型，默认post
            auto: false, //选择文件后不自动上传
            bindAction: '#ListAction' ,//指向一个按钮触发上传
            choose: function(obj){
                //将每次选择的文件追加到文件队列
                var files = obj.pushFile();
                //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                obj.preview(function(index, file, result) {
                    console.log(index); //得到文件索引
                    console.log(file); //得到文件对象
                    console.log(result); //得到文件base64编码，比如图片
                    $('#demo1').attr('src', result); //图片链接（base64）
                    $.post("http://localhost:8080/user?method=updatePhoto", {result:result,id:id}, function(res) {
                        console.log("updatePhoto请求成功");
                    }, "text");//这里用的是post提交，如果不懂可以参考JQuery中ajax提
                })
            },
            done: function(res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                //上传成功
            },
            error: function() {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #c158ff;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function() {
                    uploadInst.upload();
                });
            }
        });
    });
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form,
            layer = layui.layer,
            layedit = layui.layedit,
            laydate = layui.laydate;

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //监听提交
        form.on('submit(demo1)', function(data) {
            console.log("可以执行跳转")
            layer.confirm('真的修改资料么', function(index) {
                $.post("http://localhost:8080/login?method=denglutwo", {username:"${reader.username}",password:"${reader.password}"}, function(res) {
                    console.log("上传图片更新用户信息请求成功");
                }, "text");//这里用的是post提交，如果不懂可以参考JQuery中ajax提
                layer.close(index);
                location.href = "/infoset.jsp";
                //向服务端发送删除指令
                //删除数据库中的数据
                <%--href="/book?method=deleteFindById&id=${id}"--%>
            });
            return false;
        });

        //表单赋值
        layui.$('#LAY-component-form-setval').on('click', function() {
            form.val('example', {
                "username": "${reader.username}", // "name": "value",
                "name": "${reader.name}",
                "tel": "${reader.tel}",
                "gender": "${reader.gender}",
            });
        });

        //表单取值
        layui.$('#LAY-component-form-getval').on('click', function() {
            var data = form.val('example');
            alert(JSON.stringify(data));
        });

    });
</script>

</body>

</html>
