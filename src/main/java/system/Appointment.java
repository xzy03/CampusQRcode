package system;

public class Appointment {
    private int id;
    private String applicantName;
    private String applicantId;
    private String applicationDate;
    private String appointmentDate;
    private String campus;

    // Constructors, getters, and setters
    public Appointment(int id, String applicantName, String applicantId, String applicationDate, String appointmentDate, String campus) {
        this.id = id;
        this.applicantName = applicantName;
        this.applicantId = applicantId;
        this.applicationDate = applicationDate;
        this.appointmentDate = appointmentDate;
        this.campus = campus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }
}
