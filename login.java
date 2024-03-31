

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
    private Photo x = new Photo();
    private static String userLogined;
    private dataHolder data;
    @FXML
    private Button LoginButton;

    @FXML
    private TextField Username;

    @FXML
    private Label wrongPassword;

    void initialize(){
        data = new dataHolder();
        if(data.getUsers() != null){
            users = data.getUsers();
        }
    }

    @FXML
    void userLogin(ActionEvent event) {
        if(Username.getText().toLowerCase().equals("admin")){
            if(admin == null){
                admin y = new admin();
                y.setLogin(this);
            }
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

    public ObservableList<String> getList(){
        return users;
    }
    
}