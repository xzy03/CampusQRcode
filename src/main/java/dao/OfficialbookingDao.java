package dao;
import beans.Officialbooking;
import java.util.ArrayList;

public interface OfficialbookingDao extends Dao{
    public boolean addofficialbooking(Officialbooking officialbooking);
    public Officialbooking findByMain(String id, String intime);
    public Officialbooking query_find(String id,String name,String phone);
    public ArrayList<Officialbooking> findAllQueryOfficialbooking(String id, String name, String phone);
    public boolean updateofficialbooking(Officialbooking officialbooking);
}
