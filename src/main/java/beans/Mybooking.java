package beans;

import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Mybooking extends Person {
    private String applytime;//申请时间
    private String campus;//校区名字
    private String intime;//入校时间
    private String outtime;//出校时间
    private String unit;//所在单位
    private String vehicle;//交通方式
    private String vname;//车牌号
    private int number;//随行人员数量
    private List<Person> friends;
    private String QRcode;//二维码路径
    private String InvalidQRcode;//无效二维码路径

    public Mybooking(){
        super();
    }

    public Mybooking(String applytime,String campus, String intime,String outtime, String unit, String vehicle, String vname, String name,String id,String phoneNumber, int number, List<Person> friends,String QRcode, String InvalidQRcode) {
        this.applytime = applytime;
        this.campus = campus;
        this.intime = intime;
        this.outtime = outtime;
        this.unit = unit;
        this.vehicle = vehicle;
        this.vname = vname;
        this.setName(name);
        this.setId(id);
        this.setphoneNumber(phoneNumber);
        this.number = number;
        this.friends = friends;
        this.QRcode = QRcode;
        this.InvalidQRcode = InvalidQRcode;
    }

    //同行人员可以不填
    public Mybooking(String applytime,String campus, String intime,String outtime, String unit, String vehicle, String vname, String name,String id,String phoneNumber, String QRcode, String InvalidQRcode) {
        this.applytime = applytime;
        this.campus = campus;
        this.intime = intime;
        this.outtime = outtime;
        this.unit = unit;
        this.vehicle = vehicle;
        this.vname = vname;
        this.setName(name);
        this.setId(id);
        this.setphoneNumber(phoneNumber);
        this.QRcode = QRcode;
        this.InvalidQRcode = InvalidQRcode;
    }


    //二维码路径可以不填
    public Mybooking(String applytime,String campus, String intime,String outtime, String unit, String vehicle, String vname, String name,String id,String phoneNumber) {
        this.applytime = applytime;
        this.campus = campus;
        this.intime = intime;
        this.outtime = outtime;
        this.unit = unit;
        this.vehicle = vehicle;
        this.vname = vname;
        this.setName(name);
        this.setId(id);
        this.setphoneNumber(phoneNumber);
    }

    //车牌号可以不填
    public Mybooking(String applytime,String campus, String intime,String outtime, String unit, String vehicle , String name,String id,String phoneNumber) {
        this.applytime = applytime;
        this.campus = campus;
        this.intime = intime;
        this.outtime = outtime;
        this.unit = unit;
        this.vehicle=vehicle;
        this.setName(name);
        this.setId(id);
        this.setphoneNumber(phoneNumber);
    }

    // Constructor with JSON friends
    public Mybooking(String name, String id, String phoneNumber, String campus, String intime, String outtime, String unit, String vehicle, String vname, int number, String friendsJson, String QRcode, String InvalidQRcode, String applytime) {
        super(name, id, phoneNumber);
        //System.out.println("运行完Person构造");
        this.campus = campus;
        this.intime = intime;
        this.outtime = outtime;
        this.unit = unit;
        this.vehicle = vehicle;
        this.vname = vname;
        this.number = number;
        this.friends = new Gson().fromJson(friendsJson, new TypeToken<List<Person>>() {}.getType());
        this.QRcode = QRcode;
        this.InvalidQRcode = InvalidQRcode;
        this.applytime = applytime;
        //System.out.println("运行完Person构造");
    }


    public String getQRcode() {
        return QRcode;
    }
    public void setQRcode(String QRcode) {
        this.QRcode = QRcode;
    }
    public String getInvalidQRcode() {
        return InvalidQRcode;
    }
    public void setInvalidQRcode(String InvalidQRcode) {
        this.InvalidQRcode = InvalidQRcode;
    }
    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getunit() {
        return unit;
    }

    public void setunit(String unit) {
        this.unit = unit;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List friends){
        this.friends = friends;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }
}
