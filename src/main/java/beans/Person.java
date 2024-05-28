package beans;

public class Person {
    private String name;//姓名
    private String id;//身份证号
    private String phoneNumber;//手机号码

    public Person(){}
    public Person(String name){
        this.name=name;
    }
    public Person(String name, String id, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}