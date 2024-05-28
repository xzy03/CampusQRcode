<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2024/5/27
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.mytest.QRCodeGenerator" %>
<%@ page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>生成二维码</title>
</head>
<body>
<%
    // 生成二维码的表单数据
    HashMap<String, String> formData = new HashMap<>();
    formData.put("name", "张三");
    formData.put("age", "25");

    // 生成二维码并显示在页面上
    String qrCodePath = request.getContextPath() + "/qrcode.png";
    QRCodeGenerator.generateQRCode(formData);
    System.out.println(qrCodePath);
%>
<%--<img src="<%= qrCodePath %>" alt="二维码">--%>
</body>
</html>

