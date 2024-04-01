import java.util.HashMap;
import java.util.Optional;

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
import javafx.scene.control.TextInputDialog;
/**
 * @author Danny dl1093
 */
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

    private static HashMap<String, photoAlbumList> photoAlbum = new HashMap<>();

    private static String nameOfAlbum;

    private login Login = login.getInstance();

    String user = Login.getUser();

    private static userPage instance;

    public static userPage getInstance() {
        if (instance == null) {
            instance = new userPage();
        }
        return instance;
    }


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
                photoAlbum.get(user).getAlbumList().add(newAlbum);
                
                // Making a listofphoto for each albumn
                ObservableList<listOfPhotos> tmp = FXCollections.observableArrayList();
                listOfPhotos list = new listOfPhotos(tmp);
                photoAlbum.get(user).addPhotoList(albumName, list);
                // End of making a listofphoto for each albumn

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
        nameOfAlbum = photoAlbum.get(Login.getUser()).getAlbumList().get(item).getName();
        if(item != -1){
            x.changeScene("PhotoAlbumController.fxml");
        }
    }

    @FXML
    void renameAlbum(ActionEvent event) {
    
    int item = table.getSelectionModel().getSelectedIndex();
    TextInputDialog td = new TextInputDialog();
    td.setTitle("Rename");
    //td.setHeaderText("dw");
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

    public void initialize() {
        //Creates a unique photoAlbumList per user
        if(photoAlbum.get(user) == null){
            ObservableList<photoAlbumList> y = FXCollections.observableArrayList();
            photoAlbumList x = new photoAlbumList(y);
            photoAlbum.put(user, x);
        }

        AlbumName.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getName()));
        NumberOfPhotos.setCellValueFactory(f -> new SimpleIntegerProperty(f.getValue().getPhotoNum()));
        EarliestPhotoDate.setCellValueFactory(f -> new SimpleIntegerProperty(f.getValue().getLowestDate()));
        LatestPhotoDate.setCellValueFactory(f -> new SimpleIntegerProperty(f.getValue().getHighestDate()));
        table.getColumns().forEach(e -> e.setReorderable(false));
    
        table.setItems(photoAlbum.get(user).getAlbumList());
    }
    /**
     * This returns the name [String] of the current album
     * @return the name of the album current being access
     */
    public String getAlbumName(){
        return nameOfAlbum;
    }

}