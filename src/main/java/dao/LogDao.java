package dao;

import beans.Auditlog;
import java.util.ArrayList;
import java.util.List;

public interface LogDao extends Dao {
    public boolean addLog(Auditlog log);
    public ArrayList<Auditlog> find(Auditlog log);
    public ArrayList<Auditlog> findAlllogs();
    public List<Auditlog> searchLogs(String userName, String userOperation, String startDate, String endDate);

}
