package dao;
import system.*;
import java.sql.*;
import java.util.*;
import com.example.mytest.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminDAO implements Dao {
    public Admin login(String loginName, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT * FROM admin WHERE login_name = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, loginName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Admin(
                        rs.getString("name"),
                        rs.getString("login_name"),
                        rs.getString("password"),
                        rs.getString("department_name"),
                        rs.getString("phone"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.close(rs, stmt, conn);
        }

        return null;
    }

    public Admin getAdminByLoginName(String loginName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT * FROM admin WHERE login_name = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, loginName);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Admin(
                        rs.getString("name"),
                        rs.getString("login_name"),
                        rs.getString("password"),
                        rs.getString("department_name"),
                        rs.getString("phone"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.close(rs, stmt, conn);
        }

        return null;
    }

    public List<Admin> getAllAdmins() {
        List<Admin> adminList = new ArrayList<>();
        try {
            Connection connection = DatabaseUtils.getConnection();
            String sql = "SELECT * FROM admin";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setName(resultSet.getString("name"));
                admin.setLoginName(resultSet.getString("login_name"));
                admin.setDepartmentName(resultSet.getString("department_name"));
                admin.setPhone(resultSet.getString("phone"));
                admin.setRole(resultSet.getString("role"));
                adminList.add(admin);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminList;
    }

    public boolean addAdmin(Admin admin) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String sql = "INSERT INTO admin (name, login_name, password, department_name, phone, role) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getLoginName());
            stmt.setString(3, admin.getPassword());
            stmt.setString(4, admin.getDepartmentName());
            stmt.setString(5, admin.getPhone());
            stmt.setString(6, admin.getRole());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // 如果有行受影响，则返回 true，否则返回 false
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 出现异常时返回 false
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
    }

    public boolean updateAdmin(Admin admin) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String sql = "UPDATE admin SET name = ?, department_name = ?, phone = ?, role = ? WHERE login_name = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getDepartmentName());
            stmt.setString(3, admin.getPhone());
            stmt.setString(4, admin.getRole());
            stmt.setString(5, admin.getLoginName());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
    }

    public boolean deleteAdminByLoginName(String loginName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = getConnection();
            String sql = "DELETE FROM admin WHERE login_name = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, loginName);
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

}