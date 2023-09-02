package customer.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    TextField username;
    @FXML
    Label welcomelabel;
    @FXML
    TextField password;

    public void getuserdata()
    {
      String usernm =  username.getText();
      String pwd  = password.getText();
        System.out.println("username : " + usernm + " pwd : " + pwd);
    }
     public void SignupButton(ActionEvent event)
     {
         try {
             // Load the SignUp.fxml file
             Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));

             // Create a new stage for the SignUp view
             Stage stage = new Stage();
             stage.setTitle("Sign Up"); // Set the title for the new window
             stage.setScene(new Scene(root));

             // Close the current (login) window
             Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             currentStage.close();

             // Show the SignUp window
             stage.show();
         }

         catch (IOException e )
         {
             System.out.println("exception occured");
         }

     }

}
