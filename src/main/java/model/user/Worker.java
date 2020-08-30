package model.user;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@MappedSuperclass
public abstract class Worker extends User {

    @Column
    private String NIP;

    @Column(nullable = false)
    private LocalDate hireDate;

    @Column(nullable = false)
    @ElementCollection
    private List<LocalDate> fireDate;

    @Column(nullable = false)
    private double salary;

    private double salaryBonus;

    public Worker(String name, String surname, String address, String login, String password, LocalDate hireDate, double salary) {
        super(name, surname, address, login, password);
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public void countBonus(){
        if (DAYS.between(hireDate, LocalDate.now()) > 365){
            salaryBonus = 1000;
        }
    }

    protected Worker() {
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalaryBonus() {
        return salaryBonus;
    }

    public void setSalaryBonus(double salaryBonus) {
        this.salaryBonus = salaryBonus;
    }
}
