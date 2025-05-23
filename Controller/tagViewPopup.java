package Controller;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class tagViewPopup {

    linkerClass link = linkerClass.getInstance();
    private imageTracker track = imageTracker.getInstance();
    private userPage users = userPage.getInstance();

    @FXML private Button CloseButton;
    @FXML private Button SelecteButton;
    @FXML private Button addTagButton;
    @FXML private Button DeleteTagButton;
    

    @FXML private ListView<String> listOfTags;

// -------------------------------------------------------------------------------------

    public void initialize(){
        if (link.getPhotoAlbum(users.getUser()).getTag().size() >= 0){
            for (int i = 0; i < link.getPhotoAlbum(users.getUser()).getTag().size(); i++){
                listOfTags.getItems().add(link.getPhotoAlbum(users.getUser()).getTag().get(i));
           }
        }
    }

// -------------------------------------------------------------------------------------

    @FXML void addingTag() {
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Tag");
        td.setContentText("Please type enter a Tag: ");
        Optional<String> result = td.showAndWait();
        if (result.isPresent()){
            if(!result.get().equals("") && !result.get().contains(" ")){
                boolean tagExists = false;
                for (int i = 0; i < link.getPhotoAlbum(users.getUser()).getTag().size(); i++){
                    if (result.get().equals(link.getPhotoAlbum(users.getUser()).getTag().get(i))){
                        tagExists = true;
                        break;
                    }
                }
                if (!tagExists) {
                    link.getPhotoAlbum(users.getUser()).getTag().add(result.get());
                    listOfTags.getItems().add(result.get());
                    listOfTags.refresh();
                }
            }
            else if(result.get().contains(" ")){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Attempting to add Illegal Charater as Tag. Tags can't have spaces");
                alert.setHeaderText("An Error has Occured When Creating a Tag");
                alert.setTitle("Error");
                alert.showAndWait();
            }
            else if(result.get().contains("=")){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Attempting to add Illegal Charater as Tag. Tags can't have = in them");
                alert.setHeaderText("An Error has Occured When Creating a Tag");
                alert.setTitle("Error");
                alert.showAndWait();
            }
        }
    }
// -------------------------------------------------------------------------------------

    @FXML void close() {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        track.setClosed(false);
        stage.close();
    }

// -------------------------------------------------------------------------------------

@FXML void select() {
    int TagValue = listOfTags.getSelectionModel().getSelectedIndex();
    if(TagValue >= 0 && TagValue < listOfTags.getItems().size()){
        String selectedTag = listOfTags.getItems().get(TagValue);
        if(selectedTag != null){
            track.setMoveTag(selectedTag);
            track.addSelectedTagToList(selectedTag);
        }
    }
    else{
        track.setMoveTag(null);
    }
    Stage stage = (Stage) CloseButton.getScene().getWindow();
    stage.close();
}


// -------------------------------------------------------------------------------------

    @FXML
    void deleteTag(ActionEvent event) {
        int TagValue = listOfTags.getSelectionModel().getSelectedIndex();
        if(TagValue != -1){
            listOfTags.getItems().remove(TagValue);
        }
    }

}
