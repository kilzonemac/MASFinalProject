package model.bikeservice;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Bike {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    @Column(nullable = false)
    private double cost;

    @Column(unique = true, nullable = false)
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name="bikeModel_id", nullable=false)
    private BikeModel bikeModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bikeOrder_id")
    private BikeOrder bikeOrder;

    public Bike(LocalDate deliveryDate, double cost, String serialNumber, BikeModel bikeModel) {
        this.deliveryDate = deliveryDate;
        this.serialNumber = serialNumber;
        this.cost = cost;

        setBikeModel(bikeModel);
    }

    public Bike() {
    }

    public BikeOrder getBikeOrder() {
        return bikeOrder;
    }

    public void setBikeOrder(BikeOrder bikeOrder) {
        this.bikeOrder = bikeOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BikeModel getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(BikeModel bikeModel) {
        bikeModel.addBike(this);
        this.bikeModel = bikeModel;
    }
}
