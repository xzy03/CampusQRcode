package dao;
import java.io.*;
import java.util.Date;

import beans.Person;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.WebServlet;
import beans.Mybooking;
import dao.MybookingDao;
import dao.MybookingDaoImpl;

@WebServlet("/AddMybookingServlet")
public class AddMybookingServlet extends HttpServlet{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        MybookingDao mybookingDao = new MybookingDaoImpl();
        Mybooking mybooking = new Mybooking();
        Person friend = new Person();
        try{
            mybooking.setCampus(request.getParameter("campus"));
            mybooking.setIntime(request.getParameter("intime"));
            mybooking.setOuttime(request.getParameter("outtime"));
            mybooking.setunit(request.getParameter("unit"));
            mybooking.setVehicle(request.getParameter("vehicle"));
            mybooking.setVname(request.getParameter("vname"));
            friend.setId(request.getParameter("id"));
            friend.setName(request.getParameter("name"));
            friend.setphoneNumber(request.getParameter("phoneNumber"));
            mybooking.setFriend(friend);
            boolean success = mybookingDao.addmybooking(mybooking);
            if (success) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
