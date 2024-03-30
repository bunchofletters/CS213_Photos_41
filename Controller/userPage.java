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

    @FXML
    private TableColumn<photoAlbumList, String> AlbumName;

    @FXML
    private TableColumn<photoAlbumList, Integer> EarliestPhotoDate;

    @FXML
    private TableColumn<photoAlbumList, Integer> LatestPhotoDate;

    @FXML
    private TableColumn<photoAlbumList, Integer> NumberOfPhotos;

    @FXML
    private TableView<photoAlbumList> table;

    //private static ObservableList<photoAlbumList> photoAlbum = FXCollections.observableArrayList();


    @FXML
    void createAlbum(ActionEvent event) {
        photoAlbumList photo = new photoAlbumList(AlbumNameInput.getText(), 0, 0, 0);
        ObservableList<photoAlbumList> photos = table.getItems();
        photos.add(photo);
        table.setItems(photos);
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
        AlbumName.setCellValueFactory(new PropertyValueFactory<photoAlbumList,String>("name"));
        NumberOfPhotos.setCellValueFactory(new PropertyValueFactory<photoAlbumList,Integer>("photoNum"));
        EarliestPhotoDate.setCellValueFactory(new PropertyValueFactory<photoAlbumList,Integer>("lowestDate"));
        LatestPhotoDate.setCellValueFactory(new PropertyValueFactory<photoAlbumList,Integer>("highDate"));
        
        
        
    }

}

