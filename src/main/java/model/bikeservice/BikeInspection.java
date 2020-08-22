package model.bikeservice;

import model.bikeservice.enums.BikeInspectionType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class BikeInspection extends Service {

    @Enumerated(EnumType.ORDINAL)
    private BikeInspectionType bikeInspectionType;
}
