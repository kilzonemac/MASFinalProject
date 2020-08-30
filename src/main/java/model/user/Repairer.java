package model.user;

import model.bikeservice.ServiceRepairer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Repairer extends Worker {

    @ElementCollection
    private List<String> certificates = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy="repairer")
    private List<ServiceRepairer> serviceRepairers = new ArrayList<>();

    public Repairer(String name, String surname, String address, String login, String password, LocalDate hireDate, double salary) {
        super(name, surname, address, login, password, hireDate, salary);
    }

    public Repairer() {
    }

    public void addCertificate(String certificate){
        this.certificates.add(certificate);
    }

    public void deleteCertificate(String certificate){
        this.certificates.remove(certificate);
    }

}
