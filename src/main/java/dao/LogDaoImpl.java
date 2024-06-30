package dao;

import beans.Auditlog;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Timestamp;

public class LogDaoImpl implements LogDao{
    public boolean addLog(Auditlog log){
        String sql="INSERT INTO log(user_name,user_operation,user_description) VALUES(?,?,?)";
        try(
                Connection conn=getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,log.getUname());
            pstmt.setString(2,log.getOperation());
            pstmt.setString(3,log.getDescription());
            pstmt.executeUpdate();
            return true;
        }catch(SQLException se){
            se.printStackTrace();
            return false;
        }
    }
    public ArrayList<Auditlog> find(Auditlog log){
        String sql="select * "
                +"from log where  1=1";
        ArrayList<Auditlog> logs= new ArrayList<>();
        if(!log.getUname().equals("")){
            sql+="and uname like ?" ;
        }
        if(!log.getOperation().equals("")){
            sql+="and operation like ?" ;
        }

        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql)) {
            int t = 0;

            if(!log.getUname().equals("")){
                t++;
                pstmt.setString(t,"%"+log.getUname()+"%");
            }
            if(!log.getOperation().equals("")){
                t++;
                pstmt.setString(t,"%"+log.getOperation()+"%");
            }
            try(ResultSet rst = pstmt.executeQuery()) {
                while (rst.next()) {
                    Auditlog log1=new Auditlog();
                    log1.setUname(rst.getString("user_name"));
                    log1.setOperation(rst.getString("user_operation"));
                    log1.setDescription(rst.getString("user_description"));
                    logs.add(log1);
                }
            }
            return logs;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public ArrayList<Auditlog> findAlllogs(){
        String sql="select * "
                +"from log ";
        ArrayList<Auditlog> logs=new ArrayList<>();
        try( Connection dbconn =getConnection();
             PreparedStatement pstmt = dbconn.prepareStatement(sql);
             ResultSet rst = pstmt.executeQuery()) {
            while (rst.next()) {
                Auditlog log1=new Auditlog();
                log1.setUname(rst.getString("user_name"));
                log1.setOperation(rst.getString("user_operation"));
                log1.setDescription(rst.getString("user_description"));
                logs.add(log1);
            }
            return logs;
        }catch (SQLException ne){
            ne.printStackTrace();
            return null;
        }
    }
    public List<Auditlog> searchLogs(String userName, String userOperation, String startDate, String endDate) {
        System.out.println("进入searchLogs");
        List<Auditlog> logs = new ArrayList<>();
        String sql = "SELECT * FROM log WHERE 1=1";

        if (userName != null && !userName.isEmpty()) {
            sql += " AND user_name LIKE ?";
        }
        if (userOperation != null && !userOperation.isEmpty()) {
            sql += " AND user_operation LIKE ?";
        }
        if (startDate != null && !startDate.isEmpty()) {
            sql += " AND created_at >= ?";
        }
        if (endDate != null && !endDate.isEmpty()) {
            sql += " AND created_at <= ?";
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            int paramIndex = 1;

            if (userName != null && !userName.isEmpty()) {
                stmt.setString(paramIndex++, "%" + userName + "%");
            }
            if (userOperation != null && !userOperation.isEmpty()) {
                stmt.setString(paramIndex++, "%" + userOperation + "%");
            }
            if (startDate != null && !startDate.isEmpty()) {
                stmt.setTimestamp(paramIndex++, Timestamp.valueOf(startDate + " 00:00:00"));
            }
            if (endDate != null && !endDate.isEmpty()) {
                stmt.setTimestamp(paramIndex++, Timestamp.valueOf(endDate + " 23:59:59"));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Auditlog log = new Auditlog();
                log.setUname(rs.getString("user_name"));
                log.setOperation(rs.getString("user_operation"));
                log.setDescription(rs.getString("user_description"));
                log.setCreatedAt(rs.getTimestamp("created_at"));
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }
}
