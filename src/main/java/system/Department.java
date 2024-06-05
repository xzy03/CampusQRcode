package system;

public class Department {
    private int departmentId;
    private String departmentType;
    private String departmentName;

    // 构造函数
    public Department(int departmentId, String departmentType, String departmentName) {
        this.departmentId = departmentId;
        this.departmentType = departmentType;
        this.departmentName = departmentName;
    }

    // Getter 和 Setter 方法
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
