package Controller;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class tagViewPopup {

    @FXML private Button CloseButton;
    @FXML private Button SelecteButton;
    @FXML private Button addTagButton;
    @FXML private Button DeleteTagButton;

    @FXML private ListView<String> listOfTags;

// -------------------------------------------------------------------------------------

    @FXML void addingTag(ActionEvent event) {
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Tag");
        td.setContentText("Please type enter a Tag: ");
        Optional<String> result = td.showAndWait();
        if (result.isPresent()){
            // need to implement adding it to the tag arraylist use result.get()
        }
    }

// -------------------------------------------------------------------------------------

    @FXML void close(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

// -------------------------------------------------------------------------------------

    @FXML void select(ActionEvent event) {

    }

// -------------------------------------------------------------------------------------

    @FXML
    void deleteTag(ActionEvent event) {
    }

}
