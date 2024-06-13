<%--
  Created by IntelliJ IDEA.
  User: 86173
  Date: 2024/6/9
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>申请人填写——公务预约进校</title>
    <div style="text-align:center;">
        <h1 style="font-size: 75px">公务预约申请</h1>
    </div>
    <script type="text/javascript">
        function addFriend() {
            var friendList = document.getElementById('friendList');
            var newFriend = document.createElement('div');
            newFriend.className = 'friend-item';
            newFriend.innerHTML =
                '<div><input type="text" name="friendName" placeholder="姓名" style="width: 98%;"></div>' +
                '<div><input type="text" name="friendId" placeholder="身份证号" style="width: 98%;"></div>' +
                '<div><input type="text" name="friendPhoneNumber" placeholder="手机号" style="width: 98%;"></div>' +
                '<button type="button" onclick="removeFriend(this)">删除</button>';
            friendList.appendChild(newFriend);
        }

        function removeFriend(button) {
            var friendList = document.getElementById('friendList');
            friendList.removeChild(button.parentNode);
        }
    </script>
</head>
<style>
    .grey1{
        background-color: lightgrey;
        font-size: 40px;
    }
    .grey2{
        background-color: gray;
        font-size: 40px;
    }
    table{
        border: 1px solid #ddd; /* 可以添加边框以区分表格 */
        width: 75%;
        border-collapse: separate;
        font-size: 40px;
    }
    table td {
        border: 1px solid #ddd; /* 可以添加边框以区分单元格 */
        width: 50%; /* 如果表格只有一个列，这会使单元格填充整个表格宽度 */
        font-size: 40px;
    }
    table thead {
        background-color: gray;
        width: 100%; /* 如果表格只有一个列，这会使单元格填充整个表格宽度 */
        font-size: 40px;
    }
    input[type="text"] {
        width: 100%;
        height: 100%;
        font-size: 30px;
        padding: 0px;
    }
    input[type="date"] {
        width: 100%;
        height: 100%;
        font-size: 30px;
        padding: 0px;
    }
    input[type="number"] {
        width: 100%;
        height: 100%;
        font-size: 30px;
        padding: 0px;
    }
    option{
        width: 100%;
        height: 100%;
        font-size: 30px;
    }
    select{
        width: 100%;
        height: 100%;
        font-size: 30px;
    }
    .friend-item {
        margin-bottom: 10px;
    }
    .friend-item div {
        margin-bottom: 5px;
    }
    .friend-item button {
        display: block;
        margin-top: 5px;
    }
</style>
<body>
<div style="text-align:center;">
    <%-- 定义表单提交用户数据 --%>
    <center>
        <form action="AddOfficailbookingServlet" method="post">
            <table>
                <thead>
                <tr>
                    <td colspan="2" style="text-align: center">预约信息</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="grey1">申请日期</td>
                    <td ><input type="date" name="applytime" style="width: 98%"> </td>
                </tr>
                <tr>
                    <td class="grey1">预约校区</td>
                    <td>
                        <input type="checkbox" name="campus" value="朝晖校区"> 朝晖校区<br>
                        <input type="checkbox" name="campus" value="屏峰校区"> 屏峰校区<br>
                        <input type="checkbox" name="campus" value="莫干山校区"> 莫干山校区
                    </td>
                </tr>

                <tr>
                    <td rowspan="2" class="grey1">预约进校时间</td>
                    <td>
                        <input type="date" name="intime" style="width: 98%"><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="date" name="outtime" style="width: 98%">
                    </td>
                </tr>
                <tr>
                    <td class="grey1">所在单位</td>
                    <td><input type="text" name="unit" style="width: 98%"></td>
                </tr>
                </tbody>
            </table>

            <table  border="1" >
                <thead>
                <tr>
                    <td colspan="2" style="text-align: center">访客信息</td>
                </tr>
                </thead>
                <tbody>


                <tr>
                    <td class="grey1">姓名</td>
                    <td><input type="text" name="name" style="width: 98%"></td>
                </tr>
                <tr>
                    <td class="grey1">身份证号</td>
                    <td><input type="text" name="id" placeholder="请输入身份证号" style="width: 98%"></td>
                </tr>
                <tr>
                    <td class="grey1">手机号</td>
                    <td><input type="text" name="phoneNumber" placeholder="请输入手机号" style="width: 98%"></td>
                </tr>
                <tr>
                    <td class="grey1">交通方式</td>
                    <td><select name="vehicle" style="width: 100%">
                        <option value="walk"> 步行</option>
                        <option value="subway"> 地铁</option>
                        <option value="bus"> 公交</option>
                        <option value="car"> 汽车</option>
                    </select></td>
                </tr>
                <tr>
                    <td class="grey1">车牌号</td>
                    <td><input type="text" name="vname" style="width: 98%"> </td>
                </tr>
                <tr>
                    <td class="grey1">公务访问部门</td>
                    <td><select name="department" style="width: 100%">
                        <option value="计算机学院"> 计算机学院</option>
                        <option value="健行学院"> 健行学院</option>
                        <option value="容大后勤"> 容大后勤</option>
                        <option value="信息学院"> 信息学院</option>
                    </select></td>
                </tr>
                <tr>
                    <td class="grey1">公务访问接待人</td>
                    <td><input type="text" name="receptionist" style="width: 98%"> </td>
                </tr>
                <tr>
                    <td class="grey1">来访事由</td>
                    <td><input type="text" name="reason" style="width: 98%"> </td>
                </tr>
                <tr>
                    <td class="grey1">来访人数</td>
                    <td><input type="number" name="pnumber" style="width: 98%"> </td>
                </tr>
                <tr>
                    <td class="grey1">陪同人员</td>
                    <td>
                        <div id="friendList">
                            <div class="friend-item">
                                <div><input type="text" name="friendName" placeholder="姓名" style="width: 98%;"></div>
                                <div><input type="text" name="friendId" placeholder="身份证号" style="width: 98%;"></div>
                                <div><input type="text" name="friendPhoneNumber" placeholder="手机号" style="width: 98%;"></div>
                                <button type="button" onclick="addFriend()">新增</button>
                            </div>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
            <input type="submit" value="提交" style="font-size: 40px"/>
            <input type="reset" value="重置" style="font-size: 40px"/>
        </form>
    </center>
</div>

</body>
</html>
