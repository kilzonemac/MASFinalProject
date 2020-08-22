import controller.OrderController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.bikeservice.Bike;
import model.bikeservice.BikeModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();

//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//
//        session.save(new Bike(LocalDate.now(),1000, "5409661", new BikeModel("Affix Sport","Górski", "Czarny","KROSS")));
//        session.save(new Bike(LocalDate.now(),3000, "9008723", new BikeModel("Legion L20","BMX", "Biały","MONGOOSE")));
//        session.save(new Bike(LocalDate.now(),5000, "4680428", new BikeModel("Gan Disk 1.0","Szosowy", "Szary","PINARELLO")));
//        session.save(new Bike(LocalDate.now(),3999, "7835018", new BikeModel("Monsun 3","MTB", "Czarny","ROMET")));
//        session.save(new Bike(LocalDate.now(),4499, "8224040", new BikeModel("Aspre 2","Gravel", "Szary","ROMET")));
//        session.save(new Bike(LocalDate.now(),5000, "5830269", new BikeModel("Level 6.0","Górski", "Limonkowy","KROSS")));
//        session.save(new Bike(LocalDate.now(),5499, "2349187", new BikeModel("Gan Disk 2.0","Szosowy", "Biały","PINARELLO")));
//        session.save(new Bike(LocalDate.now(),5000, "1169464", new BikeModel("Hexagon 7.0","Górski", "Zielony","KROSS")));
//        session.save(new Bike(LocalDate.now(),3999, "6729273", new BikeModel("Hexagon 6.0","Górski", "Czarny","KROSS")));
//        session.save(new Bike(LocalDate.now(),6999, "4620428", new BikeModel("Gan Disk 3.0","Szosowy", "Szary","PINARELLO")));
//        session.save(new Bike(LocalDate.now(),7999, "1680628", new BikeModel("Gan Disk 4.0","Szosowy", "Czarny","PINARELLO")));
//        session.save(new Bike(LocalDate.now(),8999, "3685428", new BikeModel("Gan Disk 5.0","Szosowy", "Czerwony","PINARELLO")));
//        session.save(new Bike(LocalDate.now(),9999, "4484668", new BikeModel("Gan Disk 6.0","Szosowy", "Szary","PINARELLO")));
//        session.save(new Bike(LocalDate.now(),2999, "3889787", new BikeModel("Hexagon 5.0","Górski", "Czarny","KROSS")));
//        session.save(new Bike(LocalDate.now(),1999, "5147907", new BikeModel("Hexagon 4.0","Górski", "Czarny","KROSS")));
//        session.save(new Bike(LocalDate.now(),1499, "6349187", new BikeModel("Hexagon 3.0","Górski", "Czerwony","KROSS")));
//
//
//        session.getTransaction().commit();
//
//        session.close();

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("order.fxml")));
        Parent root = loader.load();

        OrderController controller = loader.getController();
        controller.setStageAndSetupListeners(primaryStage, sessionFactory);


        primaryStage.setTitle("Serwis rowerowy");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
