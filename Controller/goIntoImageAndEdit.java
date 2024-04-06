package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class goIntoImageAndEdit {
    
    private listOfPhotos photoList;
    linkerClass link = linkerClass.getInstance();
    private imageTracker track = imageTracker.getInstance();

    @FXML private Label CaptionName;
    @FXML private Label DateLabel;

    @FXML private Button ChangeTag;
    @FXML private Button CloseButton;
    @FXML private Button DeleteTag;
    @FXML private Button SaveButton;
    @FXML private Button changeValueButton;
    @FXML private Button AddTagButton;

    @FXML private ImageView ImageViewer;

    @FXML private TableColumn<imageAttributes, String> TagColumn;
    @FXML private TableColumn<imageAttributes, String> ValueColum;
    @FXML private TableView<String> infoTable;

    @FXML void chageTag(ActionEvent event) {

    }

// -------------------------------------------------------------------------------------

    @FXML void changeValue(ActionEvent event) {

    }

// -------------------------------------------------------------------------------------

    @FXML void close(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

// -------------------------------------------------------------------------------------

    @FXML void delTag(ActionEvent event) {

    }

// -------------------------------------------------------------------------------------

    @FXML void save(ActionEvent event) {

    }

// -------------------------------------------------------------------------------------

    public void initialize() {

        ImageViewer.setImage(track.getSelectedImage());

      //  TagColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getName()));
      //  ValueColum.setCellValueFactory(f -> new SimpleIntegerProperty());
      //  infoTable.getColumns().forEach(e -> e.setReorderable(false));
        
       // infoTable.setItems();
    }

// -------------------------------------------------------------------------------------

    @FXML void addTag(ActionEvent event) {

    }


}
