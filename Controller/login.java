package Controller;

import java.util.ArrayList;

import app.photo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class login {
    private static admin admin;
    private static ArrayList<String> users = new ArrayList<>();
    @FXML
    private Button LoginButton;

    @FXML
    private TextField Username;

    @FXML
    void userLogin(ActionEvent event) {
        if(Username.getText().toLowerCase().equals("admin")){
            photo x = new photo();
            if(admin == null){
                admin y = new admin();
                y.setLogin(this);
            }
            x.changeScene("/view/admin.fxml");
        }
    }

    public ArrayList<String> getArrayList(){
        return users;
    }
    
}
