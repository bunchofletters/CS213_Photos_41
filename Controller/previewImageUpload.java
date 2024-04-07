package Controller;
<<<<<<< HEAD
import java.time.LocalDate;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
=======
import java.util.Optional;
>>>>>>> 4989309 (:))
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
<<<<<<< HEAD
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
=======
import javafx.scene.control.TextInputDialog;
>>>>>>> 4989309 (:))
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class previewImageUpload {

    private listOfPhotos photoList;
    linkerClass link = linkerClass.getInstance();
    private userPage user = userPage.getInstance();
<<<<<<< HEAD
    private ObservableList<imageAttributes> images;
=======

>>>>>>> 4989309 (:))
    private imageTracker track = imageTracker.getInstance();

    @FXML private Button AddCaptionButton;
    @FXML private Button AddTagButton;
    @FXML private Button CloseButton;
    @FXML private Button SaveButton;
<<<<<<< HEAD
    @FXML private Button AddValueButton;
=======
>>>>>>> 4989309 (:))

    @FXML private Label CurrentCaptionLabel;

    @FXML private ImageView imagePreviewer;

<<<<<<< HEAD
    @FXML private TableColumn<tagsAndValue, String> TagColum;
    @FXML private TableColumn<tagsAndValue, String> ValueColumn;
    @FXML private TableView<tagsAndValue> Table;

    @FXML private Pane pane;

    ObservableList<tagsAndValue> selectedTagsList = FXCollections.observableArrayList();

=======
    @FXML private Pane pane;

>>>>>>> 4989309 (:))
// -------------------------------------------------------------------------------------

    @FXML
    void save() {
<<<<<<< HEAD
        // photoList.addPhoto(track.getUplaodImage().getImage());
        System.out.println("Preview:" + track.getUplaodImage().getImage());
        user.updateUserAlbum();
        Stage stage = (Stage) SaveButton.getScene().getWindow();
        stage.close();
    }

    imageAttributes imgAttr;
// -------------------------------------------------------------------------------------

    public void initialize() {

    userPage user = userPage.getInstance();
    photoList = link.getImageList(user.getAlbum());
    imagePreviewer.setImage(track.getUplaodImage().getImage());
    System.out.println("Preview INTIZ: " + track.getUplaodImage().getImage());

    if(track.getSelectedTagList() != null){
        for (int i = 0; i < track.tagSelectedListSize(); i++){
            if(track.getSelectedTagList().get(i) != null){
                tagsAndValue adding = new tagsAndValue(track.getSelectedTagList().get(i), null);
                selectedTagsList.add(adding);
            }
        }
    }
    
    // if (track.stockImageBoolean == true){
    //     imagePreviewer.setImage(track.getStockImage());
    //     track.turnOffStockImage();
    // }
    
    TagColum.setCellValueFactory(new PropertyValueFactory<tagsAndValue, String>("tags"));
    ValueColumn.setCellValueFactory(new PropertyValueFactory<tagsAndValue, String>("value"));
    Table.setItems(selectedTagsList);
    Table.refresh();
=======
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

>>>>>>> 4989309 (:))
    }

// -------------------------------------------------------------------------------------


    @FXML void addCaption(ActionEvent event) {
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Captions");
        td.setContentText("Please type enter a Caption: ");
        Optional<String> result = td.showAndWait();
        if (result.isPresent()){
            CurrentCaptionLabel.setText(result.get());
<<<<<<< HEAD
            imgAttr.setCaption(result.get());
=======
>>>>>>> 4989309 (:))
        }

        // still need to implement adding the caption to the actual photo, it should do a method call to imageTracker or something so it stores the caption with the image so if its abanoned it doesnt save so eveything should go to save
        
    }

// -------------------------------------------------------------------------------------
<<<<<<< HEAD
    private Stage secondPopUp;
    @FXML void addTag(ActionEvent event) {
        try {
            if (secondPopUp != null && secondPopUp.isShowing()) {
                secondPopUp.toFront();
=======
    private Stage popupStage;
    @FXML void addTag(ActionEvent event) {
        try {
            if (popupStage != null && popupStage.isShowing()) {
                popupStage.toFront();
>>>>>>> 4989309 (:))
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/tagViewPopup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

<<<<<<< HEAD
            secondPopUp = new Stage();
            secondPopUp.setTitle("Preview Image Upload");
            secondPopUp.initModality(Modality.APPLICATION_MODAL); 
            secondPopUp.setScene(scene);
            secondPopUp.setResizable(false);

            secondPopUp.setOnHidden(e -> {
             // need to implement
            });
            secondPopUp.showAndWait();;
=======
            popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL); 
            popupStage.setScene(scene);
            popupStage.setResizable(false);

            popupStage.setOnHidden(e -> {
             // need to implement
            });
            popupStage.showAndWait();;
>>>>>>> 4989309 (:))
                    
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

<<<<<<< HEAD

    @FXML void addValue(ActionEvent event) {

    }

// -------------------------------------------------------------------------------------

=======
>>>>>>> 4989309 (:))
}
