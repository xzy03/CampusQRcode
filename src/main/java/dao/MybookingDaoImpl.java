package dao;
import java.sql.*;
import java.util.ArrayList;
import beans.Mybooking;
import beans.Person;
import com.google.gson.Gson;

public class MybookingDaoImpl implements MybookingDao {
    public boolean addmybooking(Mybooking mybooking) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        String sql = "insert into mybooking values(?,?,?,?,?,?,?,?,?,?,?,'null','null')";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mybooking.getCampus());
            pstmt.setString(2, mybooking.getIntime());
            pstmt.setString(3, mybooking.getOuttime());
            pstmt.setString(4, mybooking.getunit());
            pstmt.setString(5, mybooking.getVehicle());
            pstmt.setString(6, mybooking.getVname());
            pstmt.setString(7, mybooking.getPerson().getId());
            pstmt.setString(8, mybooking.getPerson().getName());
            pstmt.setString(9, mybooking.getPerson().getphoneNumber());
            pstmt.setInt(10, mybooking.getNumber());
            Gson gson = new Gson();
            String friendsJson = gson.toJson(mybooking.getFriends());
            pstmt.setString(11, friendsJson);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //写一个根据主键id和intime查找数据库里数据的方法
    public Mybooking findByMain(String id, String intime) {
        Mybooking mybooking = new Mybooking();
        String sql = "select * from mybooking where visitor_id=? and intime=?";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, intime);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                mybooking.setCampus(rs.getString("campus"));
                mybooking.setIntime(rs.getString("intime"));
                mybooking.setOuttime(rs.getString("outtime"));
                mybooking.setunit(rs.getString("uint"));
                mybooking.setVehicle(rs.getString("vehicle"));
                mybooking.setVname(rs.getString("vname"));
                mybooking.setPerson(new Person(rs.getString("visitor_id"), rs.getString("visitor_name"), rs.getString("visitor_phoneNumber")));
                mybooking.setNumber(rs.getInt("friend_number"));
                Gson gson = new Gson();
                String friendsJson = rs.getString("follow_person");
                ArrayList<Person> friends = gson.fromJson(friendsJson, ArrayList.class);
                mybooking.setFriends(friends);
                mybooking.setQRcode(rs.getString("qrcode"));
                mybooking.setInvalidQRcode(rs.getString("invalid_qrcode"));
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return mybooking;
    }

    public ArrayList<Mybooking> findAllMybooking() {
        Mybooking mybooking = new Mybooking();
        ArrayList<Mybooking> mybookings = new ArrayList<Mybooking>();
        String sql = "select * from mybooking";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                mybooking.setCampus(rs.getString("campus"));
                mybooking.setIntime(rs.getString("intime"));
                mybooking.setOuttime(rs.getString("outtime"));
                mybooking.setunit(rs.getString("unit"));
                mybooking.setVehicle(rs.getString("vehicle"));
                mybooking.setVname(rs.getString("vname"));
                mybooking.setPerson(new Person(rs.getString("id"), rs.getString("name"), rs.getString("phoneNumber")));
                mybookings.add(mybooking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return mybookings;
    }
    //写一个修改数据库里数据的方法
    public boolean updatemybooking(Mybooking mybooking) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        String sql = "update mybooking set campus=?,intime=?,outtime=?,uint=?,vehicle=?,vname=?,visitor_id=?,visitor_name=?,visitor_phoneNumber=?,friend_number=?,follow_person=?,qrcode=?,invalid_qrcode=? where visitor_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mybooking.getCampus());
            pstmt.setString(2, mybooking.getIntime());
            pstmt.setString(3, mybooking.getOuttime());
            pstmt.setString(4, mybooking.getunit());
            pstmt.setString(5, mybooking.getVehicle());
            pstmt.setString(6, mybooking.getVname());
            pstmt.setString(7, mybooking.getPerson().getId());
            pstmt.setString(8, mybooking.getPerson().getName());
            pstmt.setString(9, mybooking.getPerson().getphoneNumber());
            pstmt.setInt(10, mybooking.getNumber());
            Gson gson = new Gson();
            String friendsJson = gson.toJson(mybooking.getFriends());
            pstmt.setString(11, friendsJson);
            pstmt.setString(12, mybooking.getQRcode());
            pstmt.setString(13, mybooking.getInvalidQRcode());
            pstmt.setString(14, mybooking.getPerson().getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}