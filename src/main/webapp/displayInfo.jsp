<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2024/5/27
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>显示信息</title>
</head>
<body>
<%-- 获取并显示用户提交的信息 --%>
<h2>用户信息：</h2>
<p>校区：<%= request.getParameter("pf") %></p>
<p>入校时间：<%= request.getParameter("intime") %></p>
</body>
</html>

