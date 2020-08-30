package model.bikeservice;

import model.user.Repairer;

import javax.persistence.*;

@Entity
public class ServiceRepairer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="service_id", nullable=false)
    private Service service;

    @ManyToOne
    @JoinColumn(name="repairer_id", nullable=false)
    private Repairer repairer;

    public ServiceRepairer() {
    }

    public ServiceRepairer(String description) {
        this.description = description;
    }

    public void addDescription(String description){
        this.description = description;
    }
}
