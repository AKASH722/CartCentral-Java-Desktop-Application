package com.Customer;

import com.database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerController {
    @FXML
    TextField username;
    @FXML
    Label welcomelabel;
    @FXML
    TextField password;

    public void getuserdata(ActionEvent event) {
        String usernm = username.getText();
        String pwd = password.getText();
        Database database = new Database();
        CustomerLogin.user = database.checkCustomerUser(usernm, pwd);
        database.close();
        if(CustomerLogin.user == null) {
            Dialog<String>  dialog = new Dialog<>();
            dialog.getDialogPane().getScene().getWindow().setOnCloseRequest(windowEvent -> dialog.close());
            dialog.setTitle("Error");
            dialog.setContentText("Invalid username or password");
            dialog.showAndWait();
        } else {
            welcomelabel.setText("Welcome " + CustomerLogin.user.name());
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/Customer/Home.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Home");
                stage.setScene(new Scene(root));
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
                stage.show();
            } catch (IOException e) {
                System.out.println("exception occured");
            }
        }

    }

    public void SignupButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Customer/CustomerSignUp.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Sign Up");
            stage.setScene(new Scene(root));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            stage.show();
        } catch (IOException e) {
            System.out.println("exception occured");
        }
    }
}
