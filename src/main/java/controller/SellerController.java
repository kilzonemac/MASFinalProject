package controller;

import dao.BikeDao;
import dao.ClientDao;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import model.user.Client;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class SellerController implements Initializable {

    @FXML
    private TableView<Client> tableView;

    @FXML
    private TableColumn<Client, String> firstName;

    @FXML
    private TableColumn<Client, String> surname;

    @FXML
    private TableColumn<Client, String> address;

    @FXML
    private TableColumn<Client, Integer> orderCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setItems(loadClients());

        firstName.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        orderCount.setCellValueFactory(new PropertyValueFactory<>("orderCount"));

        tableView.setRowFactory( tv -> {
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Client rowData = row.getItem();
                    openOrderSummaryView(rowData);
                }
            });
            return row ;
        });

    }

    private void openOrderSummaryView(Client client){
        try {
            //Load second scene
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("clientOrderSummary.fxml")));
            Parent root = loader.load();

            ClientOrderSummaryController clientOrderSummaryController = loader.getController();

            //Show scene 2 in new window
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.setTitle("Client Orders");
            stage.show();

            clientOrderSummaryController.setOrderSummaryView(client);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private ObservableList<Client> loadClients() {

        ObservableList<Client> observableClientList = FXCollections.observableArrayList();
        observableClientList.addAll(new ArrayList<>(ClientDao.getInstance().getAll()));

        return observableClientList;
    }

}
