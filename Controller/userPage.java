package Controller;
import java.util.Optional;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
/**
 * @author Danny dl1093
 */
public class userPage {

    // Buttons
    @FXML private Button RenameAlbumButton;
    @FXML private Button logoutButton;
    @FXML private Button CreateAlbumButton;
    @FXML private Button DelAlbumButton;
    @FXML private Button OpenAlbumButton;
    // @FXML private Button PasteButton;

    // TextFields
    @FXML private TextField AlbumNameInput;

    // Table
    @FXML private TableColumn<photoAlbumList, String> AlbumName;
    @FXML private TableColumn<photoAlbumList, String> EarliestPhotoDate;
    @FXML private TableColumn<photoAlbumList, String> LatestPhotoDate;
    @FXML private TableColumn<photoAlbumList, Number> NumberOfPhotos;
    @FXML private TableView<photoAlbumList> table;

    
    // ------------------------------------------------------------------------------------

    private static photoAlbumList album;
    private static userPage instance;
    private int item;

    private Photo x = Photo.getInstance();
    private login Login = login.getInstance();

    public static userPage getInstance() {
    if (instance == null) {
        instance = new userPage();
    }
    return instance;
    }
    linkerClass link = linkerClass.getInstance();
    String user = Login.getUser();

// -------------------------------------------------------------------------------------

    /**
     * Uses the "Create New Album" Button to input the names into tableview list using the TextField "AlbumNameInput"
     * @param event
     */
    @FXML void createAlbum() {
    String albumName = AlbumNameInput.getText().trim();
        if (!albumName.isEmpty()) {
            if (!containsAlbumName(albumName)) {
                photoAlbumList newAlbum = new photoAlbumList(albumName, 0,"N/A", "N/A");
                link.addToAlbum(user, newAlbum);
                link.setAlbumImages(link.getPhotoAlbum(user).getAlbumList().get(link.getPhotoAlbum(user).getAlbumList().size()-1));
                AlbumNameInput.clear();
            }
        }
    }

// -------------------------------------------------------------------------------------
    
    private boolean containsAlbumName(String name) {
        for (photoAlbumList album : table.getItems()) {
            if (album.getName().equalsIgnoreCase(name)) {
            return true;
            }
        }
        return false;
    }

// -------------------------------------------------------------------------------------

    @FXML void delButton(ActionEvent event) {
        int item = table.getSelectionModel().getSelectedIndex();
            if(item != -1){
                link.removePhotoList(link.getPhotoAlbum(user).getAlbumList().get(item));
                table.getItems().remove(item);
            }
    }

// -------------------------------------------------------------------------------------

    @FXML void logout(ActionEvent event) {
        x.changeScene("login.fxml");
    }

// -------------------------------------------------------------------------------------
    @FXML void openAlbum(ActionEvent event) {
        item = table.getSelectionModel().getSelectedIndex();
        album = link.getPhotoAlbum(user).getAlbumList().get(item);
            if(item != -1){
                x.changeScene("insidePhotoAlbum.fxml");
            }
    }

// -------------------------------------------------------------------------------------

    @FXML void renameAlbum(ActionEvent event) {
    int item = table.getSelectionModel().getSelectedIndex();
    TextInputDialog td = new TextInputDialog();
    td.setTitle("Rename");
    td.setContentText("Please type a name: ");
        if (item >= 0) {
            Optional<String> result = td.showAndWait();
            if (result.isPresent()){
                photoAlbumList selectedAlbum = table.getItems().get(item);
                selectedAlbum.setName(result.get());
                AlbumNameInput.clear();
                table.refresh();
                }
        }
        
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

        table.setRowFactory(tv -> {
            TableRow<photoAlbumList> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                int item = table.getSelectionModel().getSelectedIndex();
                album = link.getPhotoAlbum(user).getAlbumList().get(item);
                    if(item != -1){
                    item = table.getSelectionModel().getSelectedIndex();
                    album = link.getPhotoAlbum(user).getAlbumList().get(item);
                    x.changeScene("insidePhotoAlbum.fxml");
                    }
                }

            });
            return row;
        });
    }

    public void updateUserAlbum(){
        photoAlbumList album = link.getPhotoAlbum(user).getAlbumList().get(item);
        listOfPhotos photoList = link.getImageList(album);
        if(photoList.getPhotos().size() > 0){
            album.setLowestDate(photoList.getPhotos().get(0).getUploadDate());
            album.setHighestDate(photoList.getPhotos().get(photoList.getPhotos().size()-1).getUploadDate());
        }
    }

    public int getIndex(){
        return item;
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