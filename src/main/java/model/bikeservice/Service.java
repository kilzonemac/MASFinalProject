package model.bikeservice;

import model.user.Client;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Service {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(unique = true, nullable = false)
    private String serivceNumber;

    @Column(nullable = false)
    private double workerCost;

    @Column(nullable = false)
    private String description;

    //in days
    @Column(nullable = false)
    private int servicePeriod;

    @Column(nullable = false)
    private double fullCost;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="service")
    private List<ServiceRepairer> serviceRepairers;

    public void setClient(Client client) {
        this.client = client;
    }
}
