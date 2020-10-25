<%@ page import="com.book.eneity.Reader" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>用户管理</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script src="https://cdn.bootcdn.net/ajax/libs/jquery/1.10.0/jquery.js"></script>
    <!-- 你必须先引入jQuery1.8或以上版本 -->
    <script src="/layer/layer.js"></script>
    <style>
        .search_style {
            padding-top: 10px;
            padding-left: 15px;
        }
    </style>
</head>

<body class="layui-layout-body">



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
            <li class="layui-nav-item"><a href="login.jsp">退出登录</a></li>
        </ul>
        </c:if>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">

                <li class="layui-nav-item"><a href="bookshow.jsp">所有商品</a></li>
                <c:if test="${reader.grade==2}">
                <li class="layui-nav-item"><a href="bookfenxi.jsp">数据分析</a></li>
                <li class="layui-nav-item"><a href="user.jsp">所有用户</a></li>
                </c:if>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="search_style">
            <div class="demoTable">
                搜索ID：
                <div class="layui-inline">
                    <input class="layui-input" name="id" id="demoReload" autocomplete="off">
                </div>
                <button class="layui-btn" data-type="reload">搜索</button>
            </div>
        </div>
        <!-- 内容主体区域 -->
        <table class="layui-hide" id="testone" lay-filter="test"></table>
    </div>

</div>
<script src="/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function() {
        var element = layui.element;

    });
</script>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script>
    layui.use('table', function() {
        var table = layui.table;

        table.render({
            elem: '#testone',
            id: 'test',
            // url: '/test/demo.json',
            // url: '/test/table/demo.json',
            url: 'http://localhost:8080/user',

            parseData: function(res) { //res 即为原始返回的数据
                var result;
                // console.log(this);
                // console.log(JSON.stringify(res));
                if (this.page.curr) {
                    result = res.slice(this.limit * (this.page.curr - 1), this.limit * this.page.curr);
                } else {
                    result = res.slice(0, this.limit);
                }
                return {
                    "code": 0, //解析接口状态
                    "msg": '', //解析提示文本
                    "count": res.length, //解析数据长度
                    "data": result //解析数据列表
                };
            },
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
                //,curr: 5 //设定初始在第 5 页
                groups: 1, //只显示 1 个连续页码
                first: false, //不显示首页
                last: false, //不显示尾页
            },
            toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
            defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            title: '用户数据表',
            id: 'test',
            cols: [
                [{
                    type: 'checkbox',
                    fixed: 'left'
                }, {
                    field: 'id',
                    title: 'ID',
                    width: 80,
                    fixed: 'left',
                    unresize: true,
                    sort: true,
                    align: 'center'
                }, {
                    field: 'username',
                    title: '用户名',
                    width: 140
                }, {
                    field: 'password',
                    title: '密码',
                    width: 140
                }, {
                    field: 'name',
                    title: '真实姓名',
                    width: 110
                }, {
                    field: 'tel',
                    title: '电话号码',
                    width: 170,
                    sort: true
                }, {
                    field: 'cardid',
                    title: '借阅证号',
                    width: 111,
                    sort: true
                }, {
                    field: 'gender',
                    title: '性别',
                    width: 113
                }, {
                    field: 'grade',
                    title: '账号等级',
                    width: 90,
                    align: 'center',
                }, {
                    fixed: 'right',
                    title: '操作',
                    width: 128,
                    align: 'center',
                    toolbar: '#barDemo'
                }]
            ],
        });
        //监听工具条
        table.on('tool(test)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'detail') { //查看
                //do somehing
                console.log("查看");
                layer.msg('查看：' + JSON.stringify(data));
            } else if (layEvent === 'del') { //删除
                console.log("删除")
                console.log(data);
                layer.confirm('真的删除行么', function(index) {
                    $.ajax({
                        type: "GET",
                        dataType: "text",
                        url: "http://localhost:8080/user?method=deleteFindById",
                        data: {
                            id: data.id
                        },
                        success: function(data) {
                            console.log("deleteFindById请求成功");
                            // location.href = "/bookshow.jsp";
                        }
                    });
                    // location.href = "/book?method=deleteFindById&id="+data.id;
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                    //删除数据库中的数据
                    // <%--href="/book?method=deleteFindById&id=${id}"--%>
                });
            } else if (layEvent === 'edit') { //编辑

                //do something
                console.log("编辑")
                var reader = data;
                console.log('用户为：', reader);

                layer.open({
                    type: 2,
                    area: ['700px', '450px'],
                    fixed: false, //不固定
                    maxmin: true,
                    content: '/user?method=findUserById&id=' + data.id
                });
                //同步更新缓存对应的值
                obj.update({

                });
            } else if (layEvent === 'LAYTABLE_TIPS') {
                layer.alert('Hi，头部工具栏扩展的右侧图标。');
            }
        });
        //头工具栏事件
        table.on('toolbar(test)', function(obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选');
                    break;
                case 'add':
                    layer.msg('添加');
                    layer.open({
                        type: 2,
                        area: ['700px', '450px'],
                        fixed: false, //不固定
                        maxmin: true,
                        content: '/adduser.jsp'
                    });
                    break;
                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });
        //搜索
        var $ = layui.$,
            active = {
                reload: function() {
                    var demoReload = $('#demoReload');
                    console.log("demoReload.val()的值为", demoReload.val())
                    //执行重载
                    table.reload('test', {
                        url: 'http://localhost:8080/user',
                        page: {
                            curr: 1 //重新从第 1 页开始
                        },
                        where: {
                            id: demoReload.val()
                        }
                    });
                }
            };

        $('.demoTable .layui-btn').on('click', function() {
            var type = $(this).data('type');
            console.log("$('.demoTable .layui-btn').on('click', function(){这个语句执行")
            active[type] ? active[type].call(this) : '';
        });
    });
</script>

</body>

</html>