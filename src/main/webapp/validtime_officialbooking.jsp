<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2024/6/9
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pass Page</title>
    <link rel="stylesheet" href="styles1.css">
</head>
<body>
<div class="container">
    <header class="header">
        <img src="img/校徽.jpg" alt="University Logo" class="logo">
        <span class="university-name">浙江工业大学</span>
    </header>
    <div class="pass-info">
        <h1>${queryofficialbooking.getName()} 通行码</h1>
        <p id="time" class="time"></p>
        <div class="qr-code">
            <img src="${queryofficialbooking.getQRcode()}" alt="QR Code" class="qr-image">
        </div>
        <p class="valid-time">有效时间至：${queryofficialbooking.getOuttime()}</p>
        <p class="note">公务预约</p>
        <p class="note">凭此码或身份证进校，并服从学校相关管理规定。</p>
    </div>
</div>
<script src="script.js"></script>
</body>
</html>
