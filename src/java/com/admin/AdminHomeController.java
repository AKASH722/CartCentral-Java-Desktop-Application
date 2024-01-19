package com.admin;

import com.Records.Products;
import com.database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AdminHomeController {
    private Stage stage;
    @FXML
    private StackPane homePane;
    @FXML
    BorderPane internalPane;
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
        Database database = new Database();
        ArrayList<Products> productsList = new ArrayList<>();
        database.getAllProducts(productsList);
        displayProducts(productsList);
    }
    private void displayProducts(ArrayList<Products> productsList) {
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox(10);
        for (Products products : productsList) {
            HBox productContainer = new HBox(10);
            VBox detailsContainer = new VBox(5);
            Label nameLabel = new Label("Product Name: " + products.productName());
            Label priceLabel = new Label("Price: " + products.productPrice());
            Label descriptionLabel = new Label("Description: " + products.productDescription());
            nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            priceLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
            descriptionLabel.setStyle("-fx-font-size: 12px;");
            detailsContainer.getChildren().addAll(nameLabel, priceLabel, descriptionLabel);
            HBox buttonContainer = new HBox();
            productContainer.getChildren().addAll(detailsContainer);
            vBox.getChildren().add(productContainer);
        }
        scrollPane.setContent(vBox);
        internalPane.setCenter(scrollPane);
    }
    public void onClickViewOrders(ActionEvent event){}
    public void onClickUsers(ActionEvent event){}
    public void onClickViewMerchant(ActionEvent event){}


}
