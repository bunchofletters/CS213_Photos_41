import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class userPage {

    private Photo x = Photo.getInstance();

    @FXML private Button RenameAlbumButton;

    @FXML private Button logoutButton;

    @FXML private TextField AlbumNameInput;

    @FXML private Button CreateAlbumButton;

    @FXML private Button DelAlbumButton;

    @FXML private Button OpenAlbumButton;

    @FXML private TableColumn<photoAlbumList, String> AlbumName;

    @FXML private TableColumn<photoAlbumList, Number> EarliestPhotoDate;

    @FXML private TableColumn<photoAlbumList, Number> LatestPhotoDate;

    @FXML private TableColumn<photoAlbumList, Number> NumberOfPhotos;

    @FXML private TableView<photoAlbumList> table;

    ObservableList<photoAlbumList> photoAlbum = FXCollections.observableArrayList();

    /**
     * Uses the "Create New Album" Button to input the names into tableview list using the TextField "AlbumNameInput"
     * @param event
     */
    @FXML
    void createAlbum(ActionEvent event) {
    String albumName = AlbumNameInput.getText().trim();
        if (!albumName.isEmpty()) {
            if (!containsAlbumName(albumName)) {
                photoAlbumList newAlbum = new photoAlbumList(albumName, 0, 0, 0);
                table.getItems().add(newAlbum);
                AlbumNameInput.clear();
            }
        }
    }
    
    private boolean containsAlbumName(String name) {
        for (photoAlbumList album : table.getItems()) {
            if (album.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
}


    @FXML
    void delButton(ActionEvent event) {
        int item = table.getSelectionModel().getSelectedIndex();
        if(item != -1){
        table.getItems().remove(item);
        }
   }

    @FXML
    void logout(ActionEvent event) {
        x.changeScene("login.fxml");
    }

    @FXML
    void openAlbum(ActionEvent event) {
        int item = table.getSelectionModel().getSelectedIndex();
        if(item != -1){
            x.changeScene("PhotoAlbumController.fxml");
        }
    }

    @FXML
    void renameAlbum(ActionEvent event) {
    
    int item = table.getSelectionModel().getSelectedIndex();
    String newAlbumName = AlbumNameInput.getText().trim();
    if (item >= 0 && !newAlbumName.isEmpty()) {    
        photoAlbumList selectedAlbum = table.getItems().get(item);
        selectedAlbum.setName(newAlbumName);
            AlbumNameInput.clear();
            table.refresh();
        }
        
    }

    public void initialize() {
        AlbumName.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getName()));
        NumberOfPhotos.setCellValueFactory(f -> new SimpleIntegerProperty(f.getValue().getPhotoNum()));
        EarliestPhotoDate.setCellValueFactory(f -> new SimpleIntegerProperty(f.getValue().getLowestDate()));
        LatestPhotoDate.setCellValueFactory(f -> new SimpleIntegerProperty(f.getValue().getHighestDate()));
        table.getColumns().forEach(e -> e.setReorderable(false));
    
        table.setItems(photoAlbum);
    }

}