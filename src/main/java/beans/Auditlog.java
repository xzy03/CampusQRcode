package beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Auditlog implements Serializable {
    private String uname;
    private String operation;
    private String description;

    private Timestamp createdAt;
    public Auditlog() {
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public Auditlog(String uname, String operation, String description) {
        this.uname = uname;
        this.operation = operation;
        this.description = description;
    }

    public Auditlog(String uname, String operation, String description, Timestamp createdAt) {
        this.uname = uname;
        this.operation = operation;
        this.description = description;
        this.createdAt = createdAt;
    }

    public String toString() {
        return "Log{" +
                "userName='" + uname + '\'' +
                ", userOperation='" + operation + '\'' +
                ", userDescription='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
