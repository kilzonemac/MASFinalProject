package controller;

import dao.BikeDao;
import dao.ClientDao;
import dao.SellerDao;
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
import model.user.Seller;
import view.LoginDialog;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class OrderController implements Initializable {

    @FXML
    private TableColumn<Bike, String> modelName;

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
    private TableColumn<Bike, Bike> buttonTableColumn;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    private Map<Bike, CheckBox> orderedBikes = new HashMap<>();

    private Stage orderStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //add your data to the table here.
        FilteredList<Bike> filteredData = new FilteredList<>(loadBikes(), p -> true);

        searchButton.setOnAction(event -> {
            filteredData.setPredicate(bike -> bike.getBikeModel().getModelName().toLowerCase().contains(searchTextField.getText().toLowerCase()));
        });

        tableView.setItems(filteredData);

        modelName.setCellValueFactory(p -> p.getValue().getBikeModel().modelNameProperty());
        bikeType.setCellValueFactory(p -> p.getValue().getBikeModel().bikeTypeProperty());
        color.setCellValueFactory(p -> p.getValue().getBikeModel().colorProperty());
        manufacture.setCellValueFactory(p -> p.getValue().getBikeModel().manufactureProperty());
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        buttonTableColumn.setCellValueFactory(
                param -> new ReadOnlyObjectWrapper(param.getValue())
        );

        buttonTableColumn.setCellFactory(param -> new TableCell<Bike, Bike>() {
            private final CheckBox checkBox = new CheckBox();

            @Override
            protected void updateItem(Bike bike, boolean empty) {
                super.updateItem(bike, empty);

                if (bike == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(checkBox);
                orderedBikes.put(bike, checkBox);
            }
        });
    }

    @FXML
    public void summaryButtonAction() {
        List<Bike> bikeList = orderedBikes.entrySet().stream().filter(e -> e.getValue().isSelected()).map(Map.Entry::getKey).collect(Collectors.toList());

        if (bikeList.size() > 0) {
            LoginDialog loginDialog = new LoginDialog(orderStage);

            loginDialog.showAndWait();

            Client client = null;

            if(!(loginDialog.getLogin() == null || loginDialog.getPassword() == null)){
                client = ClientDao.getInstance().getByLoginAndPassword(loginDialog.getLogin(), loginDialog.getPassword());
            }

            loadSummaryScene(bikeList, client);
        }

    }

    @FXML
    public void loadSellerWindow() {
        LoginDialog loginDialog = new LoginDialog(orderStage);

        loginDialog.showAndWait();

        Seller seller = null;

        if(!(loginDialog.getLogin() == null || loginDialog.getPassword() == null)){
            seller = SellerDao.getInstance().getByLoginAndPassword(loginDialog.getLogin(), loginDialog.getPassword());
        }

        if(seller != null) {
            try {
                //Load second scene
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("sellerPanel.fxml")));
                Parent root = loader.load();

                SellerController sellerController = loader.getController();

                sellerController.setOrderStage(orderStage);


                //Show scene 2 in new window
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.setTitle("Seller Panel");
                stage.show();

                sellerController.setSellerStage(stage);

                //hide main stage
                orderStage.hide();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Podano błędne dane logowania", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public ObservableList<Bike> loadBikes() {

        ObservableList<Bike> observableBikeList = FXCollections.observableArrayList();
        observableBikeList.addAll(BikeDao.getInstance().getAll().stream().filter(bike -> bike.getBikeOrder() == null).collect(Collectors.toList()));

        return observableBikeList;
    }

    private void loadSummaryScene(List<Bike> bikeList, Client client) {
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

            //close main stage
            orderStage.close();

            summaryController.transferData(bikeList, client);
            summaryController.setOrderStage(orderStage);
            summaryController.setStage(stage);
            summaryController.setOrderController(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public TableView<Bike> getTableView() {
        return tableView;
    }

    public Map<Bike, CheckBox> getOrderedBikes() {
        return orderedBikes;
    }

    public void setStage(Stage stage) {
        this.orderStage = stage;
    }

}
