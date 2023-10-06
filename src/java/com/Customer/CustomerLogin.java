package com.Customer;

import com.Records.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerLogin extends Application {
    public static User user;

    public static void main(String[] args) {
        System.out.println("hello ");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene;
        try {
            System.out.println("got the url");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Customer/CustomerLogin.fxml"));
            scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.setResizable(false);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        stage.show();
    }
}


