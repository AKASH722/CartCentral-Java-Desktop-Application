package admin.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader= new FXMLLoader(Login.class.getResource("Login.fxml"));
        Scene scene= new Scene(fxmlLoader.load());
        stage.setTitle("Admin");
        stage .setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
