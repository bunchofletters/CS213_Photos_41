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

    @FXML private TableColumn<photoAlbumList, String> AlbumName;

    @FXML private TableColumn<photoAlbumList, Number> EarliestPhotoDate;

    @FXML private TableColumn<photoAlbumList, Number> LatestPhotoDate;

    @FXML private TableColumn<photoAlbumList, Number> NumberOfPhotos;

    @FXML private TableView<photoAlbumList> table;

    ObservableList<photoAlbumList> photoAlbum = FXCollections.observableArrayList(
        new photoAlbumList("mon", 0, 0, 0),
        new photoAlbumList("smon", 0, 0, 0)
    );


    @FXML
    void createAlbum(ActionEvent event) {
       // photoAlbumList newAlbum = new photoAlbumList(AlbumNameInput.getText(),0, 0, 0);
       // table.getItems().add(newAlbum);
    }

    @FXML
    void delButton(ActionEvent event) {

     }

    @FXML
    void logout(ActionEvent event) {
        Photo x = new Photo();
        x.changeScene("login.fxml");
    }

    @FXML
    void openAlbum(ActionEvent event) {

    }

    @FXML
    void renameAlbum(ActionEvent event) {
        
        }

    public void initialize() {
        AlbumName.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getName()));
        NumberOfPhotos.setCellValueFactory(f -> new SimpleIntegerProperty(f.getValue().getPhotoNum()));
        EarliestPhotoDate.setCellValueFactory(f -> new SimpleIntegerProperty(f.getValue().getLowestDate()));
        LatestPhotoDate.setCellValueFactory(f -> new SimpleIntegerProperty(f.getValue().getHighestDate()));
    
        table.setItems(photoAlbum);
    }

}