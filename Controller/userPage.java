package Controller;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import app.Photo;
/**
 * @author Danny dl1093
 */
public class userPage{

    // Buttons
    @FXML private Button RenameAlbumButton;
    @FXML private Button logoutButton;
    @FXML private Button CreateAlbumButton;
    @FXML private Button DelAlbumButton;
    @FXML private Button OpenAlbumButton;
    @FXML private Button SearchModeButton;

    // TextFields
    @FXML private TextField AlbumNameInput;

    // Table
    @FXML private TableColumn<photoAlbumList, String> AlbumName;
    @FXML private TableColumn<photoAlbumList, String> EarliestPhotoDate;
    @FXML private TableColumn<photoAlbumList, String> LatestPhotoDate;
    @FXML private TableColumn<photoAlbumList, Number> NumberOfPhotos;
    @FXML private TableView<photoAlbumList> table;

    
    // ------------------------------------------------------------------------------------
    private imageTracker track = imageTracker.getInstance();
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
    private LocalDate[] dates;

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
        x.changeScene("/view/login.fxml");
    }

// -------------------------------------------------------------------------------------
    @FXML void openAlbum(ActionEvent event) {
        item = table.getSelectionModel().getSelectedIndex();
            if(item >= 0){
                album = link.getPhotoAlbum(user).getAlbumList().get(item);
                x.changeScene("/view/insidePhotoAlbum.fxml");
            } else{
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("An Warning has Occured");
                alert.setContentText("PLEASE SELECT AN ALBUM");
                alert.showAndWait();
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
        tableRefesh();

        table.setRowFactory(tv -> {
            TableRow<photoAlbumList> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                int item = table.getSelectionModel().getSelectedIndex();
                album = link.getPhotoAlbum(user).getAlbumList().get(item);
                    if(item != -1){
                    item = table.getSelectionModel().getSelectedIndex();
                    album = link.getPhotoAlbum(user).getAlbumList().get(item);
                    x.changeScene("/view/insidePhotoAlbum.fxml");
                    }
                }

            });
            return row;
        });
        
        
    


        // album list
        ObservableList<photoAlbumList> userAlbum = link.getPhotoAlbum(user).getAlbumList();

        if (userAlbum != null){
            for (int i = 0; i < userAlbum.size(); i++){
                LocalDate early = LocalDate.MAX;;
                LocalDate late = LocalDate.MIN;
                LocalDate temp = null;
                System.out.println(userAlbum.size());
                    if (userAlbum.get(i).getPhotoNum() == 1){
                        early = link.getImageList(userAlbum.get(i)).getPhotos().get(0).getUploadDateAsDate();
                        link.getPhotoAlbum(user).getAlbumList().get(i).setLowestDate(early.toString());
                        link.getPhotoAlbum(user).getAlbumList().get(i).setHighestDate(early.toString());
                    }else{
                        for (int j = 0; j < link.getImageList(userAlbum.get(i)).getPhotos().size(); j++){
                            temp = link.getImageList(userAlbum.get(i)).getPhotos().get(j).getUploadDateAsDate();
                                if (temp.isAfter(late)){
                                    System.out.println(temp);
                                    System.out.println(late);
                                    late = temp;

                                    System.out.println(late);
                                } else{
                                    if (temp.isBefore(early)){
                                        System.out.println(temp);
                                        System.out.println(early);
                                        early = temp;
                                        System.out.println(early);
                                    }
                                }
                        link.getPhotoAlbum(user).getAlbumList().get(i).setLowestDate(early.toString());
                        link.getPhotoAlbum(user).getAlbumList().get(i).setHighestDate(late.toString());
                                
                        }
                }
            }
        
        }
        
    }

    // -------------------------------------------------------------------------------------

    public void tableRefesh(){
        table.getItems().clear();
        table.setItems(link.getPhotoAlbum(user).getAlbumList());
    }

    public void updateUserAlbum(){
        photoAlbumList album = link.getPhotoAlbum(user).getAlbumList().get(item);
        listOfPhotos photoList = link.getImageList(album);
        if(photoList.getPhotos().size() > 0){
            album.setLowestDate(photoList.getPhotos().get(0).getUploadDate());
            album.setHighestDate(photoList.getPhotos().get(photoList.getPhotos().size()-1).getUploadDate());
        }
    }
    public void setItems(int x){
        item = x;
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

    public String getUser(){
        user = Login.getUser();
        return user;
    }


// -------------------------------------------------------------------------------------

    @FXML void searchMode(ActionEvent event) {
        x.changeScene("/view/searchMode.fxml");
    }
}