package model.user;

import model.bikeservice.ServiceRepairer;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Repairer extends Worker {

    private String certificate;

    @OneToMany
    private List<ServiceRepairer> serviceRepairers;

    public Repairer(String name, String surname, String address, String login, String password, String certificate) {
        super(name, surname, address, login, password);
        this.certificate = certificate;
    }

    public Repairer(String name, String surname, String address, String login, String password) {
        super(name, surname, address, login, password);
    }

    public Repairer() {
    }
}
