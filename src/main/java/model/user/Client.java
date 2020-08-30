package model.user;

import model.bikeservice.BikeOrder;
import model.bikeservice.Service;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends User {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Service> services = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="client")
    private List<BikeOrder> bikeOrders = new ArrayList<>();

    @Column
    private int orderCount;

    //percent and max 20%
    @Column
    private int discount;

    public Client(String name, String surname, String address, String login, String password) {
        super(name, surname, address, login, password);
    }

    public Client() {
    }

    public void addOrder(BikeOrder bikeOrder){
        bikeOrder.setClient(this);
        bikeOrders.add(bikeOrder);

        orderCount++;
        if(discount <= 20)
            discount = orderCount;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getDiscount() {
        return discount;
    }

    public void addService(Service service){
        service.setClient(this);
        services.add(service);
    }
}
