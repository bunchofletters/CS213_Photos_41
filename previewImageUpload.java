import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class previewImageUpload {

    private listOfPhotos photoList;
    linkerClass link = linkerClass.getInstance();
    private userPage user = userPage.getInstance();

    private imageTracker track = imageTracker.getInstance();

    @FXML private Button AddCaptionButton;
    @FXML private Button AddTagButton;
    @FXML private Button CloseButton;
    @FXML private Button SaveButton;

    @FXML private Label CurrentCaptionLabel;

    @FXML private ImageView imagePreviewer;

    @FXML private Pane pane;

// -------------------------------------------------------------------------------------

    @FXML
    void save() {
        track.check = true;
        photoList.addPhoto(track.getSelectedImage());
        user.updateUserAlbum();
        Stage stage = (Stage) SaveButton.getScene().getWindow();
        stage.close();
       
    }

// -------------------------------------------------------------------------------------

    public void initialize() {
    userPage user = userPage.getInstance();
    photoList = link.getImageList(user.getAlbum());
    imagePreviewer.setImage(track.getSelectedImage());

    }

// -------------------------------------------------------------------------------------


    @FXML void addCaption(ActionEvent event) {
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Captions");
        td.setContentText("Please type enter a Caption: ");
        Optional<String> result = td.showAndWait();
        if (result.isPresent()){
            CurrentCaptionLabel.setText(result.get());
        }

        // still need to implement adding the caption to the actual photo, it should do a method call to imageTracker or something so it stores the caption with the image so if its abanoned it doesnt save so eveything should go to save
        
    }

// -------------------------------------------------------------------------------------
    private Stage popupStage;
    @FXML void addTag(ActionEvent event) {
        try {
            if (popupStage != null && popupStage.isShowing()) {
                popupStage.toFront();
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("tagViewPopup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL); 
            popupStage.setScene(scene);
            popupStage.setResizable(false);

            popupStage.setOnHidden(e -> {
             // need to implement
            });
            popupStage.showAndWait();;
                    
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

// -------------------------------------------------------------------------------------

    @FXML void close(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

// -------------------------------------------------------------------------------------

}
