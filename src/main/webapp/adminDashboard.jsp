<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    boolean loggedIn = false;
    String loginName = null;
    system.Admin admin = (system.Admin) session.getAttribute("admin");
    dao.AdminDAO adminDAO = new dao.AdminDAO();
    if (admin != null) {
        loggedIn = true;
    } else {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("adminLogin".equals(cookie.getName())) {
                    loginName = cookie.getValue();
                    break;
                }
            }
        }

        if (loginName != null) {
            admin = adminDAO.getAdminByLoginName(loginName);
            if (admin != null) {
                session.setAttribute("admin", admin);
                loggedIn = true;
            }
        }
    }
    Boolean dept_flag=false;
    if (!loggedIn) {
        response.sendRedirect("adminlogin.jsp");
        return;
    }
    else{
        Connection conn =null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = adminDAO.getConnection();
        String sql = "SELECT * FROM dept WHERE dept_name = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, admin.getDepartmentName());
        rs=stmt.executeQuery();
        if(rs.next()){
            dept_flag=rs.getBoolean("appointment_permission");
        }
    }
    System.out.println(dept_flag);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员仪表盘</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            display: flex;
            height: 100vh;
        }
        .sidebar {
            width: 250px;
            background-color: #343a40;
            color: white;
            padding: 20px;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
        }
        .sidebar h2 {
            text-align: center;
        }
        .sidebar ul {
            list-style-type: none;
            padding: 0;
        }
        .sidebar li {
            padding: 10px;
            text-align: center;
        }
        .sidebar a {
            color: white;
            text-decoration: none;
            display: block;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
        .logout-btn {
            margin-top: 20px;
            text-align: center;
        }
        .logout-btn button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #d9534f;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .content {
            flex: 1;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .button-group button {
            padding: 10px 20px;
            font-size: 16px;
            margin: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="sidebar">
    <h2>仪表盘</h2>
    <ul>
        <li><a href="#information" onclick="showUserInfo()">用户信息</a></li>
        <li><a href="#adminManagement" onclick="showAdminManagement()">管理员管理</a></li>
        <li><a href="#deptManagement" onclick="showDeptManagement()">部门管理</a></li>
        <li><a href="#publicReservationManagement" onclick="showPublicReservationManagement()">公众预约管理</a></li>
        <li><a href="#officialReservationManagement" onclick="showOfficialReservationManagement()">公务预约管理</a></li>

    </ul>
    <div class="logout-btn">
        <form action="adminLogout" method="post">
            <button type="submit">退出登录</button>
        </form>
    </div>
</div>
<div class="content" id="content">
    <h1>欢迎，<%= admin.getName() %></h1>
</div>
<script>

    function showUserInfo() {
        const content = document.getElementById('content');
        content.innerHTML = `
            <h1>用户信息</h1>
            <p>姓名：<%= admin.getName() %></p>
            <p>登录名：<%= admin.getLoginName() %></p>
            <p>部门ID：<%= admin.getDepartmentName() %></p>
            <p>电话：<%= admin.getPhone() %></p>
            <p>角色：<%= admin.getRole() %></p>
        `;
    }

    function showAdminManagement() {
        const content = document.getElementById('content');
        content.innerHTML = `
            <h1>管理员管理</h1>
            <div class="button-group">
                <button onclick="showAddAdmin()">添加管理员</button>
                <button onclick="showUpdateAdmin()">修改管理员</button>
                <button onclick="showDeleteAdmin()">删除管理员</button>
                <button onclick="showSearchAdmin()">查询管理员</button>
                <button onclick="showViewDeptAdmin()">查看管理员</button>
            </div>
        `;
    }

    function showAddAdmin() {
        const content = document.getElementById('content');
        content.innerHTML = `
        <h1>添加管理员</h1>
        <form id="addAdminForm">
            <input type="text" name="name" placeholder="姓名" required><br>
            <input type="text" name="loginName" placeholder="登录名" required><br>
            <input type="password" name="password" placeholder="密码" required><br>
            <input type="text" name="departmentName" placeholder="所在部门" required><br>
            <input type="text" name="phone" placeholder="电话" required><br>
            <label for="role">角色</label>
            <select name="role" required>
                <option value="部门管理员">部门管理员</option>
                <option value="学校管理员">学校管理员</option>
                <option value="审计管理员">审计管理员</option>
            </select><br>
            <button type="submit">提交</button>
        </form>
        <div id="result"></div>
    `;

        const form = document.getElementById('addAdminForm');
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(form);
            const searchParams = new URLSearchParams();
            for (const pair of formData) {
                searchParams.append(pair[0], pair[1]);
            }

            fetch('addAdmin', {
                method: 'POST',
                body: searchParams
            })
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('result');
                    if (data.success) {
                        resultDiv.textContent = "添加成功: " + data.message;
                    } else {
                        resultDiv.textContent = "添加失败: " + data.message;
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    const resultDiv = document.getElementById('result');
                    resultDiv.textContent = "请求失败";
                });
        });
    }

    function showUpdateAdmin() {
        const content = document.getElementById('content');
        content.innerHTML = `
        <h1>更新管理员</h1>
        <form id="updateAdminForm">
            <input type="text" name="loginName" placeholder="登录名" required><br>
            <input type="text" name="name" placeholder="姓名" required><br>
            <input type="text" name="departmentName" placeholder="所在部门" required><br>
            <input type="text" name="phone" placeholder="电话" required><br>
            <label for="role">角色</label>
            <select name="role" required>
                <option value="部门管理员">部门管理员</option>
                <option value="学校管理员">学校管理员</option>
                <option value="审计管理员">审计管理员</option>
            </select><br>
            <button type="submit">提交</button>
        </form>
        <div id="result"></div>
    `;

        const form = document.getElementById('updateAdminForm');
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(form);
            const searchParams = new URLSearchParams();
            for (const pair of formData) {
                searchParams.append(pair[0], pair[1]);
            }

            fetch('updateAdmin', {
                method: 'POST',
                body: searchParams
            })
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('result');
                    if (data.success) {
                        resultDiv.textContent = "更新成功: " + data.message;
                    } else {
                        resultDiv.textContent = "更新失败: " + data.message;
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    const resultDiv = document.getElementById('result');
                    resultDiv.textContent = "请求失败";
                });
        });
    }

    function showDeleteAdmin() {
        const content = document.getElementById('content');
        content.innerHTML = `
        <h1>删除管理员</h1>
        <form id="deleteAdminForm">
            <input type="text" name="loginName" placeholder="登录名" required><br>
            <button type="submit">提交</button>
        </form>
        <div id="deleteAdminResult"></div>
    `;

        const form = document.getElementById('deleteAdminForm');
        form.addEventListener('submit', function(event) {
            event.preventDefault(); // 防止表单默认提交

            const formData = new FormData(form);
            const loginName = formData.get('loginName');

            fetch('deleteAdmin', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ loginName: loginName })
            })
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('deleteAdminResult');
                    if (data.success) {
                        resultDiv.textContent = '管理员删除成功';
                    } else {
                        resultDiv.textContent = '管理员删除失败：' + data.message;
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });
    }

    function showSearchAdmin() {
        const content = document.getElementById('content');
        content.innerHTML = `
        <h1>查询管理员</h1>
        <form id="searchAdminForm">
            <input type="text" name="loginName" placeholder="登录名" required><br>
            <button type="submit">提交</button>
        </form>
        <div id="searchAdminResult"></div>
    `;

        const searchAdminForm = document.getElementById('searchAdminForm');
        searchAdminForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(this);
            const searchParams = new URLSearchParams(formData);
            fetch('searchAdmin', {
                method: 'POST',
                body: searchParams
            })
                .then(response => response.json())
                .then(data => {
                    const content = document.getElementById('content');
                    const table = document.createElement('table');
                    table.setAttribute('border', '1');

                    // 表头
                    const headerRow = document.createElement('tr');
                    ['姓名', '登录名', '所在部门', '电话', '角色'].forEach(headerText => {
                        const headerCell = document.createElement('th');
                        headerCell.textContent = headerText;
                        headerRow.appendChild(headerCell);
                    });
                    table.appendChild(headerRow);

                    // 表格内容
                    data.forEach(item => {
                        const row = document.createElement('tr');
                        ['name', 'loginName', 'departmentName', 'phone', 'role'].forEach(key => {
                            const cell = document.createElement('td');
                            cell.textContent = item[key];
                            row.appendChild(cell);
                        });
                        table.appendChild(row);
                    });

                    // 清空内容区域并添加表格
                    //content.innerHTML = '';
                    const lastChild=content.lastChild;
                    content.removeChild(lastChild);
                    content.appendChild(table);

                    // 调试输出以检查接收到的数据
                    console.log("Received data:", data);
                })
                .catch(error => console.error('Error:', error));
        });
    }

    function showViewDeptAdmin() {
        fetch('viewDeptAdmin')
            .then(response => response.json())
            .then(data => {
                const content = document.getElementById('content');
                const table = document.createElement('table');
                table.setAttribute('border', '1');

                // 表头
                const headerRow = document.createElement('tr');
                ['姓名', '登录名', '所在部门', '电话', '角色'].forEach(headerText => {
                    const headerCell = document.createElement('th');
                    headerCell.textContent = headerText;
                    headerRow.appendChild(headerCell);
                });
                table.appendChild(headerRow);

                // 表格内容
                data.forEach(item => {
                    const row = document.createElement('tr');
                    ['name', 'loginName', 'departmentName', 'phone', 'role'].forEach(key => {
                        const cell = document.createElement('td');
                        cell.textContent = item[key];
                        row.appendChild(cell);
                    });
                    table.appendChild(row);
                });

                // 清空内容区域并添加表格
                content.innerHTML = '';
                content.appendChild(table);

                // 调试输出以检查接收到的数据
                console.log("Received data:", data);
            })
            .catch(error => console.error('Error:', error));
    }

    function showDeptManagement() {
        const content = document.getElementById('content');
        if(<%= admin.getRole().equals("学校管理员")%>) {
            content.innerHTML = `
            <h1>部门管理</h1>
            <div class="button-group">
            <button onclick="showAddDept()">添加部门</button>
            <button onclick="showUpdateDept()">修改部门</button>
            <button onclick="showDeleteDept()">删除部门</button>
            <button onclick="showSearchDept()">查询部门</button>
            <button onclick="showViewDept()">查看部门</button>
            </div>
        `;
        }
        else{
            content.innerHTML = `
            <h1>您没有该权限</h1>
        `;
        }
    }

    function showAddDept() {
        const content = document.getElementById('content');
        content.innerHTML = `
        <h1>添加部门</h1>
        <form id="addDeptForm">
            <input type="text" name="deptId" placeholder="部门编号" required><br>
            <select name="deptType" required>
                <option value="">选择部门类型</option>
                <option value="行政部门">行政部门</option>
                <option value="直属部门">直属部门</option>
                <option value="学院">学院</option>
            </select><br>
            <input type="text" name="deptName" placeholder="部门名称" required><br>
            <label>
                <input type="checkbox" name="power"> 预约管理权限
            </label><br>
            <button type="submit">提交</button>
        </form>
        <div id="addDeptResult"></div>
    `;

        const form = document.getElementById('addDeptForm');
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const formData = new FormData(form);
            const dept = {
                deptId: formData.get('deptId'),
                deptType: formData.get('deptType'),
                deptName: formData.get('deptName'),
                power: formData.get('power') === 'on' // Convert 'on' to true, otherwise false
            };

            fetch('addDept', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(dept)
            })
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('addDeptResult');
                    if (data.success) {
                        resultDiv.textContent = '部门添加成功';
                    } else {
                        resultDiv.textContent = '部门添加失败：' + data.message;
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });
    }

    function showUpdateDept() {
        const content = document.getElementById('content');
        content.innerHTML = `
        <h1>修改部门</h1>
        <form id="updateDeptForm">
            <input type="text" name="deptId" placeholder="部门编号" required><br>
            <select name="deptType" required>
                <option value="">选择部门类型</option>
                <option value="行政部门">行政部门</option>
                <option value="直属部门">直属部门</option>
                <option value="学院">学院</option>
            </select><br>
            <input type="text" name="deptName" placeholder="部门名称" required><br>
            <label>
                <input type="checkbox" name="power"> 预约管理权限
            </label><br>
            <button type="submit">提交</button>
        </form>
        <div id="updateDeptResult"></div>
    `;

        const form = document.getElementById('updateDeptForm');
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const formData = new FormData(form);
            const dept = {
                deptId: formData.get('deptId'),
                deptType: formData.get('deptType'),
                deptName: formData.get('deptName'),
                power: formData.get('power') === 'on'
            };

            fetch('updateDept', {
                method: 'POST',  // 改为 POST 方法
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(dept)
            })
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('updateDeptResult');
                    if (data.success) {
                        resultDiv.textContent = '部门修改成功';
                    } else {
                        resultDiv.textContent = '部门修改失败：' + data.message;
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });
    }

    function showDeleteDept() {
        const content = document.getElementById('content');
        content.innerHTML = `
        <h1>删除部门</h1>
        <form id="deleteDeptForm">
            <input type="text" name="deptId" placeholder="部门编号" required><br>
            <button type="submit">提交</button>
        </form>
        <div id="deleteDeptResult"></div>
    `;

        const form = document.getElementById('deleteDeptForm');
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const formData = new FormData(form);
            const deptId = formData.get('deptId');

            fetch('deleteDept', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ deptId: deptId })
            })
                .then(response => response.json())
                .then(data => {
                    const resultDiv = document.getElementById('deleteDeptResult');
                    if (data.success) {
                        resultDiv.textContent = '部门删除成功';
                    } else {
                        resultDiv.textContent = '部门删除失败：' + data.message;
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });
    }

    function showSearchDept() {
        const content = document.getElementById('content');
        content.innerHTML = `
        <h1>查询部门</h1>
        <form id="searchDeptForm">
            <input type="text" name="deptId" placeholder="部门编号" required><br>
            <button type="submit">提交</button>
        </form>
        <div id="searchDeptResult"></div>
    `;

        const searchDeptForm = document.getElementById('searchDeptForm');
        searchDeptForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(this);
            const searchParams = new URLSearchParams(formData);
            fetch('searchDept', {
                method: 'POST',
                body: searchParams
            })
                .then(response => response.json())
                .then(data => {
                    const content = document.getElementById('content');
                    const table = document.createElement('table');
                    table.setAttribute('border', '1');

                    // 表头
                    const headerRow = document.createElement('tr');
                    ['部门编号', '部门类型', '部门名称', '预约管理权限'].forEach(headerText => {
                        const headerCell = document.createElement('th');
                        headerCell.textContent = headerText;
                        headerRow.appendChild(headerCell);
                    });
                    table.appendChild(headerRow);

                    // 表格内容
                    data.forEach(item => {
                        const row = document.createElement('tr');
                        ['deptId', 'deptType', 'deptName', 'power'].forEach(key => {
                            const cell = document.createElement('td');
                            cell.textContent = item[key];
                            row.appendChild(cell);
                        });
                        table.appendChild(row);
                    });

                    // 清空内容区域并添加表格
                    const lastChild = content.lastChild;
                    content.removeChild(lastChild);
                    content.appendChild(table);

                    // 调试输出以检查接收到的数据
                    console.log("Received data:", data);
                })
                .catch(error => console.error('Error:', error));
        });
    }

    function showViewDept() {
        fetch('viewDept')
            .then(response => response.json())
            .then(data => {
                const content = document.getElementById('content');
                const table = document.createElement('table');
                table.setAttribute('border', '1');

                const headerRow = document.createElement('tr');
                ['部门编号', '部门类型', '部门名称', '预约管理权限'].forEach(headerText => {
                    const headerCell = document.createElement('th');
                    headerCell.textContent = headerText;
                    headerRow.appendChild(headerCell);
                });
                table.appendChild(headerRow);

                data.forEach(item => {
                    const row = document.createElement('tr');
                    ['deptId', 'deptType', 'deptName', 'power'].forEach(key => {
                        const cell = document.createElement('td');
                        cell.textContent = item[key];
                        row.appendChild(cell);
                    });
                    table.appendChild(row);
                });

                content.innerHTML = '';  // 清空内容区域
                content.appendChild(table);

                console.log("Received data:", data);
            })
            .catch(error => console.error('Error:', error));
    }

    function showPublicReservationManagement() {
        const content = document.getElementById('content');
        if(<%= admin.getRole().equals("学校管理员") || dept_flag && admin.getRole().equals("部门管理员")%>) {
            content.innerHTML = `
        <h1>公众预约管理</h1>
        <div class="button-group">
            <button onclick="showSearchPublicReservations()">查询预约记录</button>
            <button onclick="showPublicStatistics()">统计预约情况</button>
        </div>
        <div id="reservationContent"></div>
    `;
        }
        else{
            content.innerHTML = `
            <h1>您没有该权限</h1>
        `;
        }
    }

    function showSearchPublicReservations() {
        const content = document.getElementById('content');
        content.innerHTML = `
    <h2>查询预约记录</h2>
    <form id="searchMybookingForm">
        <input type="date" name="applyTime" placeholder="申请日期">申请日期<br>
        <input type="date" name="entryTime" placeholder="预约日期">预约日期<br>
        <input type="text" name="campus" placeholder="预约校区"><br>
        <input type="text" name="unit" placeholder="所在单位"><br>
        <input type="text" name="name" placeholder="预约人姓名"><br>
        <input type="text" name="idCard" placeholder="身份证号"><br>
        <button type="submit">查询</button>
    </form>

    <div id="searchMybookingResult"></div>
    `;

        const searchMybookingForm = document.getElementById('searchMybookingForm');
        searchMybookingForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(this);
            const searchParams = new URLSearchParams(formData);
            fetch('searchMybooking', {
                method: 'POST',
                body: searchParams
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    const resultDiv = document.getElementById('searchMybookingResult');
                    const table = document.createElement('table');
                    table.setAttribute('border', '1');
                    if (data.length === 0) {
                        const noResultsMessage = document.createElement('p');
                        noResultsMessage.textContent = '未找到相关预约记录';
                        resultDiv.innerHTML = '';
                        resultDiv.appendChild(noResultsMessage);
                    } else {
                        const headerRow = document.createElement('tr');
                        ['申请日期', '预约校区', '预约日期', '所在单位', '姓名', '操作'].forEach(headerText => {
                            const headerCell = document.createElement('th');
                            headerCell.textContent = headerText;
                            headerRow.appendChild(headerCell);
                        });
                        table.appendChild(headerRow);

                        data.forEach(item => {
                            const row = document.createElement('tr');
                            ['applytime', 'campus', 'intime', 'unit', 'name'].forEach(key => {
                                const cell = document.createElement('td');
                                cell.textContent = item[key];
                                row.appendChild(cell);
                            });
                            const actionCell = document.createElement('td');
                            const detailsButton = document.createElement('button');
                            detailsButton.textContent = '查看详情';
                            detailsButton.addEventListener('click', () => showPublicReservationDetails(item));
                            actionCell.appendChild(detailsButton);
                            row.appendChild(actionCell);
                            table.appendChild(row);
                        });
                        resultDiv.innerHTML = '';
                        resultDiv.appendChild(table);
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });
    }

    function showPublicReservationDetails(item) {
        const detailsDiv = document.createElement('div');
        const detailsTable = document.createElement('table');
        detailsTable.setAttribute('border', '1');

        Object.keys(item).forEach(key => {
            const row = document.createElement('tr');
            const keyCell = document.createElement('td');
            switch(key) {
                case 'applytime':
                    keyCell.textContent = '申请时间';
                    break;
                case 'campus':
                    keyCell.textContent = '校区';
                    break;
                case 'intime':
                    keyCell.textContent = '入校时间';
                    break;
                case 'outtime':
                    keyCell.textContent = '出校时间';
                    break;
                case 'unit':
                    keyCell.textContent = '所在单位';
                    break;
                case 'vehicle':
                    keyCell.textContent = '交通方式';
                    break;
                case 'vname':
                    keyCell.textContent = '车牌号';
                    break;
                case 'number':
                    keyCell.textContent = '随行人员数量';
                    break;
                case 'friends':
                    keyCell.textContent = '同行者';
                    break;
                case 'QRcode':
                    keyCell.textContent = '二维码路径';
                    break;
                case 'InvalidQRcode':
                    keyCell.textContent = '无效二维码路径';
                    break;
                case 'name':
                    keyCell.textContent = '姓名';
                    break;
                case 'id':
                    keyCell.textContent = '身份证号';
                    break;
                case 'phoneNumber':
                    keyCell.textContent = '手机号码';
                    break;
                default:
                    keyCell.textContent = key; // 如果有未覆盖的字段，保留英文标签
            }
            const valueCell = document.createElement('td');

            if (key === 'friends') {
                const friendsTable = document.createElement('table');
                friendsTable.setAttribute('border', '1');

                // 添加表头
                const headerRow = document.createElement('tr');
                ['姓名', '身份证号', '电话号码'].forEach(headerText => {
                    const headerCell = document.createElement('th');
                    headerCell.textContent = headerText;
                    headerRow.appendChild(headerCell);
                });
                friendsTable.appendChild(headerRow);

                // 添加朋友信息
                item[key].forEach(friend => {
                    const friendRow = document.createElement('tr');
                    Object.values(friend).forEach(value => {
                        const friendCell = document.createElement('td');
                        friendCell.textContent = value;
                        friendRow.appendChild(friendCell);
                    });
                    friendsTable.appendChild(friendRow);
                });

                valueCell.appendChild(friendsTable);
            } else {
                valueCell.textContent = item[key];
            }

            row.appendChild(keyCell);
            row.appendChild(valueCell);
            detailsTable.appendChild(row);
        });

        detailsDiv.appendChild(detailsTable);
        const resultDiv = document.getElementById('searchMybookingResult');
        resultDiv.innerHTML = '';
        resultDiv.appendChild(detailsDiv);
    }

    function showPublicStatistics() {
        const content = document.getElementById('content');
        content.innerHTML = `
        <h2>统计预约记录</h2>
        <form id="statisticsPublicForm">
            <input type="month" name="applyMonth" placeholder="申请月度">申请月度<br>
            <input type="month" name="reservationMonth" placeholder="预约月度">预约月度<br>
            <input type="text" name="campus" placeholder="预约校区"><br>
            <button type="submit">统计</button>
        </form>

        <div id="statisticsPublicResult"></div>
    `;

        const statisticsPublicForm = document.getElementById('statisticsPublicForm');
        statisticsPublicForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(this);
            const searchParams = new URLSearchParams(formData);
            fetch('getStatisticsPublic', {
                method: 'POST',
                body: searchParams
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    const resultDiv = document.getElementById('statisticsPublicResult');
                    const table = document.createElement('table');
                    table.setAttribute('border', '1');
                    if (data.length === 0) {
                        resultDiv.textContent = '未找到相关统计记录';
                        return;
                    }
                    const headerRow = document.createElement('tr');
                    ['申请月度', '预约月度', '预约校区', '预约次数', '人次'].forEach(headerText => {
                        const headerCell = document.createElement('th');
                        headerCell.textContent = headerText;
                        headerRow.appendChild(headerCell);
                    });
                    table.appendChild(headerRow);

                    data.forEach(item => {
                        const row = document.createElement('tr');
                        ['applyMonth', 'reservationMonth', 'campus', 'reservationCount', 'personCount'].forEach(key => {
                            const cell = document.createElement('td');
                            cell.textContent = item[key];
                            row.appendChild(cell);
                        });
                        table.appendChild(row);
                    });

                    resultDiv.innerHTML = '';
                    resultDiv.appendChild(table);
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });
    }

    function showOfficialReservationManagement(){
        const content = document.getElementById('content');
        if(<%= admin.getRole().equals("学校管理员") || admin.getRole().equals("部门管理员")%>) {
            content.innerHTML = `
        <h1>公务预约管理</h1>
        <div class="button-group">
            <button onclick="showSearchOfficialReservations()">查询预约记录</button>
            <button onclick="showOfficialStatistics()">统计预约情况</button>
        </div>
        <div id="reservationContent"></div>
    `;
        }
        else{
            content.innerHTML = `
            <h1>您没有该权限</h1>
        `;
        }
    }

    function showSearchOfficialReservations() {
        const content = document.getElementById('content');
        content.innerHTML = '<h2>查询预约记录</h2>' +
            '<form id="searchOfficialbookingForm">' +
            '<input type="date" name="applyTime" placeholder="申请日期">申请日期<br>' +
            '<input type="date" name="entryTime" placeholder="预约日期">预约日期<br>' +
            '<input type="text" name="campus" placeholder="预约校区"><br>' +
            '<input type="text" name="unit" placeholder="所在单位"><br>' +
            '<input type="text" name="name" placeholder="预约人姓名"><br>' +
            '<input type="text" name="idCard" placeholder="身份证号"><br>' +
            (<%= admin.getRole().equals("部门管理员") && !dept_flag%>  ?
                    '<input type="text" name="department" value="' + '<%= admin.getDepartmentName()%>' + '" placeholder="公务访问部门" readonly><br>' :
                    '<input type="text" name="department" placeholder="公务访问部门"><br>'
            ) +
            '<input type="text" name="receptionist" placeholder="公务访问接待人"><br>' +
            '<label for="permit">审核状态</label>' +
            '<select name="permit">' +
            '<option value="">全部</option>' +
            '<option value="待审">待审</option>' +
            '<option value="通过">通过</option>' +
            '<option value="拒绝">拒绝</option>' +
            '</select><br>' +
            '<button type="submit">查询</button>' +
            '</form>' +
            '<div id="searchOfficialbookingResult"></div>';


        const searchOfficialbookingForm = document.getElementById('searchOfficialbookingForm');
        searchOfficialbookingForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(this);
            const searchParams = new URLSearchParams(formData);
            fetch('searchOfficialbooking', {
                method: 'POST',
                body: searchParams
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    const resultDiv = document.getElementById('searchOfficialbookingResult');
                    resultDiv.innerHTML = '';
                    const table = document.createElement('table');
                    table.setAttribute('border', '1');
                    if (data.length === 0) {
                        const noResultsMessage = document.createElement('p');
                        noResultsMessage.textContent = '未找到相关预约记录';
                        resultDiv.appendChild(noResultsMessage);
                    } else {
                        const headerRow = document.createElement('tr');
                        ['申请日期', '预约校区', '预约日期', '所在单位', '姓名', '公务访问部门', '公务访问接待人', '审核状态', '操作'].forEach(headerText => {
                            const headerCell = document.createElement('th');
                            headerCell.textContent = headerText;
                            headerRow.appendChild(headerCell);
                        });
                        table.appendChild(headerRow);

                        data.forEach(item => {
                            const row = document.createElement('tr');
                            ['applytime', 'campus', 'intime', 'unit', 'name', 'department', 'receptionist', 'permit'].forEach(key => {
                                const cell = document.createElement('td');
                                cell.textContent = item[key];
                                row.appendChild(cell);
                            });
                            const actionCell = document.createElement('td');
                            const detailsButton = document.createElement('button');
                            detailsButton.textContent = '查看详情';
                            detailsButton.addEventListener('click', () => showOfficialReservationDetails(item));
                            actionCell.appendChild(detailsButton);
                            row.appendChild(actionCell);
                            table.appendChild(row);
                        });
                        resultDiv.appendChild(table);
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });
    }

    function showOfficialReservationDetails(item) {
        const detailsDiv = document.createElement('div');
        const detailsTable = document.createElement('table');
        detailsTable.setAttribute('border', '1');

        Object.keys(item).forEach(key => {
            const row = document.createElement('tr');
            const keyCell = document.createElement('td');
            switch(key) {
                case 'applytime':
                    keyCell.textContent = '申请时间';
                    break;
                case 'campus':
                    keyCell.textContent = '校区';
                    break;
                case 'intime':
                    keyCell.textContent = '入校时间';
                    break;
                case 'outtime':
                    keyCell.textContent = '出校时间';
                    break;
                case 'unit':
                    keyCell.textContent = '所在单位';
                    break;
                case 'vehicle':
                    keyCell.textContent = '交通方式';
                    break;
                case 'vname':
                    keyCell.textContent = '车牌号';
                    break;
                case 'number':
                    keyCell.textContent = '随行人员数量';
                    break;
                case 'friends':
                    keyCell.textContent = '同行者';
                    break;
                case 'QRcode':
                    keyCell.textContent = '二维码路径';
                    break;
                case 'InvalidQRcode':
                    keyCell.textContent = '无效二维码路径';
                    break;
                case 'name':
                    keyCell.textContent = '姓名';
                    break;
                case 'id':
                    keyCell.textContent = '身份证号';
                    break;
                case 'phoneNumber':
                    keyCell.textContent = '手机号码';
                    break;
                case 'department':
                    keyCell.textContent = '公务访问部门';
                    break;
                case 'receptionist':
                    keyCell.textContent = '公务访问接待人';
                    break;
                case 'reason':
                    keyCell.textContent = '事由';
                    break;
                case 'permit':
                    keyCell.textContent = '审核状态';
                    break;
                default:
                    keyCell.textContent = key; // 如果有未覆盖的字段，保留英文标签
            }
            const valueCell = document.createElement('td');

            if (key === 'friends') {
                const friendsTable = document.createElement('table');
                friendsTable.setAttribute('border', '1');

                // 添加表头
                const headerRow = document.createElement('tr');
                ['姓名', '身份证号', '电话号码'].forEach(headerText => {
                    const headerCell = document.createElement('th');
                    headerCell.textContent = headerText;
                    headerRow.appendChild(headerCell);
                });
                friendsTable.appendChild(headerRow);

                // 添加朋友信息
                item[key].forEach(friend => {
                    const friendRow = document.createElement('tr');
                    Object.values(friend).forEach(value => {
                        const friendCell = document.createElement('td');
                        friendCell.textContent = value;
                        friendRow.appendChild(friendCell);
                    });
                    friendsTable.appendChild(friendRow);
                });

                valueCell.appendChild(friendsTable);
            } else {
                if(key === 'permit' && item[key] === '待审') {
                    // 创建一个表格元素
                    const table = document.createElement('table');
                    // 创建一行元素
                    const row = document.createElement('tr');
                    // 左边单选框
                    const leftCell = document.createElement('td');
                    const leftRadio1 = document.createElement('input');
                    leftRadio1.type = 'radio';
                    leftRadio1.name = 'leftOption';
                    leftRadio1.value = '通过';
                    const leftLabel1 = document.createElement('label');
                    leftLabel1.textContent = '通过';
                    leftCell.appendChild(leftRadio1);
                    leftCell.appendChild(leftLabel1);
                    // 中间单选框
                    const middleCell = document.createElement('td');
                    const middleRadio2 = document.createElement('input');
                    middleRadio2.type = 'radio';
                    middleRadio2.name = 'leftOption';
                    middleRadio2.value = '拒绝';
                    const middleLabel2 = document.createElement('label');
                    middleLabel2.textContent = '拒绝';
                    middleCell.appendChild(middleRadio2);
                    middleCell.appendChild(middleLabel2);
                    // 右边提交按钮
                    const rightCell = document.createElement('td');
                    const submitButton = document.createElement('button');
                    submitButton.textContent = '提交';
                    submitButton.addEventListener('click', function () {
                        // 调用另一个函数处理事务
                        const itemId = item['id'];
                        handleTransaction(itemId);
                    });
                    rightCell.appendChild(submitButton);

                    // 将左、中、右列添加到行中
                    row.appendChild(leftCell);
                    row.appendChild(middleCell);
                    row.appendChild(rightCell);

                    // 将行添加到表格中
                    table.appendChild(row);
                    valueCell.appendChild(table);
                }
                else{
                    valueCell.textContent = item[key];
                }
            }

            row.appendChild(keyCell);
            row.appendChild(valueCell);
            detailsTable.appendChild(row);
        });

        detailsDiv.appendChild(detailsTable);
        const resultDiv = document.getElementById('searchOfficialbookingResult');
        resultDiv.innerHTML = '';
        resultDiv.appendChild(detailsDiv);
    }

    // handleTransaction 函数
    function handleTransaction(itemId) {
        // 获取选中的单选按钮的值
        const selectedOption = document.querySelector('input[name="leftOption"]:checked').value;
        if(selectedOption === '通过' || selectedOption === '拒绝') {

            // 更新 item 的 permit 值
            const updatedPermit = selectedOption;

            // 发送请求到新建的Servlet，更新数据库
            fetch('updateOfficialBooking', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id: itemId,
                    permit: updatedPermit
                })
            })
                .then(response => response.json())
                .then(data => {
                    console.log('更新成功:', data);
                    // 禁用按钮
                    const buttons = document.querySelectorAll('button');
                    buttons.forEach(button => button.disabled = true);

                    // 显示成功提示
                    const successMessage = document.createElement('div');
                    successMessage.textContent = '更新成功';
                    successMessage.style.position = 'fixed';
                    successMessage.style.top = '50%';
                    successMessage.style.left = '50%';
                    successMessage.style.transform = 'translate(-50%, -50%)';
                    successMessage.style.backgroundColor = 'rgba(0, 128, 0, 0.8)';
                    successMessage.style.color = 'white';
                    successMessage.style.padding = '10px';
                    successMessage.style.borderRadius = '5px';
                    document.body.appendChild(successMessage);

                    // 显示2秒后移除提示，并刷新页面或更新页面上的显示
                    setTimeout(() => {
                        document.body.removeChild(successMessage);

                        // 重新启用按钮
                        buttons.forEach(button => button.disabled = false);

                        showSearchOfficialReservations();
                    }, 2000);
                })
                .catch(error => {
                    console.error('更新失败:', error);
                    // 禁用按钮
                    const buttons = document.querySelectorAll('button');
                    buttons.forEach(button => button.disabled = true);

                    // 显示成功提示
                    const successMessage = document.createElement('div');
                    successMessage.textContent = '更新失败';
                    successMessage.style.position = 'fixed';
                    successMessage.style.top = '50%';
                    successMessage.style.left = '50%';
                    successMessage.style.transform = 'translate(-50%, -50%)';
                    successMessage.style.backgroundColor = 'rgba(128, 0, 0, 0.8)';
                    successMessage.style.color = 'white';
                    successMessage.style.padding = '10px';
                    successMessage.style.borderRadius = '5px';
                    document.body.appendChild(successMessage);

                    // 显示2秒后移除提示，并刷新页面或更新页面上的显示
                    setTimeout(() => {
                        document.body.removeChild(successMessage);

                        // 重新启用按钮
                        buttons.forEach(button => button.disabled = false);

                        showSearchOfficialReservations();
                    }, 2000);
                });
        }
    }

    function showOfficialStatistics() {
        const content = document.getElementById('content');
        content.innerHTML =
        `<h2>统计预约记录</h2>`+
            `<form id="statisticsOfficialForm">`+
            `<input type="month" name="applyMonth" placeholder="申请月度">申请月度<br>`+
            `<input type="month" name="reservationMonth" placeholder="预约月度">预约月度<br>`+
            `<input type="text" name="campus" placeholder="预约校区"><br>`+
            (<%= admin.getRole().equals("部门管理员") && !dept_flag%>  ?
                    '<input type="text" name="department" value="' + '<%= admin.getDepartmentName()%>' + '" placeholder="公务访问部门" readonly><br>' :
                    '<input type="text" name="department" placeholder="公务访问部门"><br>'
            )+
            `<button type="submit">统计</button>`+
            `</form>`+

            `<div id="statisticsOfficialResult"></div>`
    ;

        const statisticsOfficialForm = document.getElementById('statisticsOfficialForm');
        statisticsOfficialForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const formData = new FormData(this);
            const searchParams = new URLSearchParams(formData);
            fetch('getStatisticsOfficial', {
                method: 'POST',
                body: searchParams
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    const resultDiv = document.getElementById('statisticsOfficialResult');
                    const table = document.createElement('table');
                    table.setAttribute('border', '1');
                    if (data.length === 0) {
                        resultDiv.textContent = '未找到相关统计记录';
                        return;
                    }
                    const headerRow = document.createElement('tr');
                    ['申请月度', '预约月度', '预约校区', '公务访问部门', '预约次数', '人次'].forEach(headerText => {
                        const headerCell = document.createElement('th');
                        headerCell.textContent = headerText;
                        headerRow.appendChild(headerCell);
                    });
                    table.appendChild(headerRow);

                    data.forEach(item => {
                        const row = document.createElement('tr');
                        ['applyMonth', 'reservationMonth', 'campus', 'department', 'reservationCount', 'personCount'].forEach(key => {
                            const cell = document.createElement('td');
                            cell.textContent = item[key];
                            row.appendChild(cell);
                        });
                        table.appendChild(row);
                    });

                    resultDiv.innerHTML = '';
                    resultDiv.appendChild(table);
                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });
    }


</script>
</body>
</html>
