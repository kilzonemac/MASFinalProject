package dao;

import model.user.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class ClientDao implements Dao<Client> {
    private static ClientDao instance;

    public static ClientDao getInstance() {
        if(instance == null){
            instance = new ClientDao();
        }
        return instance;
    }

    public Client getByLogin(String login){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            login = "'" + login + "'";

            List<Client> clients = (List<Client>)session.createQuery("FROM model.user.Client WHERE login = " + login).list();
            if(clients != null && clients.size() > 0){
                return clients.get(0);
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Client getByLoginAndPassword(String login, String password){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            login = "'" + login + "'";
            password = "'" + password + "'";

            List<Client> clients = (List<Client>)session.createQuery("FROM model.user.Client WHERE login = " + login + " AND password = " + password).list();
            if(clients != null && clients.size() > 0){
                return clients.get(0);
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Client> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Client> clients = (List<Client>)session.createQuery("FROM model.user.Client").list();

            if(clients != null) {
                return clients;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

}
