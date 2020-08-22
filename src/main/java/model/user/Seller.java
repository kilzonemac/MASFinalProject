package model.user;

import javax.persistence.Entity;

@Entity
public class Seller extends Worker {

    public Seller(String name, String surname, String address, String login, String password) {
        super(name, surname, address, login, password);
    }
}
