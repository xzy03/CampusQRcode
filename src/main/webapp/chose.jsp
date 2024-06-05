<%--
  Created by IntelliJ IDEA.
  User: 69472
  Date: 2024/5/28
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<style>
    .center{
        text-align :center;
    }

    .blue{
        color: #4682B4;
    }
    .boldblue{
        color: #4682B4;
        font-weight: bold;
    }
    .boldblack{
        color:black;
        font-weight: bold;
    }
    .container {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 10px;
    }
    .button {
        width: 100px;
        height: 30px;
        background-color: #3E82F7;
        color: #FFFFFF;
        border: none;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 14px;
        cursor: pointer;
    }
    .button1 {
        background-color: #4682B4; /* Green */
    }
    .button2 {
        background-color: #4682B4; /* Blue */
    }
</style>

<head>
    <title>预约须知</title>
</head>
<body>

<div style="text-align: center">
    <div style="display: inline-block"><img width="150" src="img/校徽.jpg"></div>
    <div style="display: inline-block">
        <p><label style="font-weight: bold;font-size: 20px">进校预约</label></p>
        <p><label>主管部门:保卫处</label></p>
        <p><label>联系方式:88320110</label></p>
    </div>
</div>
<br>
<table border="1" style="margin: 0px auto" width="1000px" >
    <tr><td  class="center boldblack"colspan="3">申请步骤</td></tr>
    <tr>
        <td class="boldblue center">第一步</td>
        <td class="boldblue center">预约申请</td>
        <td>填写申请</td>
    </tr>
    <tr >
        <td class="boldblue center">第二步</td>
        <td class="boldblue center">领取通行码</td>
        <td>
            <label>
                预约成功后将收到短信提醒，请在预约时间段内领取浙江工业大学通行码;
                若未收到短信，可通过一下网址领取：
            </label>
            <br>
            <a href="/mytest_war_exploded/yanzhengma.jsp" class="boldblue">
                http://www.xwryjx.zjut.edu.cn/Pass Code/
            </a>

        </td>
    </tr>
</table>
<table border="1" width="1000px" style="margin: 0px auto">
    <tr><td colspan="3" class="center boldblack"  >申请须知</td> </tr>
    <tr>
        <td colspan="3">
        <p class="boldblue">一、预约规则</p>
        <lable class="blue">
            1.仅限本人预约进校，不可转让预约名额。学校将根据校园实际情况，适时调整校门通行管理措施及每日预约人数上限。
        </lable>
        <br>
        <lable class="blue">
            2.预约成功后，请凭身份证(刷人行道闸)或通行码在预约时段进入预约校区，并配合安保人员现场查验和指挥。
        </lable>
        <br>
        <label class="blue">
            3.如遇重要活动或极端天气等特殊情况，已审批的预约信息将被取消，请及时关注学校
            发布的相关提示。
        </label>
        <br>
        <label class="blue">
            4.如发现违规预约或预约进校人员影响校园
        </label>
        <br>
        <p class="boldblue">二、参观须知</p>
        <label class="blue">
            1.进校及参观校园期间，请遵守国家相关法律法规和校园管理相关规定，服从学校工作人员的指挥和管理，遵守社会公德，爱护校园环境和文物，不干扰学校正常教学、科研和生活秩序。
        </label>
        <br>

        <label class="blue">
            2.校园分为开放区域和非开放区域。学生宿舍区、家属区和教学科研办公场所为非开放区域，不对参观者开放。未经允许，请勿入
            内。
        </label>
        <br>
        <label class="blue">
            3.禁止任何机构和个人违规带人进校参观。禁止各类车辆违规揽客、非法经营。
        </label>
        <br>
        <label class="blue">
            4.禁止在公共场所举办群体活动或经营性活动，禁止进行婚纱摄影、影视拍摄、集体亲子、团队旅游等活动。
        </label>
        <br>
        <label class="blue">
            5.禁止使用明火，禁止放飞高空漂浮物，禁上在水域内游泳、洗涤、垂钓、撒网、放生、捕捉伤害水生动植物和鸟禽等。
        </label>
        <br>
        <label class="blue">
            6.禁止摆放有损公共绿地的物品，禁止在校园内擅自开垦种植。
        </label>
        <br>
        <label class="blue">
            7.禁止大声喧哗、使用扩音设备，禁止拉吊床、搭帐篷，禁止涂鸦、刻画、擅自散发或张贴各类宣传品。
        </label>
        <br>
        <br>
        <lable class="blue">
            校园报警求助电话:0571-88320110
        </lable>
        <br>
        <lable class="blue">
            信息化办公室技术支持:0571-88320094
        </lable>
            <br>
            <br>

            <input  type="checkbox" class="boldblue center">我已知晓

        </td>
    </tr>
</table>
    <br>

<div class="container">

        <button class="button button1" onclick="location.href='submitForm.jsp'">社会公众预约</button>
        <button class="button button2">公务预约</button>


</div>





</body>
</html>
