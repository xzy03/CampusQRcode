package system;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.*;

@WebServlet("/updateOfficialBooking")
public class UpdateOfficialBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求中读取数据
        JsonObject jsonObject = new Gson().fromJson(request.getReader(), JsonObject.class);
        String id = jsonObject.get("id").getAsString();
        String permit = jsonObject.get("permit").getAsString();
        String inti = jsonObject.get("inti").getAsString();
        System.out.println(id);
        System.out.println(permit);
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean updateSuccess = false;

        try {
            OfficialbookingDaoImpl officialbookingDaoImpl = new OfficialbookingDaoImpl();
            conn = officialbookingDaoImpl.getConnection();
            String sql = "UPDATE officialbooking SET permit = ? WHERE visitor_id = ? AND intime = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, permit);
            stmt.setString(2, id);
            stmt.setString( 3,inti);
            System.out.println(stmt);
            updateSuccess = stmt.executeUpdate() > 0;
            System.out.println(updateSuccess);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dao.close(null, stmt, conn);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("success", updateSuccess);
        out.print(new Gson().toJson(jsonResponse));
        out.flush();
    }
}
