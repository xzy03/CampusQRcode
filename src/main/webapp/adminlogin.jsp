<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员登录</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image: linear-gradient(90deg, cyan, purple);
            background-size: 400%;
            animation: myanimation 10s infinite;
            /*background-size: cover;*/
        }
        .login-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            /*background: url("img/img_1.png") no-repeat center center fixed;*/
            /*opacity: 0.5;*/
        }
        .login-container h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .login-container form {
            display: flex;
            flex-direction: column;
        }
        .login-container input {
            margin-bottom: 15px;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .login-container button {
            padding: 10px;
            font-size: 16px;
            background-color: #5cb85c;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .login-container button:hover {
            background-color: #4cae4c;
        }
        .error {
            color: red;
            text-align: center;
            margin-bottom: 20px;
        }
        @keyframes myanimation {
            0% {
                background-position: 0% 50%;
            }
            50% {
                background-position: 100% 50%;
            }
            100% {
                background-position: 0% 50%;
            }
        }
    </style>
</head>
<body>
<div class="login-container">
    <h1>管理员登录</h1>
    <form action="adminLogin" method="post">
        <input type="text" name="loginName" placeholder="登录名" required>
        <input type="password" name="password" placeholder="密码" required>
        <button type="submit">登录</button>
        <div>${message}</div>
        <% if (request.getParameter("error") != null) { %>
        <div class="error">无效的登录名或密码</div>
        <% } %>
    </form>
</div>
</body>
</html>
