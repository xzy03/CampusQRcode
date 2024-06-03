package dao;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import beans.Person;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.WebServlet;
import beans.Mybooking;
import dao.MybookingDao;
import dao.MybookingDaoImpl;
import com.example.mytest.QRCodeGenerator;
import com.google.gson.Gson;

@WebServlet("/AddMybookingServlet")
public class AddMybookingServlet extends HttpServlet{
    private Gson gson = new Gson();
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

        MybookingDao mybookingDao = new MybookingDaoImpl();
        Mybooking mybooking = new Mybooking();
        Person person = new Person();
        try{
            mybooking.setCampus(request.getParameter("campus"));
            mybooking.setIntime(request.getParameter("intime"));
            mybooking.setOuttime(request.getParameter("outtime"));
            mybooking.setunit(request.getParameter("unit"));
            mybooking.setVehicle(request.getParameter("vehicle"));
            mybooking.setVname(request.getParameter("vname"));
            person.setId(request.getParameter("id"));
            person.setName(request.getParameter("name"));
            person.setphoneNumber(request.getParameter("phoneNumber"));
            mybooking.setPerson(person);
            mybooking.setNumber(Integer.parseInt(request.getParameter("pnumber")));

            String[] friendNames = request.getParameterValues("friendName");
            String[] friendIds = request.getParameterValues("friendId");
            String[] friendPhoneNumbers = request.getParameterValues("friendPhoneNumber");
            List<Person> friends = new ArrayList<>();
            for (int i = 0; i < friendNames.length; i++) {
                Person friend = new Person();
                friend.setName(friendNames[i]);
                friend.setId(friendIds[i]);
                friend.setphoneNumber(friendPhoneNumbers[i]);
                friends.add(friend);
            }
            mybooking.setFriends(friends);
            Mybooking mybooking_check = new Mybooking();
            mybooking_check = mybookingDao.findByMain(mybooking.getPerson().getId(),mybooking.getIntime());
            if(mybooking_check!=null){
                HashMap<String, String> formData = new HashMap<>();
                formData.put("campus", mybooking.getCampus());
                formData.put("intime", mybooking.getIntime());
                formData.put("outtime", mybooking.getOuttime());
                formData.put("unit", mybooking.getunit());
                formData.put("vehicle", mybooking.getVehicle());
                formData.put("vname", mybooking.getVname());
                formData.put("id", mybooking.getPerson().getId());
                formData.put("name", mybooking.getPerson().getName());
                formData.put("phoneNumber", mybooking.getPerson().getphoneNumber());
                formData.put("number", String.valueOf(mybooking.getNumber()));
                formData.put("friends", gson.toJson(mybooking.getFriends())); // 添加friends列表的JSON字符串
                QRCodeGenerator.generateQRCode(formData, "C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode\\" + mybooking.getPerson().getId() +mybooking.getIntime()+ ".png");
                QRCodeGenerator.generateInvalidQRCode("C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode\\" + mybooking.getPerson().getId() + "invalid.png");
                mybooking.setQRcode("QRCode/" + mybooking.getPerson().getId() +mybooking.getCampus()+mybooking.getIntime()+ ".png");
                mybooking.setInvalidQRcode("QRCode/" + mybooking.getPerson().getId() + "invalid.png");
                boolean successupdate_check = mybookingDao.updatemybooking(mybooking);
                return;
            }
            boolean success = mybookingDao.addmybooking(mybooking);
            if (success) {
                System.out.println("添加成功");
                HashMap<String, String> formData = new HashMap<>();
                formData.put("campus", mybooking.getCampus());
                formData.put("intime", mybooking.getIntime());
                formData.put("outtime", mybooking.getOuttime());
                formData.put("unit", mybooking.getunit());
                formData.put("vehicle", mybooking.getVehicle());
                formData.put("vname", mybooking.getVname());
                formData.put("id", mybooking.getPerson().getId());
                formData.put("name", mybooking.getPerson().getName());
                formData.put("phoneNumber", mybooking.getPerson().getphoneNumber());
                formData.put("number", String.valueOf(mybooking.getNumber()));
                formData.put("friends", gson.toJson(mybooking.getFriends())); // 添加friends列表的JSON字符串
                QRCodeGenerator.generateQRCode(formData, "C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode\\" + mybooking.getPerson().getId() +mybooking.getIntime()+ ".png");
                QRCodeGenerator.generateInvalidQRCode("C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode\\" + mybooking.getPerson().getId() + "invalid.png");
                mybooking.setQRcode("QRCode/" + mybooking.getPerson().getId() +mybooking.getCampus()+mybooking.getIntime()+ ".png");
                mybooking.setInvalidQRcode("QRCode/" + mybooking.getPerson().getId() + "invalid.png");
                boolean successupdate = mybookingDao.updatemybooking(mybooking);
            } else {
                System.out.println("添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
