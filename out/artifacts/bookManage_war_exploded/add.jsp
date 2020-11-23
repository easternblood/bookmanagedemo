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
    <title>添加</title>
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.10.0/jquery.js"></script>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <style>
        body {
            padding: 20px;
        }

    </style>
</head>

<body>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">ID：</label>
        <div class="layui-input-block">
            <input type="text" name="id" lay-verify="title" autocomplete="off" placeholder="请输入ID" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">书名：</label>
        <div class="layui-input-block">
            <input type="text" name="name" lay-verify="title" autocomplete="off" placeholder="请输入书名" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">作者：</label>
        <div class="layui-input-block">
            <input type="text" name="author" lay-verify="title" autocomplete="off" placeholder="请输入作者" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">出版社：</label>
        <div class="layui-input-block">
            <input type="text" name="publish" lay-verify="title" autocomplete="off" placeholder="请输入出版社" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">页数：</label>
        <div class="layui-input-block">
            <input type="text" name="pages" lay-verify="title" autocomplete="off" placeholder="请输入页数" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">价格：</label>
        <div class="layui-input-block">
            <input type="text" name="price" lay-verify="title" autocomplete="off" placeholder="请输入价格" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">类型：</label>
        <div class="layui-input-block">
            <input type="text" name="bookcaseid" lay-verify="title" autocomplete="off" placeholder="请输入类型" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">添加</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script src="/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form,
            layer = layui.layer,
            layedit = layui.layedit,
            laydate = layui.laydate;

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            content: function (value) {
                layedit.sync(editIndex);
            }
        });

        //监听提交
        form.on('submit(demo1)', function (data) {
            var book = data.field;
            console.log("编辑后新添加的数据：", data.field)
            layer.confirm('真的添加行么', function (index) {
                console.log("确认添加")
                layer.close(index);
                $.post("http://localhost:8080/book?method=addOneBook", {book: JSON.stringify(book)}, function (res) {
                    console.log("addOneBook请求成功");
                    window.parent.location.reload();
                    parent.layer.close(index);
                }, "text");//这里用的是post提交，如果不懂可以参考JQuery中ajax提
            });
            return false;
        });
    });
</script>

</body>

</html>
</body>

</html>