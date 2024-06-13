package dao;

import system.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeptDAO implements Dao{
    public boolean addDept(Dept dept) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = getConnection();
            String sql = "INSERT INTO dept (dept_id, dept_type, dept_name, appointment_permission) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dept.getDeptId());
            stmt.setString(2, dept.getDeptType());
            stmt.setString(3, dept.getDeptName());
            stmt.setBoolean(4, dept.getPower());
            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }

        return success;
    }

    public boolean updateDept(Dept dept) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String sql = "UPDATE dept SET dept_type = ?, dept_name = ?, appointment_permission = ? WHERE dept_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dept.getDeptType());
            stmt.setString(2, dept.getDeptName());
            stmt.setBoolean(3, dept.getPower());
            stmt.setString(4, dept.getDeptId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
    }

    public boolean deleteDeptById(String deptId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = getConnection();
            String sql = "DELETE FROM dept WHERE dept_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, deptId);
            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }

        return success;
    }

    public Dept getDeptById(String deptId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Dept dept = null;

        try {
            conn = getConnection();
            String sql = "SELECT * FROM dept WHERE dept_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, deptId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                dept = new Dept();
                dept.setDeptId(rs.getString("dept_id"));
                dept.setDeptType(rs.getString("dept_type"));
                dept.setDeptName(rs.getString("dept_name"));
                dept.setPower(rs.getBoolean("appointment_permission"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }

        return dept;
    }

    public List<Dept> getAllDepts() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Dept> depts = new ArrayList<>();

        try {
            conn = getConnection();
            String sql = "SELECT * FROM dept";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Dept dept = new Dept();
                dept.setDeptId(rs.getString("dept_id"));
                dept.setDeptType(rs.getString("dept_type"));
                dept.setDeptName(rs.getString("dept_name"));
                dept.setPower(rs.getBoolean("appointment_permission"));
                depts.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.close(rs);
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }

        return depts;
    }

}
