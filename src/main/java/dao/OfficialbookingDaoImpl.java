package dao;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import beans.Mybooking;
import beans.Officialbooking;
import beans.Person;
import com.google.gson.Gson;

import system.SM4Util;

public class OfficialbookingDaoImpl implements OfficialbookingDao {
    public boolean addofficialbooking(Officialbooking officialbooking) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        String sql = "insert into officialbooking values(?,?,?,?,?,?,?,?,?,?,?,null,null,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, officialbooking.getCampus());
            pstmt.setString(2, officialbooking.getIntime());
            pstmt.setString(3, officialbooking.getOuttime());
            pstmt.setString(4, officialbooking.getunit());
            pstmt.setString(5, officialbooking.getVehicle());
            pstmt.setString(6, officialbooking.getVname());
            pstmt.setString(7, officialbooking.getId());
            pstmt.setString(8, officialbooking.getName());
            pstmt.setString(9, officialbooking.getphoneNumber());
            pstmt.setInt(10, officialbooking.getNumber());
            Gson gson = new Gson();
            String friendsJson = gson.toJson(officialbooking.getFriends());
            pstmt.setString(11, friendsJson);
            pstmt.setString(12, officialbooking.getdepartment());
            pstmt.setString(13, officialbooking.getReceptionist());
            pstmt.setString(14, officialbooking.getReason());
            pstmt.setString(15, "未审核");
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

    public Officialbooking findByMain(String id, String intime) {
        Officialbooking officialbooking = new Officialbooking();
        String sql = "select * from officialbooking where visitor_id=? and intime=?";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, intime);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                officialbooking.setCampus(rs.getString("campus"));
                officialbooking.setIntime(rs.getString("intime"));
                officialbooking.setOuttime(rs.getString("outtime"));
                officialbooking.setunit(rs.getString("uint"));
                officialbooking.setVehicle(rs.getString("vehicle"));
                officialbooking.setVname(rs.getString("vname"));
                officialbooking.setId(rs.getString("visitor_id"));
                officialbooking.setName(rs.getString("visitor_name"));
                officialbooking.setphoneNumber(rs.getString("visitor_phoneNumber"));
                officialbooking.setNumber(rs.getInt("friend_number"));
                Gson gson = new Gson();
                String friendsJson = rs.getString("follow_person");
                ArrayList<Person> friends = gson.fromJson(friendsJson, ArrayList.class);
                officialbooking.setFriends(friends);
                officialbooking.setQRcode(rs.getString("qrcode"));
                officialbooking.setInvalidQRcode(rs.getString("invalid_qrcode"));
                officialbooking.setdepartment(rs.getString("department"));
                officialbooking.setReceptionist(rs.getString("receptionist"));
                officialbooking.setReason(rs.getString("reason"));
                officialbooking.setPermit(rs.getString("permit"));

            }
            else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return officialbooking;
    }

    public boolean updateofficialbooking(Officialbooking officialbooking) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        String sql = "update officialbooking set campus=?,intime=?,outtime=?,uint=?,vehicle=?,vname=?,visitor_id=?,visitor_name=?,visitor_phoneNumber=?,friend_number=?,follow_person=?,qrcode=?,invalid_qrcode=?,department=?,receptionist=?,reason=?,permit=? where visitor_id=? and intime=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, officialbooking.getCampus());
            pstmt.setString(2, officialbooking.getIntime());
            pstmt.setString(3, officialbooking.getOuttime());
            pstmt.setString(4, officialbooking.getunit());
            pstmt.setString(5, officialbooking.getVehicle());
            pstmt.setString(6, officialbooking.getVname());
            pstmt.setString(7, officialbooking.getId());
            pstmt.setString(8, officialbooking.getName());
            pstmt.setString(9, officialbooking.getphoneNumber());
            pstmt.setInt(10, officialbooking.getNumber());
            Gson gson = new Gson();
            String friendsJson = gson.toJson(officialbooking.getFriends());
            pstmt.setString(11, friendsJson);
            pstmt.setString(12, officialbooking.getQRcode());
            pstmt.setString(13, officialbooking.getInvalidQRcode());
            pstmt.setString(14, officialbooking.getdepartment());
            pstmt.setString(15, officialbooking.getReceptionist());
            pstmt.setString(16, officialbooking.getReason());
            pstmt.setString(17, officialbooking.getPermit());
            pstmt.setString(18, officialbooking.getId());
            pstmt.setString(19, officialbooking.getIntime());
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

    public Officialbooking query_find(String id, String name, String phone) {
        Officialbooking officialbooking = new Officialbooking();
        String sql = "select * from officialbooking where visitor_id=? and visitor_name=? and visitor_phoneNumber=?";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            ResultSet rs = pstmt.executeQuery();
            //将当前的时间转换为字符串2024-06-04这样的格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format(new java.util.Date());
            System.out.println(time);
            boolean havecode = false;
            if(rs.next()){
                havecode = true;
            }
            if(!havecode){
                return null;
            }
            rs.previous();
            while(rs.next()&&(!time.equals(rs.getString("intime"))||rs.getString("permit").equals("未审核"))){
                boolean success = time.equals(rs.getString("intime"));
                System.out.println(success);
                System.out.println(rs.getString("intime"));
                System.out.println("没有找到公务预约");
            }
            rs.previous();
            if (rs.next()) {
                officialbooking.setCampus(rs.getString("campus"));
                officialbooking.setIntime(rs.getString("intime"));
                officialbooking.setOuttime(rs.getString("outtime"));
                officialbooking.setunit(rs.getString("uint"));
                officialbooking.setVehicle(rs.getString("vehicle"));
                officialbooking.setVname(rs.getString("vname"));
                officialbooking.setId(rs.getString("visitor_id"));
                officialbooking.setName(rs.getString("visitor_name"));
                officialbooking.setphoneNumber(rs.getString("visitor_phoneNumber"));
                officialbooking.setNumber(rs.getInt("friend_number"));
                Gson gson = new Gson();
                String friendsJson = rs.getString("follow_person");
                ArrayList<Person> friends = gson.fromJson(friendsJson, ArrayList.class);
                officialbooking.setFriends(friends);
                officialbooking.setQRcode(rs.getString("qrcode"));
                officialbooking.setInvalidQRcode(rs.getString("invalid_qrcode"));
                officialbooking.setdepartment(rs.getString("department"));
                officialbooking.setReceptionist(rs.getString("receptionist"));
                officialbooking.setReason(rs.getString("reason"));
                officialbooking.setPermit(rs.getString("permit"));
            }
            else {
                rs.last();
                officialbooking.setCampus(rs.getString("campus"));
                officialbooking.setIntime(rs.getString("intime"));
                officialbooking.setOuttime(rs.getString("outtime"));
                officialbooking.setunit(rs.getString("uint"));
                officialbooking.setVehicle(rs.getString("vehicle"));
                officialbooking.setVname(rs.getString("vname"));
                officialbooking.setId(rs.getString("visitor_id"));
                officialbooking.setName(rs.getString("visitor_name"));
                officialbooking.setphoneNumber(rs.getString("visitor_phoneNumber"));
                officialbooking.setNumber(rs.getInt("friend_number"));
                Gson gson = new Gson();
                String friendsJson = rs.getString("follow_person");
                ArrayList<Person> friends = gson.fromJson(friendsJson, ArrayList.class);
                officialbooking.setFriends(friends);
                officialbooking.setQRcode(rs.getString("qrcode"));
                officialbooking.setInvalidQRcode(rs.getString("invalid_qrcode"));
                officialbooking.setdepartment(rs.getString("department"));
                officialbooking.setReceptionist(rs.getString("receptionist"));
                officialbooking.setReason(rs.getString("reason"));
                officialbooking.setPermit(rs.getString("permit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return officialbooking;
    }

    public ArrayList<Officialbooking> findAllQueryOfficialbooking(String id, String name, String phone) {
        ArrayList<Officialbooking> officialbookings = new ArrayList<>();
        String sql = "select * from officialbooking where visitor_id=? and visitor_name=? and visitor_phoneNumber=?";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String id1 = SM4Util.decrypt(rs.getString("visitor_id"));
                String phone1 = SM4Util.decrypt(rs.getString("visitor_phoneNumber"));
                Officialbooking officialbooking = new Officialbooking();
                officialbooking.setCampus(rs.getString("campus"));
                officialbooking.setIntime(rs.getString("intime"));
                officialbooking.setOuttime(rs.getString("outtime"));
                officialbooking.setunit(rs.getString("uint"));
                officialbooking.setVehicle(rs.getString("vehicle"));
                officialbooking.setVname(rs.getString("vname"));
                officialbooking.setId(id1);
                officialbooking.setName(rs.getString("visitor_name"));
                officialbooking.setphoneNumber(phone1);
                officialbooking.setNumber(rs.getInt("friend_number"));
                Gson gson = new Gson();
                String friendsJson = rs.getString("follow_person");
                ArrayList<Person> friends = gson.fromJson(friendsJson, ArrayList.class);
                officialbooking.setFriends(friends);
                officialbooking.setQRcode(rs.getString("qrcode"));
                officialbooking.setInvalidQRcode(rs.getString("invalid_qrcode"));
                officialbooking.setdepartment(rs.getString("department"));
                officialbooking.setReceptionist(rs.getString("receptionist"));
                officialbooking.setReason(rs.getString("reason"));
                officialbooking.setPermit(rs.getString("permit"));
                officialbookings.add(officialbooking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return officialbookings;
    }
}