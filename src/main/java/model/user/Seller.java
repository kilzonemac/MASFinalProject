package model.user;

import model.bikeservice.BikeOrder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller extends Worker {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="seller")
    public List<BikeOrder> doneOrders = new ArrayList<>();

    @Column
    private int doneOrdersCount;

    public Seller() {
    }

    public Seller(String name, String surname, String address, String login, String password, LocalDate hireDate, double salary) {
        super(name, surname, address, login, password, hireDate, salary);
    }

    public void addOrder(BikeOrder bikeOrder){
        bikeOrder.setSeller(this);
        doneOrders.add(bikeOrder);

        doneOrdersCount++;
    }
}
