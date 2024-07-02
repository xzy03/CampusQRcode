package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import beans.Mybooking;
import beans.Person;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import system.*;

public class MybookingDaoImpl implements MybookingDao {
    public boolean addmybooking(Mybooking mybooking) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        String sql = "insert into mybooking values(?,?,?,?,?,?,?,?,?,?,?,?,null,null)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mybooking.getApplytime());
            pstmt.setString(2, mybooking.getCampus());
            pstmt.setString(3, mybooking.getIntime());
            pstmt.setString(4, mybooking.getOuttime());
            pstmt.setString(5, mybooking.getunit());
            pstmt.setString(6, mybooking.getVehicle());
            pstmt.setString(7, mybooking.getVname());
            pstmt.setString(8, mybooking.getId());
            pstmt.setString(9, mybooking.getName());
            pstmt.setString(10, mybooking.getphoneNumber());
            pstmt.setInt(11, mybooking.getNumber());
            Gson gson = new Gson();
            String friendsJson = gson.toJson(mybooking.getFriends());
            pstmt.setString(12, friendsJson);
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
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, intime);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                mybooking.setApplytime(rs.getString("applytime"));
                mybooking.setCampus(rs.getString("campus"));
                mybooking.setIntime(rs.getString("intime"));
                mybooking.setOuttime(rs.getString("outtime"));
                mybooking.setunit(rs.getString("uint"));
                mybooking.setVehicle(rs.getString("vehicle"));
                mybooking.setVname(rs.getString("vname"));
                mybooking.setId(rs.getString("visitor_id"));
                mybooking.setName(rs.getString("visitor_name"));
                mybooking.setphoneNumber(rs.getString("visitor_phoneNumber"));
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
        }finally {
            try {
                pstmt.close();
                conn.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mybooking;
    }
    public Mybooking query_find(String id, String name, String phone) {
        Mybooking mybooking = new Mybooking();
        System.out.println("1111");
        String sql = "select * from mybooking where visitor_id=? and visitor_name=? and visitor_phoneNumber=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            System.out.println("66666");
            conn = getConnection();
            System.out.println("77777");
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            System.out.println("555555");
            rs = pstmt.executeQuery();
            //将当前的时间转换为字符串2024-06-04这样的格式
            System.out.println("3333");
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
            System.out.println("44444");
            rs.previous();
            System.out.println("2222");
            while(rs.next()&&!time.equals(rs.getString("intime"))){
                boolean success = time.equals(rs.getString("intime"));
                System.out.println(success);
                System.out.println(rs.getString("intime"));
                System.out.println("没有找到");
            }
            rs.previous();
            if (rs.next()) {
                mybooking.setApplytime(rs.getString("applytime"));
                mybooking.setCampus(rs.getString("campus"));
                mybooking.setIntime(rs.getString("intime"));
                mybooking.setOuttime(rs.getString("outtime"));
                mybooking.setunit(rs.getString("uint"));
                mybooking.setVehicle(rs.getString("vehicle"));
                mybooking.setVname(rs.getString("vname"));
                mybooking.setId(rs.getString("visitor_id"));
                mybooking.setName(rs.getString("visitor_name"));
                mybooking.setphoneNumber(rs.getString("visitor_phoneNumber"));
                mybooking.setNumber(rs.getInt("friend_number"));
                Gson gson = new Gson();
                String friendsJson = rs.getString("follow_person");
                ArrayList<Person> friends = gson.fromJson(friendsJson, ArrayList.class);
                mybooking.setFriends(friends);
                mybooking.setQRcode(rs.getString("qrcode"));
                mybooking.setInvalidQRcode(rs.getString("invalid_qrcode"));
            }
            else {
                rs.last();
                mybooking.setApplytime(rs.getString("applytime"));
                mybooking.setCampus(rs.getString("campus"));
                mybooking.setIntime(rs.getString("intime"));
                mybooking.setOuttime(rs.getString("outtime"));
                mybooking.setunit(rs.getString("uint"));
                mybooking.setVehicle(rs.getString("vehicle"));
                mybooking.setVname(rs.getString("vname"));
                mybooking.setId(rs.getString("visitor_id"));
                mybooking.setName(rs.getString("visitor_name"));
                mybooking.setphoneNumber(rs.getString("visitor_phoneNumber"));
                mybooking.setNumber(rs.getInt("friend_number"));
                Gson gson = new Gson();
                String friendsJson = rs.getString("follow_person");
                ArrayList<Person> friends = gson.fromJson(friendsJson, ArrayList.class);
                mybooking.setFriends(friends);
                mybooking.setQRcode(rs.getString("qrcode"));
                mybooking.setInvalidQRcode(rs.getString("invalid_qrcode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                pstmt.close();
                conn.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mybooking;
    }
    public ArrayList<Mybooking> findAllQueryMybooking(String id, String name, String phone) {
        ArrayList<Mybooking> mybookings = new ArrayList<>();
        String sql = "select * from mybooking where visitor_id=? and visitor_name=? and visitor_phoneNumber=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, phone);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String id1 = SM4Util.decrypt(rs.getString("visitor_id"));
                String phone1 = SM4Util.decrypt(rs.getString("visitor_phoneNumber"));
                Mybooking mybooking = new Mybooking();
                mybooking.setApplytime(rs.getString("applytime"));
                mybooking.setCampus(rs.getString("campus"));
                mybooking.setIntime(rs.getString("intime"));
                mybooking.setOuttime(rs.getString("outtime"));
                mybooking.setunit(rs.getString("uint"));
                mybooking.setVehicle(rs.getString("vehicle"));
                mybooking.setVname(rs.getString("vname"));
                mybooking.setId(id1);
                mybooking.setName(rs.getString("visitor_name"));
                mybooking.setphoneNumber(phone1);
                mybooking.setNumber(rs.getInt("friend_number"));
                Gson gson = new Gson();
                String friendsJson = rs.getString("follow_person");
                ArrayList<Person> friends = gson.fromJson(friendsJson, ArrayList.class);
                mybooking.setFriends(friends);
                mybooking.setQRcode(rs.getString("qrcode"));
                mybooking.setInvalidQRcode(rs.getString("invalid_qrcode"));
                mybookings.add(mybooking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                pstmt.close();
                conn.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mybookings;
    }
    //写一个修改数据库里数据的方法
    public boolean updatemybooking(Mybooking mybooking) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        String sql = "update mybooking set applytime=?,campus=?,intime=?,outtime=?,uint=?,vehicle=?,vname=?,visitor_id=?,visitor_name=?,visitor_phoneNumber=?,friend_number=?,follow_person=?,qrcode=?,invalid_qrcode=? where visitor_id=? and intime=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mybooking.getApplytime());
            pstmt.setString(2, mybooking.getCampus());
            pstmt.setString(3, mybooking.getIntime());
            pstmt.setString(4, mybooking.getOuttime());
            pstmt.setString(5, mybooking.getunit());
            pstmt.setString(6, mybooking.getVehicle());
            pstmt.setString(7, mybooking.getVname());
            pstmt.setString(8, mybooking.getId());
            pstmt.setString(9, mybooking.getName());
            pstmt.setString(10, mybooking.getphoneNumber());
            pstmt.setInt(11, mybooking.getNumber());
            Gson gson = new Gson();
            String friendsJson = gson.toJson(mybooking.getFriends());
            pstmt.setString(12, friendsJson);
            pstmt.setString(13, mybooking.getQRcode());
            pstmt.setString(14, mybooking.getInvalidQRcode());
            pstmt.setString(15, mybooking.getId());
            pstmt.setString(16, mybooking.getIntime());
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
    public List<Mybooking> searchBookings(String applyTime, String entryTime, String campus, String unit, String name, String idCard) {
        List<Mybooking> bookings = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM mybooking WHERE 1=1");

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
            System.out.println(stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("查到东西");
                StringBuffer viewid =new StringBuffer(SM4Util.decrypt(rs.getString("visitor_id")));
                StringBuffer phonenumber =new StringBuffer(SM4Util.decrypt(rs.getString("visitor_phonenumber")));
                for(int i=1;i<viewid.length() - 1;i++) {
                    viewid.replace(i, i+1, "*");
                }
                for(int i=3;i<phonenumber.length()-4;i++){
                    phonenumber.replace(i,i+1,"*");
                }
                System.out.println(viewid);
                System.out.println(phonenumber);
                String modifiedJsonString = null;
                String fp = rs.getString("follow_person");
                if(fp!=null){
                    Gson gson = new Gson();
                    Type personListType = new TypeToken<List<Person>>() {}.getType();
                    List<Person> personList = gson.fromJson(fp, personListType);
                    for (Person person : personList) {
                        //输出所有的person
                        StringBuffer modifiedId = new StringBuffer(SM4Util.decrypt(person.getId()));
                        StringBuffer modifiedPhonenumber = new StringBuffer(SM4Util.decrypt(person.getphoneNumber()));
                        // 在这里对每个id进行修改
                        for(int i=1;i<modifiedId.length() - 1;i++) {
                            modifiedId.replace(i, i+1, "*");
                        }
                        for(int i=3;i<modifiedPhonenumber.length()-4;i++){
                            modifiedPhonenumber.replace(i,i+1,"*");
                        }
                        person.setId(modifiedId.toString());
                        person.setphoneNumber(modifiedPhonenumber.toString());
                    }
                    modifiedJsonString = gson.toJson(personList);
                }

                Mybooking booking = new Mybooking(
                        rs.getString("visitor_name"),
                        viewid.toString(),
                        phonenumber.toString(),
                        rs.getString("campus"),
                        rs.getString("intime"),
                        rs.getString("outtime"),
                        rs.getString("uint"),
                        rs.getString("vehicle"),
                        rs.getString("vname"),
                        rs.getInt("friend_number"),
                        modifiedJsonString,
                        rs.getString("QRcode"),
                        rs.getString("invalid_QRcode"),
                        rs.getString("applytime")
                );
                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(rs, stmt, conn);
        }

        return bookings;
    }

    public List<StatisticsPublic> getStatistics(String applyMonth, String entryMonth, String campus) {
        List<StatisticsPublic> statisticsPublicList = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT COUNT(*) as reservationCount, SUM(friend_number + 1) as personCount");
        sql.append(" FROM mybooking WHERE 1=1");

        if (applyMonth != null && !applyMonth.isEmpty()) {
            sql.append(" AND applytime LIKE ?");
        }
        if (entryMonth != null && !entryMonth.isEmpty()) {
            sql.append(" AND intime LIKE ?");
        }
        if (campus != null && !campus.isEmpty()) {
            sql.append(" AND campus LIKE ?");
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
            System.out.println(stmt);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    StatisticsPublic stat = new StatisticsPublic();
                    stat.setApplyMonth(applyMonth);
                    stat.setEntryMonth(entryMonth);
                    stat.setCampus(campus);
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