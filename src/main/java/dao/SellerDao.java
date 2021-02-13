package dao;

import model.user.Seller;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class SellerDao implements Dao<Seller> {
    private static SellerDao instance;

    public static SellerDao getInstance() {
        if(instance == null){
            instance = new SellerDao();
        }
        return instance;
    }

    public Seller getByLogin(String login){
        login = "'" + login + "'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Seller> sellers = (List<Seller>)session.createQuery("FROM model.user.Seller WHERE login = " + login).list();
            if(sellers != null && sellers.size() > 0){
                return sellers.get(0);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Seller getByLoginAndPassword(String login, String password){
        login = "'" + login + "'";
        password = "'" + password + "'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Seller> sellers = (List<Seller>)session.createQuery("FROM model.user.Seller WHERE login = " + login + " AND password = " + password).list();
            if(sellers != null && sellers.size() > 0){
                return sellers.get(0);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Seller> getAll() {
        return null;
    }
}
