package model.user;

import model.bikeservice.BikeOrder;
import model.bikeservice.Service;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends User {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Service> services = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<BikeOrder> bikeOrders = new ArrayList<>();

    public Client(String name, String surname, String address, String login, String password) {
        super(name, surname, address, login, password);
    }

    public Client() {
    }

    public void addOrder(BikeOrder bikeOrder){
        bikeOrders.add(bikeOrder);
    }

    public void addService(Service service){
        services.add(service);
    }
}
