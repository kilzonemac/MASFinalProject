package model.bikeservice;

import model.bikeservice.enums.OrderState;
import model.user.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class BikeOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private OrderState orderState;

    private double cost;

    private LocalDate orderDate;

    private String deliveryAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;

    public BikeOrder(OrderState orderState, double cost, LocalDate orderDate, String deliveryAddress) {
        this.orderState = orderState;
        this.cost = cost;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
    }

    public BikeOrder() {
    }

    public static void deleteCanceledOrders(){
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Query q = session.createQuery("delete BikeOrder where orderState = 3");
        q.executeUpdate();

        session.close();
        sessionFactory.close();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        client.addOrder(this);
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
