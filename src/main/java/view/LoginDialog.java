package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginDialog extends Stage {

    private String login;
    private String password;

    public LoginDialog(Stage owner) {
        super();
        initOwner(owner);
        setTitle("Panel logowania");
        Group root = new Group();
        Scene scene = new Scene(root, 280, 100, Color.WHITE);
        setScene(scene);

        GridPane gridpane = new GridPane();

        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);

        Label userNameLbl = new Label("Login: ");
        gridpane.add(userNameLbl, 0, 1);

        Label passwordLbl = new Label("Hasło: ");
        gridpane.add(passwordLbl, 0, 2);
        final TextField userNameFld = new TextField("example@gmail.com");
        userNameFld.setMinWidth(230);
        gridpane.add(userNameFld, 1, 1);

        final PasswordField passwordFld = new PasswordField();
        passwordFld.setText("admin");
        gridpane.add(passwordFld, 1, 2);

        Button login = new Button("Zaloguj");

        login.setOnAction(event -> {
            setLogin(userNameFld.getText());
            setPassword(passwordFld.getText());
            close();
        });

        gridpane.add(login, 1, 3);
        GridPane.setHalignment(login, HPos.RIGHT);
        root.getChildren().add(gridpane);
        this.setResizable(false);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
