<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="system.SM3Util" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>密码加密测试</title>
</head>
<body>
<h1>密码加密测试</h1>

<form method="post">
  <label for="password">请输入密码：</label>
  <input type="password" id="password" name="password" required>
  <button type="submit">加密并登录</button>
</form>

<%-- 加密密码并输出 --%>
<% String password = request.getParameter("password"); %>
<% if (password != null && !password.isEmpty()) { %>
<p>原始密码： <%= password %></p>
<p>加密后密码： <%= SM3Util.encrypt(password) %></p>
<% } %>
</body>
</html>
