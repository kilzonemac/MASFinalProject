package dao;

import model.bikeservice.Bike;
import model.bikeservice.BikeModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure()
                        .buildSessionFactory();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }

    public static void createTestData(){
        try (Session session = getSessionFactory().openSession()) {

        session.beginTransaction();

        BikeModel bikeModel1 = new BikeModel("Affix Sport","Górski", "Czarny","KROSS");
        BikeModel bikeModel2 = new BikeModel("Legion L20","BMX", "Biały","MONGOOSE");
        BikeModel bikeModel3 = new BikeModel("Gan Disk 1.0","Szosowy", "Szary","PINARELLO");
        BikeModel bikeModel4 = new BikeModel("Monsun 3","MTB", "Czarny","ROMET");
        BikeModel bikeModel5 = new BikeModel("Aspre 2","Gravel", "Szary","ROMET");
        BikeModel bikeModel6 = new BikeModel("Level 6.0","Górski", "Limonkowy","KROSS");
        BikeModel bikeModel7 = new BikeModel("Gan Disk 2.0","Szosowy", "Biały","PINARELLO");
        BikeModel bikeModel8 = new BikeModel("Hexagon 7.0","Górski", "Zielony","KROSS");
        BikeModel bikeModel9 = new BikeModel("Hexagon 6.0","Górski", "Czarny","KROSS");
        BikeModel bikeModel10 = new BikeModel("Gan Disk 3.0","Szosowy", "Szary","PINARELLO");
        BikeModel bikeModel11 = new BikeModel("Gan Disk 4.0","Szosowy", "Czarny","PINARELLO");
        BikeModel bikeModel14 = new BikeModel("Hexagon 5.0","Górski", "Czarny","KROSS");


        session.save(bikeModel1);
        session.save(bikeModel2);
        session.save(bikeModel3);
        session.save(bikeModel4);
        session.save(bikeModel5);
        session.save(bikeModel6);
        session.save(bikeModel7);
        session.save(bikeModel8);
        session.save(bikeModel9);
        session.save(bikeModel10);
        session.save(bikeModel11);
        session.save(bikeModel14);


        session.save(new Bike(LocalDate.now(),1000, "5409661", bikeModel1));
        session.save(new Bike(LocalDate.now(),3000, "9008723", bikeModel2));
        session.save(new Bike(LocalDate.now(),5000, "4680428", bikeModel3));
        session.save(new Bike(LocalDate.now(),3999, "7835018", bikeModel4));
        session.save(new Bike(LocalDate.now(),4499, "8224040", bikeModel5));
        session.save(new Bike(LocalDate.now(),5000, "5830269", bikeModel6));
        session.save(new Bike(LocalDate.now(),5499, "2349187", bikeModel7));
        session.save(new Bike(LocalDate.now(),5000, "1169464", bikeModel8));
        session.save(new Bike(LocalDate.now(),3999, "6729273", bikeModel9));
        session.save(new Bike(LocalDate.now(),6999, "4620428", bikeModel10));
        session.save(new Bike(LocalDate.now(),7999, "1680628", bikeModel11));
        session.save(new Bike(LocalDate.now(),8999, "3685428", bikeModel11));
        session.save(new Bike(LocalDate.now(),9999, "4484668", bikeModel11));
        session.save(new Bike(LocalDate.now(),2999, "3889787", bikeModel14));
        session.save(new Bike(LocalDate.now(),1999, "5147907", bikeModel14));
        session.save(new Bike(LocalDate.now(),1499, "6349187", bikeModel14));

        session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }
}