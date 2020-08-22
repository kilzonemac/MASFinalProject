package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.bikeservice.Bike;
import model.bikeservice.BikeOrder;
import model.bikeservice.enums.OrderState;
import model.user.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SummaryController implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private TextField street;

    @FXML
    private TextField city;

    @FXML
    private TextField postCode;

    @FXML
    private Label bikeLabel;

    @FXML
    private Label acceptLabel;

    @FXML
    private Label insertLabel;

    @FXML
    private GridPane inputGrid;

    @FXML
    private BorderPane pickedModel;

    @FXML
    private Button acceptButton;

    @FXML
    private HBox summaryButtons;

    private Bike bike;

    private SessionFactory sessionFactory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pickedModel.setVisible(false);
        summaryButtons.setVisible(false);
        acceptLabel.setVisible(false);

    }

    @FXML
    public void buttonSummarize() {
        if (!name.getText().equals("") && !surname.getText().equals("") && !email.getText().equals("") && !phone.getText().equals("") && !street.getText().equals("") && !city.getText().equals("") && !postCode.getText().equals("")) {

            inputGrid.setDisable(true);

            pickedModel.setVisible(true);
            summaryButtons.setVisible(true);
            acceptLabel.setVisible(true);

            insertLabel.setVisible(false);
            acceptButton.setVisible(false);

            bikeLabel.setText(bike.getBikeModel().getModelName() + ", " + bike.getBikeModel().getBikeType() + ", " + bike.getBikeModel().getColor() + ", " + bike.getBikeModel().getManufacture());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Wprowadź poprawnie wszystkie pola", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void editButton() {
        inputGrid.setDisable(false);

        pickedModel.setVisible(false);
        summaryButtons.setVisible(false);
        acceptLabel.setVisible(false);

        insertLabel.setVisible(true);
        acceptButton.setVisible(true);
    }

    @FXML
    public void acceptOrder() {
        BikeOrder bikeOrder = summarizeOrder();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(bikeOrder);
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Zamówienie zostało złożone poprawnie", ButtonType.OK);
        alert.showAndWait();

        Platform.exit();

    }

    @FXML
    public void cancelOrder() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Czy napewno chcesz anulować ?", ButtonType.CANCEL, ButtonType.YES);
        Optional<ButtonType> resultbutton = alert.showAndWait();

        if (resultbutton.get().equals(ButtonType.YES)) {
            Platform.exit();
        }
    }

    public BikeOrder summarizeOrder() {
        String address = buildAddress(phone.getText(), street.getText(), city.getText(), postCode.getText());
        BikeOrder bikeOrder = new BikeOrder(OrderState.ORDERED, bike.getCost(), java.time.LocalDate.now(), address);

        bikeOrder.setClient(new Client(name.getText(), surname.getText(), address, String.valueOf(Math.random() * 100000), "admin"));

        return bikeOrder;
    }

    public void transferData(Bike bike, SessionFactory sessionFactory) {
        this.bike = bike;
        this.sessionFactory = sessionFactory;
    }

    private String buildAddress(String phone, String street, String city, String postCode) {
        return phone +
                "," +
                street +
                "," +
                city +
                "," +
                postCode;
    }
}
