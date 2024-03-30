package Controller;

import app.photo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputDialog;

public class admin {

    private static login login;

    @FXML
    void backToLogin(ActionEvent event) {
        photo x = new photo();
        x.changeScene("/view/login.fxml");
    }   

    @FXML
    void createUser(ActionEvent event) {
        TextInputDialog username = new TextInputDialog();
        username.setTitle("Add User");
        username.setHeaderText("Name of User");
        username.showAndWait().ifPresent(string -> login.getArrayList().add(string));
    }

    @FXML
    void deleteUser(ActionEvent event) {

    }

    @FXML
    void listUser(ActionEvent event) {
        for (int i = 0; i<login.getArrayList().size();i++){
            System.out.println(login.getArrayList().get(i));
        }
    }

    public void setLogin(login x){
        login = x;
    }

}
