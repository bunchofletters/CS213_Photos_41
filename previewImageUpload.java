import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class previewImageUpload {

    private listOfPhotos photoList;
    linkerClass link = linkerClass.getInstance();

    private imageTracker track = imageTracker.getInstance();

    @FXML private Button SaveButton;

    @FXML private ImageView imagePreviewer;

    @FXML private Pane pane;

    @FXML
    void save(ActionEvent event) {
        photoList.addPhoto(track.getSelectedImage());
        Stage stage = (Stage) SaveButton.getScene().getWindow();
        stage.close();
       
    }

    public void initialize() {
    userPage user = userPage.getInstance();
    photoList = link.getImageList(user.getAlbum());
    imagePreviewer.setImage(track.getSelectedImage());

    }
}
