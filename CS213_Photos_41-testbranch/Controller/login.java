package Controller;

import app.photo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class login {
// array list of user, not admin
// error


    @FXML
    private Button LoginButton;

    @FXML
    private TextField Username;

    @FXML
    private Label wrongPassword;

    @FXML
    void userLogin(ActionEvent event) {
        if(Username.getText().toLowerCase().equals("admin")){
            photo x = new photo();
            x.changeScene("/view/admin.fxml");
        }
        //if (){
        //    wrongPassword.setText("User can't be found");
        //}
    }
   
}
