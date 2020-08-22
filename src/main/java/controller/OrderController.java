package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.bikeservice.Bike;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    public TableColumn<Bike, String> modelName;
    @FXML
    private TableView<Bike> tableView;
    @FXML
    private TableColumn<Bike, String> bikeType;

    @FXML
    private TableColumn<Bike, String> color;

    @FXML
    private TableColumn<Bike, String> manufacture;

    @FXML
    private TableColumn<Bike, Double> cost;

    @FXML
    private Label bikeLabel;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    private SessionFactory sessionFactory;

    private Stage orderStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //add your data to the table here.
        FilteredList<Bike> filteredData = new FilteredList<>(load(), p -> true);

        searchButton.setOnAction(event -> {
            filteredData.setPredicate(bike -> bike.getBikeModel().getModelName().contains(searchTextField.getText()));
        });

        tableView.setItems(filteredData);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Bike bike = tableView.getSelectionModel().getSelectedItem();

                bikeLabel.setText(bike.getBikeModel().getModelName() + ", " + bike.getBikeModel().getBikeType() + ", " + bike.getBikeModel().getColor() + ", " + bike.getBikeModel().getManufacture());
            }
        });

        modelName.setCellValueFactory(p -> p.getValue().getBikeModel().modelNameProperty());
        bikeType.setCellValueFactory(p -> p.getValue().getBikeModel().bikeTypeProperty());
        color.setCellValueFactory(p -> p.getValue().getBikeModel().colorProperty());
        manufacture.setCellValueFactory(p -> p.getValue().getBikeModel().manufactureProperty());
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

    }

    private ObservableList<Bike> load() {

        ObservableList<Bike> observableBikeList = FXCollections.observableArrayList();
        observableBikeList.addAll(Bike.getAllBikes());

        return observableBikeList;
    }

    @FXML
    public void pickBikeToOrder() {
        Bike bike = tableView.getSelectionModel().getSelectedItem();

        if (bike != null) {
            loadSummaryScene(bike);
        }

    }

    private void loadSummaryScene(Bike bike) {
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("data.fxml")));
            Parent root = loader.load();

            SummaryController summaryController = loader.getController();

            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.setTitle("Wprowadź dane");
            stage.show();

            //hide main stage
            orderStage.hide();

            summaryController.transferData(bike, sessionFactory);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setStageAndSetupListeners(Stage stage, SessionFactory sessionFactory) {
        this.orderStage = stage;
        this.sessionFactory = sessionFactory;
    }

}
