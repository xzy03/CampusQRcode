package dao;
import beans.Mybooking;
import java.util.ArrayList;
import java.util.List;
import system.StatisticsPublic;
public interface MybookingDao extends Dao{
    public boolean addmybooking(Mybooking mybooking);
    public Mybooking findByMain(String id, String intime);
    public Mybooking query_find(String id,String name,String phone);
    public ArrayList<Mybooking> findAllQueryMybooking(String id, String name, String phone);
    public boolean updatemybooking(Mybooking mybooking);
    public List<Mybooking> searchBookings(String applyTime, String entryTime, String campus, String unit, String name, String idCard);

    public List<StatisticsPublic> getStatistics(String applyMonth, String entryMonth, String campus);
}
