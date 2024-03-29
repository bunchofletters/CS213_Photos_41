package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class login {

    @FXML
    private Button LoginButton;

    @FXML
    private TextField Username;

    @FXML
    void userLogin(ActionEvent event) throws IOException {
        loginIn();
    }

    private void loginIn() {
        
    }


}
