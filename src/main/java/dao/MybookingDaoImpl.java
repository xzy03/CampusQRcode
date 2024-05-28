package dao;
import java.sql.*;
import java.util.ArrayList;
import beans.Mybooking;
import beans.Person;
public class MybookingDaoImpl implements MybookingDao {
    public boolean addmybooking(Mybooking mybooking) {
        Connection conn = getConnection();
        PreparedStatement pstmt = null;
        String sql = "insert into mybooking values(?,?,?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mybooking.getCampus());
            pstmt.setString(2, mybooking.getIntime());
            pstmt.setString(3, mybooking.getOuttime());
            pstmt.setString(4, mybooking.getunit());
            pstmt.setString(5, mybooking.getVehicle());
            pstmt.setString(6, mybooking.getVname());
            pstmt.setString(7, mybooking.getFriend().getId());
            pstmt.setString(8, mybooking.getFriend().getName());
            pstmt.setString(9, mybooking.getFriend().getphoneNumber());
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

    public Mybooking findById(String id) {
        Mybooking mybooking = new Mybooking();
        String sql = "select * from mybooking where id=?";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                mybooking.setCampus(rs.getString("campus"));
                mybooking.setIntime(rs.getString("intime"));
                mybooking.setOuttime(rs.getString("outtime"));
                mybooking.setunit(rs.getString("unit"));
                mybooking.setVehicle(rs.getString("vehicle"));
                mybooking.setVname(rs.getString("vname"));
                mybooking.setFriend(new Person(rs.getString("id"), rs.getString("name"), rs.getString("phoneNumber")));
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
                mybooking.setFriend(new Person(rs.getString("id"), rs.getString("name"), rs.getString("phoneNumber")));
                mybookings.add(mybooking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return mybookings;
    }
}