package com.admin;

import com.database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AdminLoginController {
    @FXML
    TextField username;
    @FXML
    TextField password;
public Database data= new Database();
   public void login(ActionEvent event) throws SQLException {
       Alert alert;
       if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
           if (data.checkForAdmin(username.getText(), password.getText())) {
               moveToLogin(event);
           }
           else{
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("error Message");
               alert.setContentText("Inavild username or password");
               alert.showAndWait();
           }
       }
       else{
           alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("error Message");
           alert.setContentText("Please fill all blank fields");
           alert.showAndWait();
       }
   }
    public void moveToLogin(ActionEvent event){

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin/AdminHome.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Home Page"); // Set the title for the new window
                stage.setScene(new Scene(root));
                // Close the current (login) window
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();

                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


    }

}
