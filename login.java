<<<<<<< HEAD
<<<<<<< HEAD:Controller/login.java
package Controller;

import app.photo;
=======


>>>>>>> 70651fe (Reorder to comply with assignment):login.java
=======


>>>>>>> 70651fe (Reorder to comply with assignment)
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class login{
    private static admin admin;
    private static ObservableList<String> users = FXCollections.observableArrayList();
<<<<<<< HEAD
<<<<<<< HEAD:Controller/login.java
    private photo x = new photo();

=======
    private Photo x = new Photo();
    private static String userLogined;
    private dataHolder data;
>>>>>>> 70651fe (Reorder to comply with assignment):login.java
=======
    private Photo x = new Photo();
    private static String userLogined;
    private dataHolder data;
>>>>>>> 70651fe (Reorder to comply with assignment)
    @FXML
    private Button LoginButton;

    @FXML
    private TextField Username;

    @FXML
    private Label wrongPassword;

<<<<<<< HEAD
<<<<<<< HEAD:Controller/login.java
=======
=======
>>>>>>> 70651fe (Reorder to comply with assignment)
    void initialize(){
        data = new dataHolder();
        if(data.getUsers() != null){
            users = data.getUsers();
        }
    }

<<<<<<< HEAD
>>>>>>> 70651fe (Reorder to comply with assignment):login.java
=======
>>>>>>> 70651fe (Reorder to comply with assignment)
    @FXML
    void userLogin(ActionEvent event) {
        if(Username.getText().toLowerCase().equals("admin")){
            if(admin == null){
                admin y = new admin();
                y.setLogin(this);
            }
<<<<<<< HEAD
<<<<<<< HEAD:Controller/login.java
            x.changeScene("/view/admin.fxml");
        } 
        else if(Username.getText().toLowerCase().equals("stock")){
            x.changeScene("/view/stockImageUser.fxml");
        }
        else if (!Username.getText().toLowerCase().equals("admin")) {
            for(int i = 0; i < users.size(); i++){
                if (Username.getText().equals(users.get(i)));
                x.changeScene("/view/userPage.fxml");
            }
                wrongPassword.setText("Can't Find User");
        } 
        
    }  

=======
=======
>>>>>>> 70651fe (Reorder to comply with assignment)
            x.changeScene("admin.fxml");
        } 
        else if(Username.getText().toLowerCase().equals("stock")){
            x.changeScene("stockImageUser.fxml");
        }
        else if (!Username.getText().toLowerCase().equals("admin")) {
            for(int i = 0; i < users.size(); i++){
                if (Username.getText().toLowerCase().equals(users.get(i))){
                    userLogined = Username.getText().toLowerCase();
                    x.changeScene("userPage.fxml");
                }
            }
            if(!Username.getText().equals("")){
                wrongPassword.setText("Can't Find User");
            }
        }    
    }  

    public String getUser(){
        return userLogined;
    }
<<<<<<< HEAD
>>>>>>> 70651fe (Reorder to comply with assignment):login.java
=======
>>>>>>> 70651fe (Reorder to comply with assignment)

    public ObservableList<String> getList(){
        return users;
    }
    
}