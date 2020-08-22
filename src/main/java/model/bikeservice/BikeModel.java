package model.bikeservice;

import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Access(AccessType.PROPERTY)
public class BikeModel {

    private int id;

    private SimpleStringProperty modelName = new SimpleStringProperty();
    private SimpleStringProperty bikeType = new SimpleStringProperty();
    private SimpleStringProperty color = new SimpleStringProperty();
    private SimpleStringProperty manufacture = new SimpleStringProperty();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Bike> bikes;

    public BikeModel(String modelName, String bikeType, String color, String manufacture) {
        this.modelName = new SimpleStringProperty(modelName);
        this.bikeType = new SimpleStringProperty(bikeType);
        this.color = new SimpleStringProperty(color);
        this.manufacture = new SimpleStringProperty(manufacture);
    }

    public BikeModel() {
    }

    @Column(name="id")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="ModelName")
    public String getModelName() {
        return modelName.get();
    }

    public SimpleStringProperty modelNameProperty() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName.set(modelName);
    }

    @Column(name="BikeType")
    public String getBikeType() {
        return bikeType.get();
    }

    public SimpleStringProperty bikeTypeProperty() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType.set(bikeType);
    }

    @Column(name="Color")
    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    @Column(name="Manufacture")
    public String getManufacture() {
        return manufacture.get();
    }

    public SimpleStringProperty manufactureProperty() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture.set(manufacture);
    }
}
