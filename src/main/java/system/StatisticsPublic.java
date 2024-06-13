package system;

public class StatisticsPublic {
    private String applyMonth;
    private String reservationMonth;
    private String campus;
    private int reservationCount;
    private int personCount;

    // Getters and Setters
    public String getApplyMonth() {
        return applyMonth;
    }

    public void setApplyMonth(String applyMonth) {
        this.applyMonth = applyMonth;
    }

    public String getReservationMonth() {
        return reservationMonth;
    }

    public void setEntryMonth(String reservationMonth) {
        this.reservationMonth = reservationMonth;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(int reservationCount) {
        this.reservationCount = reservationCount;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }
}
