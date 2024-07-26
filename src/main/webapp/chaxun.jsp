<%@ page import="system.SM4Util" %>
<%--
  Created by IntelliJ IDEA.
  User: 章鱼哥
  Date: 2024/6/5
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>预约列表</title>
    <link rel="stylesheet" href="styles3.css">
</head>
<body>
<div class="container">
    <div class="booking-list" id="mybookings">

        <c:forEach var="booking" items="${mybookings}">
            <div class="booking-item">
                <div class="avatar">社会</div>
                <div class="details">
                    <p class="name"> 姓名：${booking.name}</p>
                    <p class="intime">进时间：${booking.intime}</p>
                    <p class="campus">院校：${booking.campus}</p>
                </div>
                <div class="arrow">&#9654;</div>
            </div>

            <div class="extra-details" style="display:none;">
                <p>姓名: ${booking.name}</p>
                <p>身份证: ${booking.id}</p>
                <p>电话号码: ${booking.phoneNumber}</p>
                <p>校区: ${booking.campus}</p>
                <p>进入时间: ${booking.intime}</p>
                <p>离开时间: ${booking.outtime}</p>
                <p>单位: ${booking.unit}</p>
                <p>交通工具: ${booking.vehicle}</p>
                <p>车牌号: ${booking.vname}</p>
                <div class="booking-list" id="officialfriends">
                    <div class="booking-item">
                        <div class="details">
                            <p class="name"> 同行人员人数：${booking.number}</p>
                        </div>
                        <div class="arrow">&#9654;</div>
                    </div>
                    <div class="extra-details" style="display:none;">
                        <c:forEach var="friend" items="${booking.friends}">
                            <div class="booking-item">
                                <p>同行人员姓名：${friend.name}</p>
                                <p>同行人员身份证：${SM4Util.decrypt(friend.id)}</p>
                                <p>同行人员电话号码：${SM4Util.decrypt(friend.phoneNumber)}</p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
    <div class="booking-list" id="officialbookings">
        <c:forEach var="officialbooking" items="${officialbookings}">

            <div class="booking-item">
                <div class="avatar">公务</div>
                <div class="details">
                    <p class="name"> 姓名：${officialbooking.name}</p>
                    <p class="intime">进时间：${officialbooking.intime}</p>
                    <p class="campus">校区：${officialbooking.campus}</p>
                </div>
                <div class="arrow">&#9654;</div>
            </div>

            <div class="extra-details" style="display:none;">
                <p>姓名: ${officialbooking.name}</p>
                <p>身份证: ${officialbooking.id}</p>
                <p>电话号码: ${officialbooking.phoneNumber}</p>
                <p>校区: ${officialbooking.campus}</p>
                <p>进入时间: ${officialbooking.intime}</p>
                <p>离开时间: ${officialbooking.outtime}</p>
                <p>单位: ${officialbooking.unit}</p>
                <p>访问原因: ${officialbooking.reason}</p>
                <p>接待人员: ${officialbooking.receptionist}</p>
                <p>访问部门编号: ${officialbooking.department}</p>
                <p>交通工具: ${officialbooking.vehicle}</p>
                <p>车牌号: ${officialbooking.vname}</p>
                <div class="booking-list" id="friends">
                    <div class="booking-item">
                        <div class="details">
                            <p class="name"> 同行人员人数：${officialbooking.number}</p>
                        </div>
                        <div class="arrow">&#9654;</div>
                    </div>
                    <div class="extra-details" style="display:none;">
                        <c:forEach var="friend" items="${officialbooking.friends}">
                            <div class="booking-item">
                                <p>同行人员姓名：${friend.name}</p>
                                <p>同行人员身份证：${SM4Util.decrypt(friend.id)}</p>
                                <p>同行人员电话号码：${SM4Util.decrypt(friend.phoneNumber)}</p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="scripts3.js"></script>
</body>
</html>