<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2024/3/12
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="customer" class="beans.Customer" scope="application"></jsp:useBean>
<html>
<head>
    <title>A Simple Exam</title>
    <style type="text/css">
        h3, p {text-indent:2em;font-size:85%}
    </style>
</head>
<body>
    <p>考生名字：<jsp:getProperty name="customer" property="username"/></p>
<%--    <p>考生用户密码：<jsp:getProperty name="customer" property="password"/></p>--%>
    <h3>请回答下面的问题：</h3>
    <form action="simple-test" method="post">
    <p>1. 在北京召开的奥运会是第29届奥运会。
        <input type="radio" name="quest1" value="1"> 正确
        <input type="radio" name="quest1" value="2"> 错误
    </p>
    <p>2. Windows操作系统是哪个公司的产品？
        <input type="radio" name="quest2" value="1"> Sun公司
        <input type="radio" name="quest2" value="2"> IBM公司
        <input type="radio" name="quest2" value="3"> Intel公司
        <input type="radio" name="quest2" value="4"> Microsoft公司
    </p>
    <p>3.下面的程序设计语言，哪些是面向对象的？
        <input type="checkbox" name="quest3" value="1"> Visual Basic
        <input type="checkbox" name="quest3" value="2"> Java语言
        <input type="checkbox" name="quest3" value="3"> C语言
        <input type="checkbox" name="quest3" value="4"> C++语言
    </p>
    <p>4.编写Servlet程序应继承什么类？
        <input type="text" name="quest4" size="30"></p>
    <p><b>交卷请点击:</b><input type="submit" value="交卷">
        <input type="reset" value="重答"></p>
    </form>

</body>
</html>
