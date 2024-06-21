package dao;

import beans.Auditlog;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
