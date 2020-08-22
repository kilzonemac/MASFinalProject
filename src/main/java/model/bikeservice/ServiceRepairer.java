package model.bikeservice;

import model.user.Repairer;

import javax.persistence.*;

@Entity
public class ServiceRepairer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String description;

    @ManyToOne
    private Service service;

    @ManyToOne
    private Repairer repairer;
}
