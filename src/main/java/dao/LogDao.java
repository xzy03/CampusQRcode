package dao;

import beans.Auditlog;
import java.util.ArrayList;

public interface LogDao extends Dao {
    public boolean addLog(Auditlog log);
    public ArrayList<Auditlog> find(Auditlog log);
    public ArrayList<Auditlog> findAlllogs();

}
