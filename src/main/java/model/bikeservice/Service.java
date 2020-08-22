package model.bikeservice;

import model.user.Client;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class Service {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String serivceNumber;

    private double workerCost;

    private String description;

    //in days
    private int servicePeriod;

    private double fullCost;

    @ManyToOne
    private Client client;

    @OneToMany
    private List<ServiceRepairer> serviceRepairers;
}
