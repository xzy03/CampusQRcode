package beans;

import java.io.Serializable;

public class Auditlog implements Serializable {
    private String uname;
    private String operation;
    private String description;

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

    public Auditlog(String uname, String operation, String description) {
        this.uname = uname;
        this.operation = operation;
        this.description = description;
    }
}
