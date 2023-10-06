package com.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminHomeController {
    private Stage stage;
    @FXML
    private StackPane homePane;
    public void onClickCategory(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin/AddCategory.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AddCategory Page"); // Set the title for the new window
            stage.setScene(new Scene(root));
            // Close the current (login) window
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void onClickSubCategory(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin/AddSub-Category.fxml"));
            Stage stage = new Stage();
            stage.setTitle("AddCategory Page"); // Set the title for the new window
            stage.setScene(new Scene(root));
            // Close the current (login) window
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickLogout(ActionEvent event){
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are logging out");
        alert.showAndWait();
        stage=(Stage)homePane.getScene().getWindow();
        System.out.println("You are Succesfully logout");
        stage.close();
    }
    public void onClickProduct(ActionEvent event){

    }
    public void onClickViewOrders(ActionEvent event){}
    public void onClickUsers(ActionEvent event){}
    public void onClickViewMerchant(ActionEvent event){}


}
