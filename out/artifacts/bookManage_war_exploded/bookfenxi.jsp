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
    <title>万书</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
    <!-- 你必须先引入jQuery1.8或以上版本 -->

    <!-- <script src="/layer/layer.js"></script> -->
    <style>
        .search_style {
            padding-top: 10px;
            padding-left: 15px;
        }

        .leixing_tu {
            position: absolute;
            margin-top: 40px;
            margin-left: 40px;
        }

        .chubanshe_tu {
            position: absolute;
            left: 600px;
            margin-top: 40px;
            margin-left: 40px;
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
                        <img src="http://t.cn/RCzsdCq" class="layui-nav-img">${reader.username}
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
                    <li class="layui-nav-item"><a href="bookshow.jsp">所有用户</a></li>
                </c:if>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="leixing_tu">
            <div id="mainone" style="width: 600px;height:400px;"></div>
        </div>
        <div class="chubanshe_tu">
            <div id="maintwo" style="width: 600px;height:400px;"></div>
        </div>
    </div>

</div>
<script src=" https://cdn.bootcss.com/jquery/3.5.0/jquery.min.js"></script>
<script src="/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function() {
        var element = layui.element;

    });
</script>
<script type="text/javascript">
    window.onload = function() {
        var book = [];
        var publish = [];
        //这是书的种类数据分析
        $.ajax({
            // url: '/test/table/demoone.json',
            url: 'http://localhost:8080/book?method=zhongLeiFind',
            type: 'get',
            // 解决跨域
            dataType: 'json',
            success: function(res) {
                console.log("这是书的种类数据分析1", res);
                book = res;
                console.log("这是书的种类数据分析2", book);
                let new_book = book.map(obj => {
                    return obj.name
                })
                console.log("这是书的种类数据分析3", new_book);

                // 基于准备好的dom，初始化echarts实例
                var myChartone = echarts.init(document.getElementById('mainone'));

                // 指定图表的配置项和数据
                var optionone = {
                    title: {
                        text: '书类数据统计',
                        textAlign: 'auto'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b}: {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 0,
                        top: 25,
                        data: new_book
                    },
                    series: [{
                        name: '访问来源',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: book
                    }]
                };


                // 使用刚指定的配置项和数据显示图表。
                myChartone.setOption(optionone);
            },
            error: function(jqXHR) {
                console.log("Error");
            }
        });





        //这是出版社的数据分析
        $.ajax({
            // url: '/test/table/demotwo.json',
            url: 'http://localhost:8080/book?method=chuBanSheFind',
            type: 'get',
            // 解决跨域
            dataType: 'json',
            success: function(res) {
                console.log("这是出版社的数据分析1", res);
                publish = res;
                console.log("这是出版社的数据分析2", publish);
                let new_publish = publish.map(obj => {
                    return obj.name
                })
                console.log("这是出版社的数据分析3", new_publish);
                // 基于准备好的dom，初始化echarts实例
                var myCharttwo = echarts.init(document.getElementById('maintwo'));

                // 指定图表的配置项和数据
                var optiontwo = {
                    title: {
                        text: '出版社数据统计',
                        textAlign: 'auto'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b}: {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 0,
                        top: 25,
                        data: new_publish
                    },
                    series: [{
                        name: '访问来源',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: publish
                    }]
                };


                // 使用刚指定的配置项和数据显示图表。
                myCharttwo.setOption(optiontwo);
            },
            error: function(jqXHR) {
                console.log("Error");
            }
        });

    };
</script>
</body>

</html>