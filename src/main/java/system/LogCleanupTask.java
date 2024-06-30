package system;

import dao.AdminDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogCleanupTask {

    public static void start() {
        Runnable cleanupTask = new Runnable() {
            public void run() {
                while (true) {
                    try {
                        AdminDAO dao=new AdminDAO();
                        try (Connection conn = dao.getConnection()) {
                            System.out.println("进入监听器");
                            String sql = "DELETE FROM log WHERE created_at < NOW() - INTERVAL '1 YEAR'";
                            System.out.println(sql);
                            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                                int rowsDeleted = stmt.executeUpdate();
                                System.out.println("Deleted " + rowsDeleted + " old log entries");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Thread.sleep(24 * 60 * 60 * 1000); // Sleep for 1 day in milliseconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread cleanupThread = new Thread(cleanupTask);
        cleanupThread.setDaemon(true); // Set as a daemon thread to not prevent JVM shutdown
        cleanupThread.start();
    }
}
