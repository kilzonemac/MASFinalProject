package dao;

import model.bikeservice.Bike;
import model.bikeservice.BikeOrder;
import model.user.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class BikeOrderDao implements Dao<BikeOrder> {
    private static BikeOrderDao instance;

    public static BikeOrderDao getInstance() {
        if(instance == null){
            instance = new BikeOrderDao();
        }
        return instance;
    }

    @Override
    public List<BikeOrder> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<BikeOrder> bikeOrders = (List<BikeOrder>)session.createQuery("FROM model.bikeservice.BikeOrder").list();
            if(bikeOrders != null) {
                return bikeOrders;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeCanceledOrders(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<BikeOrder> bikeOrders = (List<BikeOrder>)(session.createQuery("FROM model.bikeservice.BikeOrder where orderState=4").list());
            List<Bike> bikesToUpdate = new ArrayList<>();

            for(BikeOrder bikeOrder: bikeOrders){
                bikeOrder.getOrderedBikes().forEach(b -> b.setBikeOrder(null));
                bikesToUpdate.addAll(bikeOrder.getOrderedBikes());
            }

            BikeDao.getInstance().updateAll(bikesToUpdate);

            session.beginTransaction();

            session.createQuery("delete model.bikeservice.BikeOrder where orderState=4").executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

}
