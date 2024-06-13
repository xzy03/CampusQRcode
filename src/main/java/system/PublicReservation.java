package system;

import java.sql.Timestamp;

public class PublicReservation {
    private String name;
    private String idCard;
    private String phoneNumber;
    private String community;
    private Timestamp entryTime;
    private Timestamp exitTime;
    private String unit;
    private String transportation;
    private String licensePlate;
    private String companions;

    // 构造函数
    public PublicReservation(String name, String idCard, String phoneNumber, String community, Timestamp entryTime,
                             Timestamp exitTime, String unit, String transportation, String licensePlate, String companions) {
        this.name = name;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
        this.community = community;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.unit = unit;
        this.transportation = transportation;
        this.licensePlate = licensePlate;
        this.companions = companions;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public Timestamp getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
    }

    public Timestamp getExitTime() {
        return exitTime;
    }

    public void setExitTime(Timestamp exitTime) {
        this.exitTime = exitTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCompanions() {
        return companions;
    }

    public void setCompanions(String companions) {
        this.companions = companions;
    }
}
