package model.bikeservice;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class BikeRepair extends Service {

    private double cost;

    private int partCount;

    @ManyToMany
    @JoinTable(
            name = "BikeRepair_BikePart",
            joinColumns = { @JoinColumn(name = "bikeRepair_id") },
            inverseJoinColumns = { @JoinColumn(name = "bikePart_id") }
    )
    private List<BikePart> bikeParts;

    public double getCost() {
        return cost;
    }

    public int getPartCount() {
        return partCount;
    }

    public void setCost(){
        for(BikePart bikePart: bikeParts){
            cost += bikePart.getCost();
        }
    }

    public void setPartCount(){
        partCount = bikeParts.size();
    }
}
