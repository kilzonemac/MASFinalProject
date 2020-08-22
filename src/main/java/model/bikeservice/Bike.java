package model.bikeservice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Bike {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private LocalDate deliveryDate;

    private double cost;

    @Column(unique = true)
    private String serialNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    private BikeModel bikeModel;

    public Bike(LocalDate deliveryDate, double cost, String serialNumber, BikeModel bikeModel) {
        this.deliveryDate = deliveryDate;
        this.serialNumber = serialNumber;
        this.bikeModel = bikeModel;
        this.cost = cost;
    }

    public Bike() {
    }

    public static List<Bike> getAllBikes(){
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        @SuppressWarnings("unchecked")
        List<Bike> bikes = session.createQuery("FROM model.bikeservice.Bike").list();

        session.close();
        sessionFactory.close();

        return bikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BikeModel getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(BikeModel bikeModel) {
        this.bikeModel = bikeModel;
    }
}
