<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>编辑</title>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.10.0/jquery.js"></script>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <style>
        body {
            padding: 20px;
        }
        .photo_item {
            position: relative;
            left: 110px;
        }
    </style>
</head>

<body>
<%
    // 取出request域的值
    Object book=request.getAttribute("book");
%>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">ID：</label>
        <div class="layui-input-block">
            <input type="text" name="id" lay-verify="title" autocomplete="off" placeholder="${book.id}" class="layui-input" value="${book.id}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">书名：</label>
        <div class="layui-input-block">
            <input type="text" name="name" lay-verify="title" autocomplete="off" placeholder="${book.name}" class="layui-input" value="${book.name}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">作者：</label>
        <div class="layui-input-block">
            <input type="text" name="author" lay-verify="title" autocomplete="off" placeholder="${book.author}" class="layui-input" value="${book.author}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">出版社：</label>
        <div class="layui-input-block">
            <input type="text" name="publish" lay-verify="title" autocomplete="off" placeholder="${book.publish}" class="layui-input" value="${book.publish}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">页数：</label>
        <div class="layui-input-block">
            <input type="text" name="pages" lay-verify="title" autocomplete="off" placeholder="${book.pages}" class="layui-input" value="${book.pages}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">价格：</label>
        <div class="layui-input-block">
            <input type="text" name="price" lay-verify="title" autocomplete="off" placeholder="${book.price}" class="layui-input" value="${book.price}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">类型：</label>
        <div class="layui-input-block">
            <input type="text" name="bookcaseid" lay-verify="title" autocomplete="off" placeholder="${book.bookcaseid}" class="layui-input" value="${book.bookcaseid}">
        </div>
    </div>
    <div class="photo_item">
        <div class="layui-upload">
            <button type="button" class="layui-btn" id="test1">上传图片</button>
            <div class="layui-upload-list">
                <c:if test="${book.bookimageurl!=null}">
                    <img src="${book.bookimageurl}" style="width:100px ;height: 100px;" class="layui-upload-img" id="demo1">
                </c:if>
                <c:if test="${book.bookimageurl==null}">
                    <img src="https://gitee.com/eastern_blood/dongxuetu/raw/master/image/20201014101729.jpg" style="width:100px ;height: 100px;" class="layui-upload-img" id="demo1">
                </c:if>
                <p id="demoText"></p>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" id="testListAction" class="layui-btn" lay-submit="" lay-filter="demo1">编辑</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script src="/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    var id=${book.id};
    console.log("id为="+id);
    layui.use('upload', function() {
        var $ = layui.jquery;
        var upload = layui.upload;

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test1',
            //根据id上传图片
            url: 'http://localhost:8080/book?method=updateBookPhoto&id='+id, //改成您自己的上传接口
            method: 'post' , //可选项。HTTP类型，默认post
            auto: false, //选择文件后不自动上传
            bindAction: '#testListAction' ,//指向一个按钮触发上传
            choose: function(obj){
                //将每次选择的文件追加到文件队列
                var files = obj.pushFile();
                //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
                obj.preview(function(index, file, result) {
                    console.log(index); //得到文件索引
                    console.log(file); //得到文件对象
                    console.log(result); //得到文件base64编码，比如图片
                    $('#demo1').attr('src', result); //图片链接（base64）
                    $.post("http://localhost:8080/book?method=updatePhoto", {result:result,id:id}, function(res) {
                        console.log("bookupdatePhoto请求成功");
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

        //自定义验证规则
        form.verify({

        });
        //监听提交
        form.on('submit(demo1)', function(data) {
            var book=data.field;
            console.log("在编辑里的数据",book);
            layer.confirm('真的修改行么', function(index) {
                console.log("确认修改")
                layer.close(index);
                // $.post("http://localhost:8080/book?method=updateOneBook", {bookid:id}, function(res) {
                //     console.log("updateOneBook请求成功");
                    window.parent.location.reload();
                    parent.layer.close(index);
                // }, "text");//这里用的是post提交，如果不懂可以参考JQuery中ajax提
                //执行数据修改
                // $.ajax({
                //     type : "POST",
                //     dataType : "json",
                //     url: "http://localhost:8080/book?method=updateOneBook",
                //     data : {book:JSON.stringify(book)},
                //     success : function(data) {
                //
                //     }
                // });
                // location.href = "/bookshow.jsp";
            });
            return false;
        });
    });
</script>

</body>

</html>
</body>

</html>