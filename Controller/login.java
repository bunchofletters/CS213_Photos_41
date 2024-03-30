package Controller;

import app.photo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class login {
    private static admin admin;
    private static ObservableList<String> users = FXCollections.observableArrayList();


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
            if(admin == null){
                admin y = new admin();
                y.setLogin(this);
            }
            x.changeScene("/view/admin.fxml");
        } else if (!Username.getText().toLowerCase().equals("admin")) {
            for(int i = 0; i < users.size(); i++){
                if (Username.getText().equals(users.get(i)));
                photo x = new photo();
                x.changeScene("/view/userPage.fxml");
            }
                wrongPassword.setText("Can't Find User");
        } 
        
    }  


    public ObservableList<String> getList(){
        return users;
    }
    
}