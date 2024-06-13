package beans;

import java.util.List;

public class Officialbooking extends Mybooking {
    private String department;//公务访问部门
    private String receptionist;//公务访问接待人
    private String reason;
    private String permit;//许可证

    public Officialbooking() {
        super();
    }



    //什么都有
    public Officialbooking(String applytime,String campus, String intime, String outtime, String unit, String vehicle, String vname, String name,String id,String phoneNumber, int number, List<Person> friends, String QRcode, String InvalidQRcode, String department, String receptionist, String reason, String permit) {
        super(applytime,campus, intime, outtime, unit, vehicle, vname, name,id,phoneNumber, number, friends, QRcode, InvalidQRcode);
        this.department = department;
        this.receptionist = receptionist;
        this.reason = reason;
        this.permit = permit;
    }

    //可以没有随行人员
    public Officialbooking(String applytime,String campus, String intime, String outtime, String unit, String vehicle, String vname, String name,String id,String phoneNumber, String QRcode, String InvalidQRcode, String department, String receptionist, String reason, String permit) {
        super(applytime,campus, intime, outtime, unit, vehicle, vname, name,id,phoneNumber, QRcode, InvalidQRcode);
        this.department = department;
        this.receptionist = receptionist;
        this.reason = reason;
        this.permit = permit;
    }

    //可以没有二维码
    public Officialbooking(String applytime,String campus, String intime, String outtime, String unit, String vehicle, String vname, String name,String id,String phoneNumber, String department, String receptionist, String reason, String permit) {
        super(applytime,campus, intime, outtime, unit, vehicle, vname, name,id,phoneNumber);
        this.department = department;
        this.receptionist = receptionist;
        this.reason = reason;
        this.permit = permit;
    }

    //车牌号可以不填
    public Officialbooking(String applytime,String campus, String intime, String outtime, String unit, String vehicle, String name,String id,String phoneNumber, String department, String receptionist, String reason, String permit) {
        super(applytime,campus, intime, outtime, unit, vehicle, name,id,phoneNumber);
        this.department = department;
        this.receptionist = receptionist;
        this.reason = reason;
        this.permit = permit;
    }
    //with json
    public Officialbooking(String name, String id, String phoneNumber, String campus, String intime, String outtime, String unit, String vehicle, String vname, int number, String friendsJson, String QRcode, String InvalidQRcode, String department, String receptionist, String reason, String permit, String applytime) {
        super(name, id, phoneNumber, campus, intime, outtime, unit, vehicle, vname, number, friendsJson, QRcode, InvalidQRcode, applytime);
        //System.out.println("运行完父类构造");
        this.department = department;
        this.receptionist = receptionist;
        this.reason = reason;
        this.permit = permit;
    }

    public String getdepartment() {
        return department;
    }

    public void setdepartment(String department) {
        this.department = department;
    }

    public String getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(String receptionist) {
        this.receptionist = receptionist;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }
}
