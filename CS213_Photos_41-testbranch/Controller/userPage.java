package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.photo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class userPage implements Initializable{

    @FXML
    private TextField AlbumNameInput;

    @FXML
    private Button CreateAlbumButton;

    @FXML
    private Button DelAlbumButton;

    @FXML
    private Button OpenAlbumButton;

    @FXML
    private ListView<String> PhotoAlbumList;

    private static ObservableList<String> photoAlbum = FXCollections.observableArrayList();

    @FXML
    private Button RenameAlbumButton;

    @FXML
    private Button logoutButton;

    @FXML
    void createAlbum(ActionEvent event) {
        if(!AlbumNameInput.getText().toLowerCase().equals("") && !AlbumNameInput.getText().toLowerCase().substring(0,1).equals(" ")){
            photoAlbum.add(AlbumNameInput.getText().toLowerCase());
        }

    }

    @FXML
    void delButton(ActionEvent event) {
        int item = PhotoAlbumList.getSelectionModel().getSelectedIndex();
        PhotoAlbumList.getItems().remove(item);
    }

    @FXML
    void logout(ActionEvent event) {
        photo x = new photo();
        x.changeScene("/view/login.fxml");
    }

    @FXML
    void openAlbum(ActionEvent event) {
        
    }

    @FXML
    void renameAlbum(ActionEvent event) {
        int item = PhotoAlbumList.getSelectionModel().getSelectedIndex();
            if(item != -1){
                String name = AlbumNameInput.getText().toLowerCase();
                photoAlbum.set(item, name);
            }
        }
        

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        PhotoAlbumList.setItems(photoAlbum);
    }

}
