package com.Customer;

import com.Records.Products;
import com.database.Database;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class HomeController {
    @FXML
    TextField searchBox;
    @FXML
    BorderPane internalPane;

    public void onSearchAction(ActionEvent actionEvent) {
        String text = searchBox.getText();
        Dialog<String> dialog = new Dialog<>();
        dialog.getDialogPane().getScene().getWindow().setOnCloseRequest(windowEvent -> dialog.close());
        if (text.isEmpty()) {
            dialog.setTitle("Warning");
            dialog.getDialogPane().setContentText("Please enter a title to search");
            dialog.show();
        } else {
            Database database = new Database();
            ArrayList<Products> productList = new ArrayList<>();
            database.SearchProducts(productList, text);
            database.close();
            if (productList.isEmpty()) {
                dialog.setTitle("Warning");
                dialog.getDialogPane().setContentText("Sorry we couldn't find any hotels with the title " + text);
                dialog.show();
            } else {
                displayProducts(productList);
            }
        }
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
            Button addToCartButton = new Button("Add to Cart");
            addToCartButton.setId(String.valueOf(products.productId()));
            addToCartButton.setOnAction(event -> onClickAddToCart(event)); // Assuming onClickAddToCart takes an ActionEvent parameter

            productContainer.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            addToCartButton.setStyle("-fx-text-fill: #d66bef; -fx-font-size: 13;");

            buttonContainer.getChildren().addAll(addToCartButton);
            productContainer.getChildren().addAll(detailsContainer, buttonContainer);

            vBox.getChildren().add(productContainer);
        }

        scrollPane.setContent(vBox);
        internalPane.setCenter(scrollPane);
    }


    private void onClickAddToCart(ActionEvent actionEvent) {
    }

    public void onViewAllProducts(ActionEvent actionEvent) {
        Database database = new Database();
        ArrayList<Products> productList = new ArrayList<>();
        database.getAllProducts(productList);
        database.close();
        displayProducts(productList);
    }

    public void onClickAccount(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Customer/CustomerAccount.fxml"));
            internalPane.setCenter(root);
        } catch (IOException e) {
            System.out.println("exception occured");
        }
    }

    public void onClickLogout(ActionEvent actionEvent) {
        CustomerLogin.user = null;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
