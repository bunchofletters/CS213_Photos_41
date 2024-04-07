package Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

import app.Photo;

public class login{
    private static ObservableList<String> users = FXCollections.observableArrayList();
    private Photo x = Photo.getInstance();
    private static String userLogined = "";
    private static login instance;

    @FXML private Button LoginButton;

    @FXML private TextField Username;

    @FXML private Label wrongPassword;

// -------------------------------------------------------------------------------------

// -------------------------------------------------------------------------------------

    /**
     * Create a single instance of login
     * @return the single login instance
     */
    public static login getInstance() {
        if (instance == null) {
            instance = new login();
        }
        return instance;
    }

    @FXML
    void userLogin() {
        if(Username.getText().toLowerCase().equals("admin")){
            x.changeScene("/view/admin.fxml");
        } 
        else if(Username.getText().toLowerCase().equals("stock")){
            x.changeScene("/view/stockImageUser.fxml");
        }
        else if (!Username.getText().toLowerCase().equals("admin")) {
            for(int i = 0; i < users.size(); i++){
                if (Username.getText().toLowerCase().equals(users.get(i))){
                    userLogined = Username.getText().toLowerCase();
                    x.changeScene("/view/userPage.fxml");
                }
            }
            if(!Username.getText().equals("")){
                wrongPassword.setText("Can't Find User");
            }    
        }    
    }  

// -------------------------------------------------------------------------------------

    public String getUser(){
        return userLogined;
    }

// -------------------------------------------------------------------------------------

    public ObservableList<String> getList(){
        return users;
    }
    
    public void setUserList(ArrayList<String> savedUser){
        users.addAll(savedUser);
    }
}
