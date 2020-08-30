package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;


public interface Dao<T> {

    default void save(T t){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            session.save(t);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void saveOrUpdate(T t){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            session.saveOrUpdate(t);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void update(T t){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            session.update(t);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void delete(T t){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            session.delete(t);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void saveAll(List<T> listToSave){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            for(T obj: listToSave) {
                session.save(obj);
            }

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void updateAll(List<T> listToUpdate){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            for(T obj: listToUpdate) {
                session.update(obj);
            }

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    default void deleteAll(List<T> listToDelete){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            for(T obj: listToDelete) {
                session.delete(obj);
            }

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    List<T> getAll();
}
