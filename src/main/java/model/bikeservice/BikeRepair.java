package model.bikeservice;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class BikeRepair extends Service {

    @ManyToMany
    private List<BikePart> bikeParts;
}
