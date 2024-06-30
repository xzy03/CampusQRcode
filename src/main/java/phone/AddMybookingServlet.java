package phone;
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
import system.SM4Util;
import util.ImageToBase64;

@WebServlet("/AddMybookingServlet")
public class AddMybookingServlet extends HttpServlet{
    private Gson gson = new Gson();
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        MybookingDao mybookingDao = new MybookingDaoImpl();
        Mybooking mybooking = new Mybooking();
        try{
            //加密id和phoneNumber
            String id = SM4Util.encrypt(request.getParameter("id"));
            String phoneNumber = SM4Util.encrypt(request.getParameter("phoneNumber"));
            mybooking.setApplytime(request.getParameter("applytime"));
            mybooking.setCampus(request.getParameter("campus"));
            mybooking.setIntime(request.getParameter("intime"));
            mybooking.setOuttime(request.getParameter("outtime"));
            mybooking.setunit(request.getParameter("unit"));
            mybooking.setVehicle(request.getParameter("vehicle"));
            mybooking.setVname(request.getParameter("vname"));
            mybooking.setId(id);
            mybooking.setName(request.getParameter("name"));
            mybooking.setphoneNumber(phoneNumber);
            mybooking.setNumber(Integer.parseInt(request.getParameter("pnumber")));

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
            mybooking.setFriends(friends);
            Mybooking mybooking_check;
            mybooking_check = mybookingDao.findByMain(mybooking.getId(),mybooking.getIntime());
            if(mybooking_check!=null){
                HashMap<String, String> formData = new HashMap<>();
                formData.put("applytime", mybooking.getApplytime());
                formData.put("campus", mybooking.getCampus());
                formData.put("intime", mybooking.getIntime());
                formData.put("outtime", mybooking.getOuttime());
                formData.put("unit", mybooking.getunit());
                formData.put("vehicle", mybooking.getVehicle());
                formData.put("vname", mybooking.getVname());
                formData.put("id", mybooking.getId());
                formData.put("name", mybooking.getName());
                formData.put("phoneNumber", mybooking.getphoneNumber());
                formData.put("number", String.valueOf(mybooking.getNumber()));
                formData.put("friends", gson.toJson(mybooking.getFriends())); // 添加friends列表的JSON字符串
                QRCodeGenerator.generateQRCode(formData, "C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Mybooking\\" + mybooking.getId() +mybooking.getIntime()+ ".png");
                QRCodeGenerator.generateInvalidQRCode("C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Mybooking\\" + mybooking.getId() + "invalid.png");
//                ServletContext context = request.getServletContext();
//                String deploymentPath = context.getRealPath("/");
//                System.out.println("Deployment path: " + deploymentPath);
                QRCodeGenerator.generateQRCode(formData,"C:\\Users\\86173\\IdeaProjects\\mytest\\target\\mytest-1.0-SNAPSHOT\\QRCode_Mybooking\\" + mybooking.getId() +mybooking.getIntime()+ ".png");
                QRCodeGenerator.generateInvalidQRCode("C:\\Users\\86173\\IdeaProjects\\mytest\\target\\mytest-1.0-SNAPSHOT\\QRCode_Mybooking\\" + mybooking.getId() + "invalid.png");
//                String base64String = ImageToBase64.encodeImageToBase64("C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Mybooking\\" + mybooking.getId() +mybooking.getIntime()+ ".png");
//                mybooking.setQRcode(base64String);
//                String base64String_invalid = ImageToBase64.encodeImageToBase64("C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Mybooking\\" + mybooking.getId() + "invalid.png");
//                mybooking.setInvalidQRcode(base64String_invalid);
                mybooking.setQRcode("QRCode_Mybooking/" + mybooking.getId() +mybooking.getIntime()+ ".png");
                mybooking.setInvalidQRcode("QRCode_Mybooking/" + mybooking.getId() + "invalid.png");
//                System.out.println(base64String);
                boolean successupdate_check = mybookingDao.updatemybooking(mybooking);
                return;
            }
            boolean success = mybookingDao.addmybooking(mybooking);
            if (success) {
                System.out.println("添加成功");
                HashMap<String, String> formData = new HashMap<>();
                formData.put("applytime", mybooking.getApplytime());
                formData.put("campus", mybooking.getCampus());
                formData.put("intime", mybooking.getIntime());
                formData.put("outtime", mybooking.getOuttime());
                formData.put("unit", mybooking.getunit());
                formData.put("vehicle", mybooking.getVehicle());
                formData.put("vname", mybooking.getVname());
                formData.put("id", mybooking.getId());
                formData.put("name", mybooking.getName());
                formData.put("phoneNumber", mybooking.getphoneNumber());
                formData.put("number", String.valueOf(mybooking.getNumber()));
                formData.put("friends", gson.toJson(mybooking.getFriends())); // 添加friends列表的JSON字符串
                QRCodeGenerator.generateQRCode(formData, "C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Mybooking\\" + mybooking.getId() +mybooking.getIntime()+ ".png");
                QRCodeGenerator.generateInvalidQRCode("C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Mybooking\\" + mybooking.getId() + "invalid.png");
//                ServletContext context = request.getServletContext();
//                String deploymentPath = context.getRealPath("/");
//                System.out.println("Deployment path: " + deploymentPath);
                QRCodeGenerator.generateQRCode(formData,"C:\\Users\\86173\\IdeaProjects\\mytest\\target\\mytest-1.0-SNAPSHOT\\QRCode_Mybooking\\" + mybooking.getId() +mybooking.getIntime()+ ".png");
                QRCodeGenerator.generateInvalidQRCode("C:\\Users\\86173\\IdeaProjects\\mytest\\target\\mytest-1.0-SNAPSHOT\\QRCode_Mybooking\\" + mybooking.getId() + "invalid.png");
//                String base64String = ImageToBase64.encodeImageToBase64("C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Mybooking\\" + mybooking.getId() +mybooking.getIntime()+ ".png");
//                mybooking.setQRcode(base64String);
//                String base64String_invalid = ImageToBase64.encodeImageToBase64("C:\\Users\\86173\\IdeaProjects\\mytest\\src\\main\\webapp\\QRCode_Mybooking\\" + mybooking.getId() + "invalid.png");
//                mybooking.setInvalidQRcode(base64String_invalid);
//                System.out.println(base64String);
//                System.out.println(base64String_invalid);
                mybooking.setQRcode("QRCode_Mybooking/" + mybooking.getId() +mybooking.getIntime()+ ".png");
                mybooking.setInvalidQRcode("QRCode_Mybooking/" + mybooking.getId() + "invalid.png");
                boolean successupdate = mybookingDao.updatemybooking(mybooking);
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
