package dao;
import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;
public interface Dao {
    public static DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/xiangzy_db_uni");
        } catch (NamingException e) {
            e.printStackTrace();
            System.out.println("异常：" + e);
        }
        return dataSource;
    }
    public default Connection getConnection() {
        DataSource dataSource = getDataSource();
        Connection conn = null;
        try {
            conn = getDataSource().getConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("异常：" + sqle);
        }
        return conn;
    }

    static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void close(ResultSet rs, Statement stmt, Connection conn) {
        close(rs);
        close(stmt);
        close(conn);
    }

}
