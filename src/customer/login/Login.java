package customer.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Login extends Application {
    public static void main(String[] args) {
        System.out.println("hello ");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


       Scene scene ;
       Parent root;

        URL url = getClass().getResource("Login.fxml");
        try {
            if(url != null)
            {
                System.out.println("got the url");
                FXMLLoader loader = new FXMLLoader(url);
                root =loader.load(url);
                scene = new Scene(root);
                stage.setScene(scene);
               stage.setTitle("Login");
//                System.out.println("got the controller");
              Controller controller = loader.getController();
               controller.getuserdata();
            }
        }
        catch(NullPointerException e)
        {
            System.out.println("url not found");
            System.out.println(url);
        }
     stage.show();
    }
}


