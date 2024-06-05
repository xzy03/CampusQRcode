package dao;
import beans.Mybooking;
import java.util.ArrayList;
public interface MybookingDao extends Dao{
    public boolean addmybooking(Mybooking mybooking);
    public Mybooking findByMain(String id, String intime);
    public Mybooking query_find(String id,String name,String phone);
    public ArrayList<Mybooking> findAllMybooking();
    public boolean updatemybooking(Mybooking mybooking);
}
