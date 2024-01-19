package com.Customer;

import com.Records.CartProducts;
import com.Records.Orders;
import com.Records.Products;
import com.Utilities.OrderNumber;
import com.database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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
                dialog.getDialogPane().setContentText("Sorry we couldn't find any products with the title " + text);
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
        Dialog<Integer> dialog = new Dialog<>();
        Long selectedProduct = Long.parseLong(((Button) actionEvent.getSource()).getId());
        dialog.setTitle("Add to Cart");
        dialog.setHeaderText("Enter the number of products:");
        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        dialog.getDialogPane().setContent(quantityField);
        ButtonType addToCartButton = new ButtonType("Add to Cart", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addToCartButton, ButtonType.CANCEL);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addToCartButton) {
                try {
                    return Integer.parseInt(quantityField.getText());
                } catch (NumberFormatException e) {
                    showAlert("Invalid input. Please enter a valid quantity.");
                    return null;
                }
            }
            return null;
        });
        Optional<Integer> result = dialog.showAndWait();
        Database database = new Database();
        result.ifPresent(quantity -> {
            if (database.hasEnoughProducts(selectedProduct, quantity)) {
                database.addToCart(selectedProduct, quantity, CustomerLogin.user.user_id());
                showAlert("Product added to cart successfully!");
            } else {
                showAlert("Not enough products in stock.");
            }
        });
        database.close();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
            System.out.println("exception occurred");
        }
    }

    public void onClickLogout(ActionEvent actionEvent) {
        CustomerLogin.user = null;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void onClickViewCart(ActionEvent actionEvent) {
        Database database = new Database();
        ArrayList<CartProducts> productList = new ArrayList<>();
        database.getCart(productList, CustomerLogin.user.user_id());
        database.close();
        Dialog<String> dialog = new Dialog<>();
        dialog.getDialogPane().getScene().getWindow().setOnCloseRequest(windowEvent -> dialog.close());
        if (productList.isEmpty()) {
            dialog.setTitle("Warning");
            dialog.getDialogPane().setContentText("We couldn't find any thing in cart");
            dialog.show();
        } else {
            displayCartProducts(productList);
        }
    }

    private void displayCartProducts(ArrayList<CartProducts> productList) {
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox(10);
        for (CartProducts products : productList) {
            HBox productContainer = new HBox(10);
            VBox detailsContainer = new VBox(5);
            Label nameLabel = new Label("Product Name: " + products.productName());
            Label priceLabel = new Label("Price: " + products.productPrice());
            Label descriptionLabel = new Label("Description: " + products.productDescription());
            Label quantityLabel = new Label("Quantity: " + products.quantity());
            nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            priceLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
            descriptionLabel.setStyle("-fx-font-size: 12px;");
            detailsContainer.getChildren().addAll(nameLabel, priceLabel, descriptionLabel, quantityLabel);
            HBox buttonContainer = new HBox();
            Button removeFromCart = new Button("Remove from cart");
            removeFromCart.setId(String.valueOf(products.productId()));
            removeFromCart.setOnAction(this::onClickRemoveCart);
            productContainer.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
            removeFromCart.setStyle("-fx-text-fill: #d66bef; -fx-font-size: 13;");
            productContainer.getChildren().addAll(detailsContainer, buttonContainer);
            vBox.getChildren().add(productContainer);
        }
        Button buyAll = new Button("Buy All");
        buyAll.setOnAction(this::onClickBuyAll);
        vBox.getChildren().add(buyAll);
        scrollPane.setContent(vBox);
        internalPane.setCenter(scrollPane);
    }

    private void onClickBuyAll(ActionEvent actionEvent) {
        Database database = new Database();
        ArrayList<CartProducts> productList = new ArrayList<>();
        database.getCart(productList, CustomerLogin.user.user_id());
        for (CartProducts product : productList) {
            if (!database.checkQuantity(product.productId(), product.quantity())) {
                showAlert("Not enough products in stock.: " + product.productName());
                return;
            }
        }
        String OrderNumber = new OrderNumber().getOrderNumber();
        for (CartProducts product : productList) {
            database.addToOrder(product.productId(), product.quantity(), OrderNumber, CustomerLogin.user.user_id(), product.productPrice() * product.quantity());
            database.minusQuantity(product.productId(), product.quantity());
        }
    }

    private void onClickRemoveCart(ActionEvent actionEvent) {
        Dialog<Integer> dialog = new Dialog<>();
        Long selectedProduct = Long.parseLong(((Button) actionEvent.getSource()).getId());
        dialog.setTitle("Remove Cart");
        dialog.setHeaderText("Enter the number of products:");
        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        dialog.getDialogPane().setContent(quantityField);
        ButtonType removeFromCart = new ButtonType("Remove from cart", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(removeFromCart, ButtonType.CANCEL);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == removeFromCart) {
                try {
                    return Integer.parseInt(quantityField.getText());
                } catch (NumberFormatException e) {
                    showAlert("Invalid input. Please enter a valid quantity.");
                    return null;
                }
            }
            return null;
        });
        Optional<Integer> result = dialog.showAndWait();
        Database database = new Database();
        result.ifPresent(quantity -> {
            if (database.hasEnoughProductInCart(selectedProduct, quantity, CustomerLogin.user.user_id())) {
                database.minusCartQuantity(selectedProduct, quantity, CustomerLogin.user.user_id());
            } else {
                database.removeProductFromCart(selectedProduct, CustomerLogin.user.user_id());
            }
            showAlert("Product removed from the cart successfully! Please refresh");
        });
        database.close();
    }

    public void onClickViewOrder(ActionEvent actionEvent) {
        Database database = new Database();
        ArrayList<Orders> ordersArrayList = new ArrayList<>();
        database.getAllOrders(ordersArrayList, CustomerLogin.user.user_id());
        displayOrders(ordersArrayList);
    }

    private void displayOrders(ArrayList<Orders> ordersArrayList) {
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox(10);
        for (Orders order : ordersArrayList) {
            HBox productContainer = new HBox(10);
            VBox detailsContainer = new VBox(5);
            Label nameLabel = new Label("Order Number: " + order.orderNumber());
            Label priceLabel = new Label("Price: $" + order.price());
            Label descriptionLabel = new Label("ProductName: " + order.product());
            Label quantityLabel = new Label("Quantity: " + order.quantity());
            detailsContainer.getChildren().addAll(nameLabel, priceLabel, descriptionLabel, quantityLabel);
            productContainer.getChildren().addAll(detailsContainer);
            vBox.getChildren().add(productContainer);
        }
        scrollPane.setContent(vBox);
        internalPane.setCenter(scrollPane);
    }

}
