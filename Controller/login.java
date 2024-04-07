package Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

import app.Photo;

/**
 * This class represents a login system.
 * @author Danny, Oscar
 */
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

     /**
     * Handles user login.
     * Login Credentials "admin", it changes the scene to the admin view.
     * Login Credentials "stock", it changes the scene to the stock image user view.
     * Login Credentials not "admin", it checks if the username exists in the users list.
     * If the username exists, it changes the scene to the user page view.
     * If the username does not exist, it displays an error message.
     */
    @FXML void userLogin() {
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

    /**
     * Returns the username of the logged in user.
     *
     * @return The username of the logged in user.
     */
    public String getUser(){
        return userLogined;
    }

// -------------------------------------------------------------------------------------

     /**
     * Returns the list of users.
     *
     * @return The list of users.
     */
    public ObservableList<String> getList(){
        return users;
    }
    
    public void setUserList(ArrayList<String> savedUser){
        users.addAll(savedUser);
    }
}
