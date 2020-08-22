package model.user;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public abstract class Worker extends User {

    private String NIP;

    private LocalDate hireDate;

    private LocalDate fireDate;

    private double salary;

    private double salaryPremium;

    public Worker(String name, String surname, String address, String login, String password) {
        super(name, surname, address, login, password);
    }

    protected Worker() {
    }
}
