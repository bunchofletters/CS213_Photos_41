import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class movingPhotos {

    private static photoAlbumList album;
    private static userPage instance;
    
    private login Login = login.getInstance();
    private imageTracker track = imageTracker.getInstance();

    public static userPage getInstance() {
    if (instance == null) {
        instance = new userPage();
    }
    return instance;
    }
    linkerClass link = linkerClass.getInstance();
    String user = Login.getUser();

    @FXML private Button CloseButton;
    @FXML private Button MoveIntoButton;

    @FXML private TableColumn<photoAlbumList, String> AlbumName;
    @FXML private TableColumn<photoAlbumList, String> EarliestPhotoDate;
    @FXML private TableColumn<photoAlbumList, String> LatestPhotoDate;
    @FXML private TableColumn<photoAlbumList, Number> NumberOfPhotos;
    @FXML private TableView<photoAlbumList> table;

// -------------------------------------------------------------------------------------

    @FXML void close(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

// -------------------------------------------------------------------------------------

    @FXML void moveInto(ActionEvent event) {
        // track.move = true;
        // int item = table.getSelectionModel().getSelectedIndex();
        // album = link.getPhotoAlbum(user).getAlbumList().get(item);
        // link.addToImage(album, track.getSelectedImage());
        // Stage stage = (Stage) MoveIntoButton.getScene().getWindow();
        // stage.close();

        track.move = true;
        int item = table.getSelectionModel().getSelectedIndex();
        album = link.getPhotoAlbum(user).getAlbumList().get(item);
        

        // Only add the image if it's not already in the album
        if (!link.isImageInAlbum(album, track.getSelectedImage())) {
            link.addToImage(album, track.getSelectedImage());
        }

        Stage stage = (Stage) MoveIntoButton.getScene().getWindow();
        stage.close();
    }

// -------------------------------------------------------------------------------------

    public void initialize() {
       
        //Creates a unique photoAlbumList per user
        if(link.getPhotoAlbum(user) == null){
            link.setUserAlbum(user);
        }

        AlbumName.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getName()));
        NumberOfPhotos.setCellValueFactory(f -> new SimpleIntegerProperty(f.getValue().getPhotoNum()));
        EarliestPhotoDate.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getLowestDate()));
        LatestPhotoDate.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getHighestDate()));
        table.getColumns().forEach(e -> e.setReorderable(false));
        table.setItems(link.getPhotoAlbum(user).getAlbumList());
    }

// -------------------------------------------------------------------------------------

    public void refreshTable(){
        table.getItems().clear();
        table.setItems(link.getPhotoAlbum(user).getAlbumList());
    }

// -------------------------------------------------------------------------------------

    /**
     * This returns the name [String] of the current album
     * @return the name of the album current being access
     */
    
    public photoAlbumList getAlbum(){
        return album;
    }

}
