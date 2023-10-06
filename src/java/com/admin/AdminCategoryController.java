package com.admin;


import com.database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.fxml.FXMLLoader.load;

public class AdminCategoryController {
    private Stage stage;
    private Scene scene;

    public Database data= new Database();
    @FXML
   public TextField c_name;
   public TextField a_name;
   public TextField sc_name;
   @FXML
   public TextField delete_catgoryName;
   public TextField delete_subcategoryname;
   @FXML
   public TextField sub_name;
   public TextField ct_name;
   public TextField ad_name;
   @FXML
   public TextField subdelete_catgoryName;
    public TextField subdelete_subcategoryname;

    public void AddCategory_to_insert(ActionEvent event) throws IOException {
        Parent root = load(getClass().getResource("/fxml/admin/CategoryInsert.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.setTitle("Update Student Details");

    }
    public void onClickAddCategory(ActionEvent event) throws SQLException, IOException {
        Alert alert;

        if (c_name.getText().isEmpty() || a_name.getText().isEmpty() || sc_name.getText().isEmpty() ){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }
        else {

                data.insertCategory(c_name.getText(),sc_name.getText(),a_name.getText());
                Dialog<String> dialogWarning = new Dialog<>();
                dialogWarning.getDialogPane().getScene().getWindow().setOnCloseRequest(windowEvent -> dialogWarning.close());
                dialogWarning.setTitle("Successful");
                dialogWarning.setContentText("Category added successfully");
                dialogWarning.showAndWait();
                Parent root = load(getClass().getResource("/fxml/admin/AddCategory.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setResizable(false);



        }
   }
    public void DeleteCategory_to_delete(ActionEvent event) throws IOException {
        Parent root = load(getClass().getResource("/fxml/admin/CategoryDelete.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
//        stage.setResizable(false);
        stage.setTitle("Update Student Details");

    }
   public void onClickDeleteCategory(ActionEvent event) throws SQLException {
        Alert alert;
       if (delete_catgoryName.getText().isEmpty() || delete_subcategoryname.getText().isEmpty()){
           alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error Message");
           alert.setContentText("Please fill all blank fields");
           alert.showAndWait();
       }
       else{

           if(data.deleteCategory(delete_catgoryName.getText(), delete_subcategoryname.getText())){
               Dialog<String> dialogWarning = new Dialog<>();
               dialogWarning.getDialogPane().getScene().getWindow().setOnCloseRequest(windowEvent -> dialogWarning.close());
               dialogWarning.setTitle("Successful");
               dialogWarning.setContentText("Category deleted successfully");
               dialogWarning.showAndWait();
           }
           else{
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error Message");
               alert.setContentText("Unable to delete category");
               alert.showAndWait();
           }
       }
   }
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
   public void onClickAddSubCategory(ActionEvent event) throws IOException, SQLException {
       Alert alert;

       if (ct_name.getText().isEmpty() || ad_name.getText().isEmpty() || sub_name.getText().isEmpty() ){
           alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error Message");
           alert.setContentText("Please fill all blank fields");
           alert.showAndWait();
       }
       else {

          data.insertSubCategory(ct_name.getText(),sub_name.getText(),ad_name.getText());
           Dialog<String> dialogWarning = new Dialog<>();
           dialogWarning.getDialogPane().getScene().getWindow().setOnCloseRequest(windowEvent -> dialogWarning.close());
           dialogWarning.setTitle("Successful");
           dialogWarning.setContentText("Sub-Category added successfully");
           dialogWarning.showAndWait();
           Parent root = load(getClass().getResource("/fxml/admin/AddCategory.fxml"));
           stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
           stage.setResizable(false);

       }
   }
    public void AddSubCategory_to_insert(ActionEvent event) throws IOException {
        Parent root = load(getClass().getResource("/fxml/admin/SubCategoryInsert.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
//        stage.setResizable(false);
        stage.setTitle("Sub-category added successfully");

    }
    public void onClickDeleteSubCategory(ActionEvent event) throws SQLException {
        Alert alert;
        if (subdelete_catgoryName.getText().isEmpty() || subdelete_subcategoryname.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }
        else{

            if(data.deleteSubCategory(subdelete_catgoryName.getText(), subdelete_subcategoryname.getText())){
                Dialog<String> dialogWarning = new Dialog<>();
                dialogWarning.getDialogPane().getScene().getWindow().setOnCloseRequest(windowEvent -> dialogWarning.close());
                dialogWarning.setTitle("Successful");
                dialogWarning.setContentText("Category deleted successfully");
                dialogWarning.showAndWait();
            }
            else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setContentText("Unable to delete category");
                alert.showAndWait();
            }
        }
    }
    public void DeleteSubCategory_to_delete(ActionEvent event) throws IOException {
        Parent root = load(getClass().getResource("/fxml/admin/SubCategoryDelete.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
//        stage.setResizable(false);
        stage.setTitle("Update Student Details");

    }
}
