package model.bikeservice;

import javax.persistence.*;
import java.util.List;

@Entity
public class BikePart {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;

    private String cost;

    @ManyToMany
    private List<BikeRepair> bikeRepairs;
}
