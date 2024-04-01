import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PhotoAlbumController{

    private Photo photo = Photo.getInstance();

    private userPage userpage = userPage.getInstance();
    
    //Buttons
    @FXML private Button ReturnButton;
    @FXML private Button logoutButton;
    @FXML private Button SearchInput;
    @FXML private Button CopyPhotosButton;
    @FXML private Button UploadButton;
    @FXML private Button RemoveButton;
    @FXML private Button EditTagButton;
    @FXML private Button EditCaptionButton;
    @FXML private Button MoveButton;



    //Pane
    @FXML private GridPane gridPane;
    @FXML private ScrollPane scrollPane;

    //Searches
    @FXML private DatePicker DatePickerBar;
    @FXML private TextField FilterInput;

    //label
    @FXML private Label AlbumNameItsIn;

    public void initialize() {

        gridPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

            int col = 0;
            int row = 1;

         for (int i = 0; i < 20; i++){

             // Load the image (replace with your image file path)
            Image image = new Image("data/Beach.jpeg");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(120); // Set image width
            imageView.setFitHeight(100); // Set image height

            Label photoName = new Label("TEMP PHOTO NAME");
            photoName.setAlignment(Pos.CENTER);

            VBox box = new VBox();
            box.setPadding(new Insets(20, 20, 20, 20));
            box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(imageView, photoName);
             // Add the ImageView to the GridPane

            if (col == 5){
                col = 0;
                row++;
            }
            gridPane.add(box, col++,row );
            
         }
        scrollPane.setContent(gridPane);
        scrollPane.setFitToWidth(true); // Fit content to width
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
    // botton of the screen showing what album its in
    @FXML void albumNametext(MouseEvent event) {
        
    }

    // copies photos
    @FXML void copyPhotos(ActionEvent event) {

    }

    // uplaod button goest to personal computer to uplaod
    @FXML void upload(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif"));
        File file = chooser.showOpenDialog(new Stage());
    }

    // remoing photos with this button
    @FXML void remove(ActionEvent event) {

    }
   
    // edit tag to wahtever user wants
    @FXML void editTag(ActionEvent event) {


    }

    // cahnge caption
    @FXML
    void editCaption(ActionEvent event) {

    }

    @FXML void move(ActionEvent event) {

    }
}
