package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.photo;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class userPage implements Initializable {

    @FXML
    private Button RenameAlbumButton;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField AlbumNameInput;

    @FXML
    private Button CreateAlbumButton;

    @FXML
    private Button DelAlbumButton;

    @FXML
    private Button OpenAlbumButton;

    @FXML private TableColumn<photoAlbumList, SimpleStringProperty> AlbumName;

    @FXML private TableColumn<photoAlbumList, SimpleIntegerProperty> EarliestPhotoDate;

    @FXML private TableColumn<photoAlbumList, SimpleIntegerProperty> LatestPhotoDate;

    @FXML private TableColumn<photoAlbumList, SimpleIntegerProperty> NumberOfPhotos;

    @FXML private TableView<photoAlbumList> table;

    ObservableList<photoAlbumList> photoAlbum = FXCollections.observableArrayList(
        new photoAlbumList("mon", 0, 0, 0),
        new photoAlbumList("smon", 0, 0, 0)
    );


    @FXML
    void createAlbum(ActionEvent event) {
       // photoAlbumList newAlbum = new photoAlbumList(AlbumNameInput.getText(),0, 0, 0);
       // table.getItems().add(newAlbum);
    }

    @FXML
    void delButton(ActionEvent event) {

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
        
        }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        AlbumName.setCellValueFactory(new PropertyValueFactory<photoAlbumList,SimpleStringProperty>("AlbumName"));
        NumberOfPhotos.setCellValueFactory(new PropertyValueFactory<photoAlbumList,SimpleIntegerProperty>("NumberOfPhotos"));
        EarliestPhotoDate.setCellValueFactory(new PropertyValueFactory<photoAlbumList,SimpleIntegerProperty>("EarliestPhotoDate"));
        LatestPhotoDate.setCellValueFactory(new PropertyValueFactory<photoAlbumList,SimpleIntegerProperty>("LatestPhotoDate"));
    
        table.setItems(photoAlbum);
    }

}

