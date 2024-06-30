package phone;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import beans.Officialbooking;
import beans.Person;
import dao.OfficialbookingDao;
import dao.OfficialbookingDaoImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.WebServlet;
import beans.Mybooking;
import dao.MybookingDao;
import dao.MybookingDaoImpl;
import com.example.mytest.QRCodeGenerator;
import com.google.gson.Gson;
import system.SM4Util;

@WebServlet("/AddOfficailbookingServlet")
public class AddOfficailbookingServlet extends HttpServlet{
    private Gson gson = new Gson();
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        OfficialbookingDao officialbookingDao = new OfficialbookingDaoImpl();
        Officialbooking officialbooking = new Officialbooking();
        try{
            String id = SM4Util.encrypt(request.getParameter("id"));
            String phoneNumber = SM4Util.encrypt(request.getParameter("phoneNumber"));
            officialbooking.setApplytime(request.getParameter("applytime"));
            officialbooking.setCampus(request.getParameter("campus"));
            officialbooking.setIntime(request.getParameter("intime"));
            officialbooking.setOuttime(request.getParameter("outtime"));
            officialbooking.setunit(request.getParameter("unit"));
            officialbooking.setVehicle(request.getParameter("vehicle"));
            officialbooking.setVname(request.getParameter("vname"));
            officialbooking.setId(id);
            officialbooking.setName(request.getParameter("name"));
            officialbooking.setphoneNumber(phoneNumber);
            officialbooking.setNumber(Integer.parseInt(request.getParameter("pnumber")));

            String[] friendNames = request.getParameterValues("friendName");
            String[] friendIds = request.getParameterValues("friendId");
            String[] friendPhoneNumbers = request.getParameterValues("friendPhoneNumber");
            List<Person> friends = new ArrayList<>();
            for (int i = 0; i < friendNames.length; i++) {
                //随行人员信息加密
                String friend_id = SM4Util.encrypt(friendIds[i]);
                String friend_phoneNumber = SM4Util.encrypt(friendPhoneNumbers[i]);
                Person friend = new Person();
                friend.setName(friendNames[i]);
                friend.setId(friend_id);
                friend.setphoneNumber(friend_phoneNumber);
                friends.add(friend);
            }
            officialbooking.setFriends(friends);
            officialbooking.setdepartment(request.getParameter("department"));
            officialbooking.setReceptionist(request.getParameter("receptionist"));
            officialbooking.setReason(request.getParameter("reason"));
            Officialbooking officialbooking_check;
            officialbooking_check = officialbookingDao.findByMain(officialbooking.getId(),officialbooking.getIntime());
            if(officialbooking_check!=null){
                HashMap<String, String> formData = new HashMap<>();
                formData.put("applytime", officialbooking.getApplytime());
                formData.put("campus", officialbooking.getCampus());
                formData.put("intime", officialbooking.getIntime());
                formData.put("outtime", officialbooking.getOuttime());
                formData.put("unit", officialbooking.getunit());
                formData.put("vehicle", officialbooking.getVehicle());
                formData.put("vname", officialbooking.getVname());
                formData.put("id", officialbooking.getId());
                formData.put("name", officialbooking.getName());
                formData.put("phoneNumber", officialbooking.getphoneNumber());
                formData.put("number", String.valueOf(officialbooking.getNumber()));
                formData.put("friends", gson.toJson(officialbooking.getFriends())); // 添加friends列表的JSON字符串
                formData.put("department", officialbooking.getdepartment());
                formData.put("receptionist", officialbooking.getReceptionist());
                formData.put("reason", officialbooking.getReason());
                formData.put("permit", officialbooking.getPermit());
                QRCodeGenerator.generateQRCode(formData, "C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Officialbooking\\" + officialbooking.getId() +officialbooking.getIntime()+ ".png");
                QRCodeGenerator.generateInvalidQRCode("C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Officialbooking\\" + officialbooking.getId() + "invalid.png");
                QRCodeGenerator.generateQRCode(formData,"C:\\Users\\86173\\IdeaProjects\\mytest\\target\\mytest-1.0-SNAPSHOT\\QRCode_Officialbooking\\" + officialbooking.getId() +officialbooking.getIntime()+ ".png");
                QRCodeGenerator.generateInvalidQRCode("C:\\Users\\86173\\IdeaProjects\\mytest\\target\\mytest-1.0-SNAPSHOT\\QRCode_Officialbooking\\" + officialbooking.getId() + "invalid.png");
                officialbooking.setPermit(officialbooking_check.getPermit());
                officialbooking.setQRcode("QRCode_Officialbooking/" + officialbooking.getId() +officialbooking.getIntime()+ ".png");
                officialbooking.setInvalidQRcode("QRCode_Officialbooking/" + officialbooking.getId() + "invalid.png");
                boolean successupdate_check = officialbookingDao.updateofficialbooking(officialbooking);
                return;
            }
            boolean success = officialbookingDao.addofficialbooking(officialbooking);
            if (success) {
                System.out.println("添加成功");
                officialbooking.setPermit("待审");
                HashMap<String, String> formData = new HashMap<>();
                formData.put("campus", officialbooking.getCampus());
                formData.put("campus", officialbooking.getCampus());
                formData.put("intime", officialbooking.getIntime());
                formData.put("outtime", officialbooking.getOuttime());
                formData.put("unit", officialbooking.getunit());
                formData.put("vehicle", officialbooking.getVehicle());
                formData.put("vname", officialbooking.getVname());
                formData.put("id", officialbooking.getId());
                formData.put("name", officialbooking.getName());
                formData.put("phoneNumber", officialbooking.getphoneNumber());
                formData.put("number", String.valueOf(officialbooking.getNumber()));
                formData.put("friends", gson.toJson(officialbooking.getFriends())); // 添加friends列表的JSON字符串
                formData.put("department", officialbooking.getdepartment());
                formData.put("receptionist", officialbooking.getReceptionist());
                formData.put("reason", officialbooking.getReason());
                formData.put("permit", officialbooking.getPermit());
                QRCodeGenerator.generateQRCode(formData, "C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Officialbooking\\" + officialbooking.getId() +officialbooking.getIntime()+ ".png");
                QRCodeGenerator.generateInvalidQRCode("C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Officialbooking\\" + officialbooking.getId() + "invalid.png");
                QRCodeGenerator.generateQRCode(formData,"C:\\Users\\86173\\IdeaProjects\\mytest\\target\\mytest-1.0-SNAPSHOT\\QRCode_Officialbooking\\" + officialbooking.getId() +officialbooking.getIntime()+ ".png");
                QRCodeGenerator.generateInvalidQRCode("C:\\Users\\86173\\IdeaProjects\\mytest\\target\\mytest-1.0-SNAPSHOT\\QRCode_Officialbooking\\" + officialbooking.getId() + "invalid.png");
                officialbooking.setQRcode("QRCode_Officialbooking/" + officialbooking.getId() +officialbooking.getIntime()+ ".png");
                officialbooking.setInvalidQRcode("QRCode_Officialbooking/" + officialbooking.getId() + "invalid.png");
                boolean successupdate = officialbookingDao.updateofficialbooking(officialbooking);
                if(successupdate){
                    System.out.println("更新成功");
                }else{
                    System.out.println("更新失败");
                }
            } else {
                System.out.println("添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
