package controller;

import dao.BikeOrderDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.bikeservice.BikeOrder;
import model.user.Client;

import java.util.ArrayList;

public class ClientOrderSummaryController {

    @FXML
    private TableView<BikeOrder> tableView;

    @FXML
    private TableColumn<BikeOrder, String> street;

    @FXML
    private TableColumn<BikeOrder, String> city;

    @FXML
    private TableColumn<BikeOrder, String> postCode;

    @FXML
    private TableColumn<BikeOrder, String> orderState;

    @FXML
    private TableColumn<BikeOrder, String> cost;

    @FXML
    private TableColumn<BikeOrder, Integer> id;

    @FXML
    private Label clientName;

    public void setOrderSummaryView(Client client){
        clientName.setText(client.getName() + " " + client.getSurname());

        //add your data to the table here.
        tableView.setItems(loadOrdersForClient(client));

        String [] address = client.getAddress().split(",");

        street.setCellValueFactory(p -> new SimpleStringProperty(address[0]));
        city.setCellValueFactory(p -> new SimpleStringProperty(address[1]));
        postCode.setCellValueFactory(p -> new SimpleStringProperty(address[2]));

        orderState.setCellValueFactory(new PropertyValueFactory<>("orderState"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    private ObservableList<BikeOrder> loadOrdersForClient(Client client) {

        ObservableList<BikeOrder> observableOrderList = FXCollections.observableArrayList();
        observableOrderList.addAll(client.getBikeOrders());

        return observableOrderList;
    }

}
