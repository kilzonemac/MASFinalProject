import controller.OrderController;
import dao.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //uncomment to create test data in database
        //HibernateUtil.createTestData();

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("order.fxml")));
        Parent root = loader.load();

        OrderController controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setTitle("Serwis rowerowy");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
