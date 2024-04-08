package Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class movingPhotos {

    private static photoAlbumList album;
    private static userPage instance;
    
    private userPage users = userPage.getInstance();
    private imageTracker track = imageTracker.getInstance();
    private login Login = login.getInstance();

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

        int item = table.getSelectionModel().getSelectedIndex();
        if(item >= 0){
        album = link.getPhotoAlbum(user).getAlbumList().get(item);
            if (track.getSelectedImage() != null){
                if (album.getPhotoNum() == 0){
                    album = link.getPhotoAlbum(user).getAlbumList().get(item);
                    link.addToImage(link.getPhotoAlbum(user).getAlbumList().get(item), track.getSelectedImage());
                    link.getPhotoAlbum(user).getAlbumList().get(item).setPhotoNum(link.getImageList(users.getAlbum()).getPhotos().size());
                    System.out.println(link.getImageList(users.getAlbum()).getPhotos().size());
             
                    System.out.println("moving track: " + track.getSelectedImage());
                    System.out.println("moving album: " + users.getAlbum());
                
                    track.move = true;
                    link.getDataPhotoAlbum().get(users.getUser()).getAlbumList().get(item).setPhotoNum(link.getImageList(album).getPhotos().size());
                    users.getAlbum().setPhotoNum(link.getImageList(users.getAlbum()).getPhotos().size());
                }
                else{
                    album = link.getPhotoAlbum(user).getAlbumList().get(item);
                    boolean run = true;

                        for (int i = 0; i < link.getImageList(album).getPhotos().size(); i++){

                            System.out.println(link.getPhotoAlbum(user).getAlbumList().get(item).getPhotoNum());
                            System.out.println("moving ouside track: " + track.getSelectedImage());
                            // System.out.println("moving outside index: " + link.getImageList(album).getPhotos().get(i).getImage());
                                
                                System.out.println("moving index: " + link.getImageList(album).getPhotos().get(i).getImage().getUrl());
                                System.out.println("moving track: " + track.getSelectedImage().getImage().getUrl());
                                if (link.getImageList(album).getPhotos().get(i).getImage().equals(track.getSelectedImage().getImage())){

                                    System.out.println("moving index: " + link.getImageList(album).getPhotos().get(i).getImage());
                                    System.out.println("moving track: " + track.getSelectedImage().getImage());

                                    track.move = false;
                                    track.setSelectedImage(null);
                                    run = false;
                                    break;
                                    // Stage stage = (Stage) MoveIntoButton.getScene().getWindow();
                                    // stage.close();
                                }   
                        }
                    if(run){
                    link.addToImage(link.getPhotoAlbum(user).getAlbumList().get(item), track.getSelectedImage()); //one add
                    link.getDataPhotoAlbum().get(users.getUser()).getAlbumList().get(item).setPhotoNum(link.getImageList(album).getPhotos().size());
                    users.getAlbum().setPhotoNum(link.getImageList(users.getAlbum()).getPhotos().size());
                    track.move = true;
                    }
                }
                Stage stage = (Stage) MoveIntoButton.getScene().getWindow();
                stage.close();
            }
            int tmp = users.getIndex();
            users.setItems(item);
            users.updateUserAlbum();
            users.setItems(tmp);

        } else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("An Warning has Occured");
            alert.setContentText("PLEASE SELECT AN ALBUM");
            alert.showAndWait();
        }

// -------------------------------------------------------------------------------------

    public void initialize() {
       
    
        
        //Creates a unique photoAlbumList per user
        // if(link.getPhotoAlbum(user) == null){
        //     link.setUserAlbum(user);
        // }

        AlbumName.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getName()));
        NumberOfPhotos.setCellValueFactory(f -> new SimpleIntegerProperty(f.getValue().getPhotoNum()));
        EarliestPhotoDate.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getLowestDate()));
        LatestPhotoDate.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getHighestDate()));
        table.getColumns().forEach(e -> e.setReorderable(false));
        refreshTable();
        // table.setItems(link.getPhotoAlbum(user).getAlbumList());
        
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
