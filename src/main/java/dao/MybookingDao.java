package dao;
import beans.Mybooking;
import java.util.ArrayList;
public interface MybookingDao extends Dao{
    public boolean addmybooking(Mybooking mybooking);
    public Mybooking findById(String id);
    public ArrayList<Mybooking> findAllMybooking();
}
