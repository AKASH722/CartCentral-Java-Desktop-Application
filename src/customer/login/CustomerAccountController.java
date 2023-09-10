package customer.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerAccountController implements Initializable {
    String address;
    String username;
    String bankaccount;

    String emailid;
    long mobilenumber;
    @FXML
    TextField mobilenumbertextfeild;

    @FXML
    TextArea addresstextfield;
    @FXML
    TextField usernametextfeild;
    @FXML
    TextField emailtextfeild;
    @FXML
    TextField Banktextfeild;

     public void intialdetails()
     {
         mobilenumbertextfeild.setText("111111111");
         addresstextfield.setText("kya kar loge mera address jan kar ");
         usernametextfeild.setText("You know it ");
         emailtextfeild.setText("can't remember my email ");
         Banktextfeild.setText("Laxmi Cheat Fund ");
     }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        intialdetails();
    }

    public void useredit(ActionEvent e )
    {
        username= usernametextfeild.getText();
        System.out.println("username is " + username);
    }


    public void mobilenumberedit(ActionEvent e )
    {
        mobilenumber = Long.parseLong(mobilenumbertextfeild.getText());
        System.out.println("mobilenumber is " + mobilenumber);
    }
    public void emailedit(ActionEvent e )
    {
       emailid= emailtextfeild.getText();
        System.out.println("email is " + emailid);
    }

    public void addressedit(ActionEvent e )
    {
        address= addresstextfield.getText();
        System.out.println("address is " + address);
    }

    public void bankaccountedit(ActionEvent e )
    { bankaccount= Banktextfeild.getText();
        System.out.println("bankaccount is " + bankaccount);
    }

}
