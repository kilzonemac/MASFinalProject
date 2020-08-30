package model.bikeservice;

import model.bikeservice.enums.OrderState;
import model.user.Client;
import model.user.Seller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BikeOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private OrderState orderState;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private String deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="seller_id", nullable=false)
    private Seller seller;

    @OneToMany(mappedBy="bikeOrder")
    private List<Bike> orderedBikes = new ArrayList<>();

    public BikeOrder(OrderState orderState, double cost, LocalDate orderDate, String deliveryAddress, int discount) {
        this.orderState = orderState;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;

        //discountCount
        this.cost = cost - (((double)discount/100) * cost);
    }

    public BikeOrder() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<Bike> getOrderedBikes() {
        return orderedBikes;
    }

    public void addBike(Bike bike){
        bike.setBikeOrder(this);
        orderedBikes.add(bike);
    }

    public void acceptOrder(){
        this.orderState = OrderState.ACCEPTED;
    }

    public void cancelOrder(){
        this.orderState = OrderState.CANCELED;
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
