import java.io.File;
import java.util.HashMap;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InsidePhotoAlbum{

    //Singleton Photos so it doesnt create new object of scene
    private Photo photo = Photo.getInstance();
    linkerClass link = linkerClass.getInstance();
    private userPage user = userPage.getInstance();
    //Buttons
    @FXML private Button ReturnButton;
    @FXML private Button logoutButton;
    @FXML private Button SearchInput;
    @FXML private Button CopyPhotosButton;
    @FXML private Button UploadButton;
    @FXML private Button RemoveButton;
    @FXML private Button EditButton;
    @FXML private Button MoveButton;

    //Pane
    //@FXML private GridPane gridPane;
    @FXML private TilePane tilePane;
    @FXML private ScrollPane scrollPane;

    //Searches
    @FXML private DatePicker DatePickerBar;
    @FXML private TextField FilterInput;

    //label
    @FXML private Label AlbumNameItsIn;

    static int x = 0;

    public void initialize() {

        AlbumNameItsIn.setText("hello");
        //link.getAlbumName(link.getPhotoAlbum(user.getAlbum().getName()))

        if(x == 0){
        Image image = new Image("data/Frog.jpeg");
        link.addToImage(user.getAlbum(), image);
        link.addToImage(user.getAlbum(), image);
        link.addToImage(user.getAlbum(), image);
        x++;
        }
        else{
            Image image = new Image("data/Beach.jpeg");
            link.addToImage(user.getAlbum(), image);
            link.addToImage(user.getAlbum(), image);

        }

        for (int i = 0; i < link.getImageList(user.getAlbum()).getPhotos().size() ; i++){
        if (link.getImageList(user.getAlbum()).getPhotos().get(i) != null){
        tilePane.getChildren().add(setImages(link.getImageList(user.getAlbum()).getPhotos().get(i)));
            
        }
        scrollPane.setContent(tilePane);
        scrollPane.setFitToWidth(true); // Fit content to width

    }
    }

    public VBox setImages(Image image){
    // Load the image (replace with your image file path)
    // Image image = new Image("data/Beach.jpeg");
    ImageView imageView = new ImageView(image);
    imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
        System.out.println("aaa");
        event.consume();
    });
    // userpage.getlListOfPhotos().getPhotoList().add(image);
    imageView.setFitWidth(120); // Set image width
    imageView.setFitHeight(100); // Set image height

    Label photoName = new Label("TEMP PHOTO NAME");
    photoName.setAlignment(Pos.CENTER);

    VBox box = new VBox();
    box.setPadding(new Insets(20, 20, 20, 20));
    box.setAlignment(Pos.CENTER);
    box.getChildren().add(imageView);
    box.getChildren().add(photoName);

    return box;

    }


    // When you click on a photo image
    @FXML
    void goIntoPhotoDetails(MouseEvent event) {
        
    }
    // brings you to the login screen
    @FXML
    void logout(ActionEvent event) {
        photo.changeScene("login.fxml");
    }

    // return to userPage going backwards
    @FXML
    void returnButton(ActionEvent event) {
        photo.changeScene("userPage.fxml");
    }

    // using the date picker it filters the image browers
    @FXML void pickDate(ActionEvent event) {

    }
    
    // useing the box to filter
    @FXML void filterBox(ActionEvent event) {

    }

    @FXML void searchbox(ActionEvent event) {

    }

    // copies photos
    @FXML void copyPhotos(ActionEvent event) {

    }

    // uplaod button goest to personal computer to uplaod
    @FXML void upload(ActionEvent event) {
        // FileChooser chooser = new FileChooser();
        // chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        // chooser.getExtensionFilters().addAll(
        //         new FileChooser.ExtensionFilter("Image Files",
        //                 "*.bmp", "*.png", "*.jpg", "*.gif"));
        // File file = chooser.showOpenDialog(new Stage());


    }
    // remoing photos with this button
    @FXML void remove(ActionEvent event) {

    }

    // cahnge caption
    @FXML
    void edit(ActionEvent event) {
        
    }

    @FXML void move(ActionEvent event) {

    }

}
