package controller;

import dao.BikeOrderDao;
import dao.ClientDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.bikeservice.Bike;
import model.bikeservice.BikeOrder;
import model.user.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClientOrderSummaryController {

    @FXML
    private TableView<BikeOrder> tableView;

    @FXML
    private TableColumn<BikeOrder, String> deliveryAddress;

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

        deliveryAddress.setCellValueFactory(new PropertyValueFactory<>("deliveryAddress"));
        orderState.setCellValueFactory(new PropertyValueFactory<>("orderState"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    private ObservableList<BikeOrder> loadOrdersForClient(Client client) {

        ObservableList<BikeOrder> observableOrderList = FXCollections.observableArrayList();
        observableOrderList.addAll(new ArrayList<>(BikeOrderDao.getInstance().getAllByClient(client)));

        return observableOrderList;
    }

}
