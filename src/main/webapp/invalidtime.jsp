<%--
  Created by IntelliJ IDEA.
  User: 69472
  Date: 2024/5/30
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pass Page</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
    <header class="header">
        <img src="img/校徽.jpg" alt="University Logo" class="logo">
        <span class="university-name">浙江工业大学</span>
    </header>
    <div class="pass-info">
        <h1>${querymybooking.getPerson().getName()}通行码</h1>
        <p id="time" class="time"></p>
        <div class="qr-code">
            <div class="qr-overlay">
                <div class="ban-icon" style="background-image: url('${querymybooking.getInvalidQRcode()}');">
                    <!-- 如果你还需要在这里显示一个透明的占位符或内容，可以添加 -->
                </div>
            </div>
        </div>
        <button class="refresh-button" onclick="updateTime()">刷新</button>
        <p class="note">当前时间不在有效预约时间内，暂不可进校</p>
    </div>
</div>
<script src="script.js"></script>
</body>
</html>
