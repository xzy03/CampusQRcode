package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import system.*;
import java.sql.*;
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
                        rs.getInt("department_id"),
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

    public void addAdmin(Admin admin) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String sql = "INSERT INTO admin (name, login_name, password, department_id, phone, role) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getLoginName());
            stmt.setString(3, admin.getPassword());
            stmt.setInt(4, admin.getDepartmentId());
            stmt.setString(5, admin.getPhone());
            stmt.setString(6, admin.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.close(stmt);
            DatabaseUtils.close(conn);
        }
    }

//    private void close(ResultSet rs, Statement stmt, Connection conn) {
//        close(rs);
//        close(stmt);
//        close(conn);
//    }
//
//    private void close(ResultSet rs) {
//        if (rs != null) {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void close(Statement stmt) {
//        if (stmt != null) {
//            try {
//                stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void close(Connection conn) {
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}








