import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PhotoAlbumController implements Initializable{

    private Photo x = Photo.getInstance();
    
    //Buttons
    @FXML private Button ReturnButton;
    @FXML private Button logoutButton;
    @FXML private Button SearchInput;
    @FXML private Button CopyPhotosButton;
    @FXML private Button PasteButton;

    //Pane
    @FXML private GridPane gridPane;
    @FXML private ScrollPane scrollPane;

    //Searches
    @FXML private DatePicker DatePickerBar;
    @FXML private TextField FilterInput;

    //label
    @FXML private Label AlbumNameItsIn;

    //list
    @FXML private ListView<String> SelectedItemList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        gridPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

            int col = 0;
            int row = 1;

         for (int i = 0; i < 21; i++){

             // Load the image (replace with your image file path)
            Image image = new Image("data/Beach.jpeg");
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(120); // Set image width
            imageView.setFitHeight(100); // Set image height

            Label photoName = new Label("TEMP PHOTO NAME");
            photoName.setAlignment(Pos.CENTER);

            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(imageView, photoName);
             // Add the ImageView to the GridPane

            if (col == 3){
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
        x.changeScene("login.fxml");
    }

    // return to userPage going backwards
    @FXML
    void returnButton(ActionEvent event) {
        x.changeScene("userPage.fxml");
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

    // boxes with the photos they select
    @FXML void SelectedItem(ActionEvent event) {

    }

    @FXML void copyPhotos(ActionEvent event) {

    }
    @FXML void paste(ActionEvent event) {

    }
    

}
