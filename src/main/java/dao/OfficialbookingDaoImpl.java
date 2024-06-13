package dao;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import beans.Mybooking;
import beans.Officialbooking;
import beans.Person;
import com.google.gson.Gson;

import system.SM4Util;
import system.StatisticsOfficial;
import system.DatabaseUtils;

public class OfficialbookingDaoImpl implements OfficialbookingDao {
    public boolean addofficialbooking(Officialbooking officialbooking) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        String sql = "insert into officialbooking values(?,?,?,?,?,?,?,?,?,?,?,?,null,null,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, officialbooking.getApplytime());
            pstmt.setString(2, officialbooking.getCampus());
            pstmt.setString(3, officialbooking.getIntime());
            pstmt.setString(4, officialbooking.getOuttime());
            pstmt.setString(5, officialbooking.getunit());
            pstmt.setString(6, officialbooking.getVehicle());
            pstmt.setString(7, officialbooking.getVname());
            pstmt.setString(8, officialbooking.getId());
            pstmt.setString(9, officialbooking.getName());
            pstmt.setString(10, officialbooking.getphoneNumber());
            pstmt.setInt(11, officialbooking.getNumber());
            Gson gson = new Gson();
            String friendsJson = gson.toJson(officialbooking.getFriends());
            pstmt.setString(12, friendsJson);
            pstmt.setString(13, officialbooking.getdepartment());
            pstmt.setString(14, officialbooking.getReceptionist());
            pstmt.setString(15, officialbooking.getReason());
            pstmt.setString(16, "待审");
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
                officialbooking.setApplytime(rs.getString("applytime"));
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
        String sql = "update officialbooking set applytime=?,campus=?,intime=?,outtime=?,uint=?,vehicle=?,vname=?,visitor_id=?,visitor_name=?,visitor_phoneNumber=?,friend_number=?,follow_person=?,qrcode=?,invalid_qrcode=?,department=?,receptionist=?,reason=?,permit=? where visitor_id=? and intime=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, officialbooking.getApplytime());
            pstmt.setString(2, officialbooking.getCampus());
            pstmt.setString(3, officialbooking.getIntime());
            pstmt.setString(4, officialbooking.getOuttime());
            pstmt.setString(5, officialbooking.getunit());
            pstmt.setString(6, officialbooking.getVehicle());
            pstmt.setString(7, officialbooking.getVname());
            pstmt.setString(8, officialbooking.getId());
            pstmt.setString(9, officialbooking.getName());
            pstmt.setString(10, officialbooking.getphoneNumber());
            pstmt.setInt(11, officialbooking.getNumber());
            Gson gson = new Gson();
            String friendsJson = gson.toJson(officialbooking.getFriends());
            pstmt.setString(12, friendsJson);
            pstmt.setString(13, officialbooking.getQRcode());
            pstmt.setString(14, officialbooking.getInvalidQRcode());
            pstmt.setString(15, officialbooking.getdepartment());
            pstmt.setString(16, officialbooking.getReceptionist());
            pstmt.setString(17, officialbooking.getReason());
            pstmt.setString(18, officialbooking.getPermit());
            pstmt.setString(19, officialbooking.getId());
            pstmt.setString(20, officialbooking.getIntime());
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
            while(rs.next()&&(!time.equals(rs.getString("intime"))||rs.getString("permit").equals("待审"))){
                boolean success = time.equals(rs.getString("intime"));
                System.out.println(success);
                System.out.println(rs.getString("intime"));
                System.out.println("没有找到公务预约");
            }
            rs.previous();
            if (rs.next()) {
                officialbooking.setApplytime(rs.getString("applytime"));
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
                officialbooking.setApplytime(rs.getString("applytime"));
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
                officialbooking.setApplytime(rs.getString("applytime"));
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

    public List<Officialbooking> searchBookings(String applyTime, String entryTime, String campus, String unit, String name, String idCard, String department, String receptionist, String permit) {
        List<Officialbooking> bookings = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM officialbooking WHERE 1=1");

            // 构建查询条件
            if (applyTime != null && !applyTime.isEmpty()) {
                sqlBuilder.append(" AND applytime = ?");
            }
            if (entryTime != null && !entryTime.isEmpty()) {
                sqlBuilder.append(" AND intime = ?");
            }
            if (campus != null && !campus.isEmpty()) {
                sqlBuilder.append(" AND campus LIKE ?");
            }
            if (unit != null && !unit.isEmpty()) {
                sqlBuilder.append(" AND uint LIKE ?");
            }
            if (name != null && !name.isEmpty()) {
                sqlBuilder.append(" AND visitor_name LIKE ?");
            }
            if (idCard != null && !idCard.isEmpty()) {
                sqlBuilder.append(" AND visitor_id LIKE ?");
            }
            if(department != null && !department.isEmpty()){
                sqlBuilder.append(" AND department LIKE ?");
            }
            if(receptionist != null && !receptionist.isEmpty()){
                sqlBuilder.append(" AND receptionist LIKE ?");
            }
            if(permit != null && !permit.isEmpty()){
                sqlBuilder.append(" AND permit LIKE ?");
            }

            stmt = conn.prepareStatement(sqlBuilder.toString());
            int parameterIndex = 1;
            if (applyTime != null && !applyTime.isEmpty()) {
                stmt.setString(parameterIndex++, applyTime);
            }
            if (entryTime != null && !entryTime.isEmpty()) {
                stmt.setString(parameterIndex++, entryTime);
            }
            if (campus != null && !campus.isEmpty()) {
                stmt.setString(parameterIndex++, "%" + campus + "%");
            }
            if (unit != null && !unit.isEmpty()) {
                stmt.setString(parameterIndex++, "%" + unit + "%");
            }
            if (name != null && !name.isEmpty()) {
                stmt.setString(parameterIndex++, "%" + name + "%");
            }
            if (idCard != null && !idCard.isEmpty()) {
                stmt.setString(parameterIndex++, "%" + idCard + "%");
            }
            if(department != null && !department.isEmpty()){
                stmt.setString(parameterIndex++, "%" + department + "%");
            }
            if(receptionist != null && !receptionist.isEmpty()){
                stmt.setString(parameterIndex++, "%" + receptionist + "%");
            }
            if(permit != null && !permit.isEmpty()){
                stmt.setString(parameterIndex++, permit);
            }
            System.out.println(stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                //System.out.println("查到一次");
                Officialbooking booking = new Officialbooking(
                        rs.getString("visitor_name"),
                        rs.getString("visitor_id"),
                        rs.getString("visitor_phonenumber"),
                        rs.getString("campus"),
                        rs.getString("intime"),
                        rs.getString("outtime"),
                        rs.getString("uint"),
                        rs.getString("vehicle"),
                        rs.getString("vname"),
                        rs.getInt("friend_number"),
                        rs.getString("follow_person"),
                        rs.getString("QRcode"),
                        rs.getString("invalid_QRcode"),
                        rs.getString("department"),
                        rs.getString("receptionist"),
                        rs.getString("reason"),
                        rs.getString("permit"),
                        rs.getString("applytime")
                );
                bookings.add(booking);
            }
            //System.out.println(bookings.size());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(rs, stmt, conn);
        }

        return bookings;
    }

    public List<StatisticsOfficial> getStatistics(String applyMonth, String entryMonth, String campus, String department) {
        List<StatisticsOfficial> statisticsPublicList = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT COUNT(*) as reservationCount, SUM(friend_number + 1) as personCount");
        sql.append(" FROM officialbooking WHERE 1=1");

        if (applyMonth != null && !applyMonth.isEmpty()) {
            sql.append(" AND applytime LIKE ?");
        }
        if (entryMonth != null && !entryMonth.isEmpty()) {
            sql.append(" AND intime LIKE ?");
        }
        if (campus != null && !campus.isEmpty()) {
            sql.append(" AND campus LIKE ?");
        }
        if (department != null && !department.isEmpty()) {
            sql.append(" AND department LIKE ?");
        }

        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;
            if (applyMonth != null && !applyMonth.isEmpty()) {
                stmt.setString(index++, applyMonth+"-%");
            }
            else{
                applyMonth="全部";
            }
            if (entryMonth != null && !entryMonth.isEmpty()) {
                stmt.setString(index++, entryMonth+"-%");
            }
            else{
                entryMonth="全部";
            }
            if (campus != null && !campus.isEmpty()) {
                stmt.setString(index++, "%"+campus+"%");
            }
            else{
                campus="全部";
            }
            if (department != null && !department.isEmpty()) {
                stmt.setString(index++, "%"+department+"%");
            }
            else{
                department="全部";
            }
            System.out.println(stmt);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    StatisticsOfficial stat = new StatisticsOfficial();
                    stat.setApplyMonth(applyMonth);
                    stat.setEntryMonth(entryMonth);
                    stat.setCampus(campus);
                    stat.setDepartment(department);
                    stat.setReservationCount(rs.getInt("reservationCount"));
                    stat.setPersonCount(rs.getInt("personCount"));
                    statisticsPublicList.add(stat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statisticsPublicList;
    }



}