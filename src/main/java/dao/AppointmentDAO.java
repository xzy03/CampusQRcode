package dao;

import system.Appointment;
import system.DatabaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    public List<Appointment> searchAppointments(String applicantName, String applicantId, String applicationDate, String appointmentDate, String campus) {
        List<Appointment> appointments = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT * FROM appointments WHERE applicant_name = ? AND applicant_id = ? AND application_date = ? AND appointment_date = ? AND campus = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, applicantName);
            stmt.setString(2, applicantId);
            stmt.setString(3, applicationDate);
            stmt.setString(4, appointmentDate);
            stmt.setString(5, campus);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getString("applicant_name"),
                        rs.getString("applicant_id"),
                        rs.getString("application_date"),
                        rs.getString("appointment_date"),
                        rs.getString("campus")
                );
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtils.close(rs, stmt, conn);
        }

        return appointments;
    }

    private Connection getConnection() {
        // 你的获取连接的逻辑
        return null;
    }
}

