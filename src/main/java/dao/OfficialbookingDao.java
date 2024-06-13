package dao;
import beans.Officialbooking;
import java.util.ArrayList;
import java.util.List;
import system.StatisticsOfficial;

public interface OfficialbookingDao extends Dao{
    public boolean addofficialbooking(Officialbooking officialbooking);
    public Officialbooking findByMain(String id, String intime);
    public Officialbooking query_find(String id,String name,String phone);
    public ArrayList<Officialbooking> findAllQueryOfficialbooking(String id, String name, String phone);
    public boolean updateofficialbooking(Officialbooking officialbooking);
    public List<Officialbooking> searchBookings(String applyTime, String entryTime, String campus, String unit, String name, String idCard, String department, String receptionist, String permit);

    public List<StatisticsOfficial> getStatistics(String applyMonth, String entryMonth, String campus, String department);
}
