package model.bikeservice;

import javax.persistence.*;
import java.util.List;

@Entity
public class BikePart {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;

    private double cost;

    @ManyToMany(mappedBy = "bikeParts")
    private List<BikeRepair> bikeRepairs;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
