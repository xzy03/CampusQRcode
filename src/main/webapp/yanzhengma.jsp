<%--
  Created by IntelliJ IDEA.
  User: 69472
  Date: 2024/5/31
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查询页面</title>
    <link rel="stylesheet" href="styles2.css">
</head>
<body>
<form action="QueryMybookingServlet" method="post">
<div class="container">
    <header class="header">
        <h1>查询页面</h1>
    </header>
    <div class="form-container">
        <div class="input-group">
            <label for="name">姓名：</label>
            <input type="text" id="name" name="name" placeholder="请输入姓名">
        </div>
        <div class="input-group">
            <label for="id-card">身份证：</label>
            <input type="text" id="id-card" name="id-card" placeholder="请输入身份证号码">
        </div>
        <div class="input-group">
            <label for="phone">电话号码：</label>
            <input type="text" id="phone" name="phone" placeholder="请输入电话号码">
        </div>
        <input class="btn" type="submit" value="查询">

        <p>${message}</p>
    </div>
</div>
</form>

</body>
</html>