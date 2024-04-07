package Controller;

import app.Photo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;

public class displayOwnImage {

    private Photo photo = Photo.getInstance();
    linkerClass link = linkerClass.getInstance();
    private userPage user = userPage.getInstance();
    private imageTracker track = imageTracker.getInstance();
    private ObservableList<imageAttributes> images;

    @FXML private Label CaptionLabel;
    @FXML private Label DateLabel;

    @FXML private Button ReturnButton;

    @FXML private TableColumn<?, ?> Tags;
    @FXML private TableColumn<?, ?> Values;

    @FXML private Button logoutButton;

    @FXML private ImageView ImageViewer;

    @FXML
    void logout(ActionEvent event) {
        photo.changeScene("/view/login.fxml");
    }

    @FXML
    void returnButton(ActionEvent event) {
        photo.changeScene("/view/insidePhotoAlbum.fxml");
    }

    public void initialize(){
        ImageViewer.setImage(track.getSelectedImage());
        
    }

}
