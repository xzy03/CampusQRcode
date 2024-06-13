package system;

public class Admin {
    private String name;
    private String loginName;
    private String password;
    private String departmentName;
    private String phone;
    private String role;

    // 全参构造函数
    public Admin(String name, String loginName, String password, String departmentName, String phone, String role) {
        this.name = name;
        this.loginName = loginName;
        this.password = password;
        this.departmentName = departmentName;
        this.phone = phone;
        this.role = role;
    }

    // 无参构造函数
    public Admin() {
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", departmentName=" + departmentName +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

