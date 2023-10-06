package com.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminSubCategoryController {
      public void onClickAddCategory(ActionEvent event){}
      public void onClickDeleteCategory(ActionEvent event){}
      public void onClickViewCategory(ActionEvent event){}
      public void onClickHome(ActionEvent event){
          try {
              Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin/AdminHome.fxml"));
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
}
