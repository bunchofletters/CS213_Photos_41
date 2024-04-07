package Controller;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class tagViewPopup {

    linkerClass link = linkerClass.getInstance();
    private imageTracker track = imageTracker.getInstance();

    @FXML private Button CloseButton;
    @FXML private Button SelecteButton;
    @FXML private Button addTagButton;
    @FXML private Button DeleteTagButton;
    

    @FXML private ListView<String> listOfTags;

// -------------------------------------------------------------------------------------

    public void initialize(){
        if (track.tagListSize() >= 0){
            for (int i = 0; i < track.tagListSize(); i++){
                listOfTags.getItems().add(track.allTags.get(i));
           }
        }
    }

// -------------------------------------------------------------------------------------

    @FXML void addingTag(ActionEvent event) {
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Tag");
        td.setContentText("Please type enter a Tag: ");
        Optional<String> result = td.showAndWait();
        if (result.isPresent()){
            boolean tagExists = false;
            for (int i = 0; i < track.tagListSize(); i++){
                if (result.get().equals(track.allTags.get(i))){
                    tagExists = true;
                    break;
                }
            }
            if (!tagExists) {
                track.addTagToList(result.get());
                listOfTags.getItems().add(result.get());
            }
        }
    }
// -------------------------------------------------------------------------------------

    @FXML void close(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

// -------------------------------------------------------------------------------------

@FXML void select(ActionEvent event) {
    int TagValue = listOfTags.getSelectionModel().getSelectedIndex();
    if(TagValue >= 0 && TagValue < listOfTags.getItems().size()){
        String selectedTag = listOfTags.getItems().get(TagValue);
        if(selectedTag != null){
            track.setMoveTag(selectedTag);
            track.addSelectedTagToList(selectedTag);
        }
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
