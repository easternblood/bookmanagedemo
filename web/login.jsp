<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录</title>
    <style>
        * {
            padding: 0;
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            letter-spacing: .05em;
        }

        html {
            height: 100%;
        }

        body {
            height: 100%;
        }

        .container {
            height: 100%;
            /* 设置渐变色 */
            background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
        }

        .login-wrapper {
            background-color: #fff;
            width: 250px;
            height: 500px;
            position: relative;
            padding: 0 50px;
            border-radius: 15px;
            /* 设置框的居中位置也可以采用flex布局方式 */
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }

        .login-wrapper .header {
            font-size: 30px;
            font-weight: bold;
            text-align: center;
            line-height: 200px;
        }

        .login-wrapper .form-wrapper .input-item {
            display: block;
            width: 100%;
            margin-bottom: 20px;
            border: 0;
            padding: 10px;
            border-bottom: 1px solid rgb(128, 125, 125);
            font-size: 15px;
            outline: none;
        }

        .login-wrapper .form-wrapper .input-item ::placeholder {
            text-transform: uppercase;
        }

        .login-wrapper .form-wrapper .btn {
            text-align: center;
            padding: 10px;
            margin-top: 40px;
            width: 100%;
            background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
            color: #fff;
        }

        .login-wrapper .msg {
            text-align: center;
            line-height: 80px;
        }

        .login-wrapper .msg a {
            /* 消除下划线 */
            text-decoration: none;
            text-decoration-color: none;
            color: #a6c1ee;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="login-wrapper">
        <div class="header">Login</div>
        <div class="form-wrapper">
            <form action="/login?method=denglu" method="post" id="loginForm">
                <input type="text" name="username" placeholder="username" class="input-item">
                <input type="password" name="password" placeholder="password" class="input-item">
                <input class="btn" type="submit" value="登录"/>
            </form>
        </div>
        <div class="msg">
            Don't have account?<a href='zhece.jsp'>Sign up</a>
        </div>
    </div>
</div>
</body>

</html>