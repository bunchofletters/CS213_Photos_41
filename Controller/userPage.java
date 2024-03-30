package Controller;

import app.photo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class userPage {

    @FXML
    private ListView<?> PhotoAlbumList;

    @FXML
    private Button logoutButton;

    @FXML
    void logout(ActionEvent event) {
        photo x = new photo();
        x.changeScene("/view/login.fxml");
    }

}