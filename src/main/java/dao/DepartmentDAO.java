package dao;

import system.DatabaseUtils;
import system.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements Dao {

    public void addDepartment(Department department) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String sql = "INSERT INTO department (department_id, department_type, department_name) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, department.getDepartmentId());
            stmt.setString(2, department.getDepartmentType());
            stmt.setString(3, department.getDepartmentName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
    }

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT * FROM department";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Department department = new Department(
                        rs.getInt("department_id"),
                        rs.getString("department_type"),
                        rs.getString("department_name")
                );
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.close(rs, stmt, conn);
        }

        return departments;
    }
}
