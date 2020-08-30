package dao;

import model.bikeservice.Bike;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class BikeDao implements Dao<Bike> {

    private static BikeDao instance;

    public static BikeDao getInstance() {
        if(instance == null){
            instance = new BikeDao();
        }
        return instance;
    }

    @Override
    public List<Bike> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Bike> bikes = (List<Bike>)session.createQuery("FROM model.bikeservice.Bike").list();

            if(bikes != null) {
                return bikes;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
