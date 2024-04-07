package Controller;
import java.util.Optional;

import app.Photo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class searchMode {

    private Photo x = Photo.getInstance();
    linkerClass link = linkerClass.getInstance();
    private userPage user = userPage.getInstance();
    private ObservableList<imageAttributes> images;
    private int imageAttributeIndex;

    @FXML private TextField FilterInput;

    @FXML private Button ReturnButton;
    @FXML private Button SearchInput;
    @FXML private Button UploadButton;
    @FXML private Button logoutButton;

    @FXML private ScrollPane scrollPane;

    @FXML private TilePane tilePane;


// -------------------------------------------------------------------------------------

    public void initialize() {
        ObservableList<photoAlbumList> userAlbum= link.getDataPhotoAlbum().get(user.getUser()).getAlbumList();
        for (int i = 0; i < userAlbum.size(); i++){
            ObservableList<imageAttributes> albumnImage = link.getImageList(link.getPhotoAlbum(user.getUser()).getAlbumList().get(i)).getPhotos();
            for(int j = 0; j < link.getImageList(link.getDataPhotoAlbum().get(user.getUser()).getAlbumList().get(i)).getPhotos().size(); j++){
                if (link.getImageList(link.getDataPhotoAlbum().get(user.getUser()).getAlbumList().get(i)) != null){
                    tilePane.getChildren().add(setImages(albumnImage,j));
                
                }

            }
            scrollPane.setContent(tilePane);
            scrollPane.setFitToWidth(true); // Fit content to width
        }        
        
    }
// -------------------------------------------------------------------------------------


    @FXML void filterBox(ActionEvent event) {

    }

// -------------------------------------------------------------------------------------

    private VBox selectedVBox = null;

    @FXML void goIntoPhotoDetails(MouseEvent event) {
        imageAttributeIndex = 0;
        if (event.getButton() == MouseButton.PRIMARY) {

            // Check if the source of the event is a VBox
            if (event.getSource() instanceof VBox) {
                VBox vbox = (VBox) event.getSource();
                
                if (selectedVBox != null) {
                    selectedVBox.setStyle(""); 
                }
                selectedVBox = vbox;
                selectedVBox.setStyle("-fx-border-color: blue; -fx-background-color: lightblue;"); 
            }
        }
    }

// -------------------------------------------------------------------------------------

    public VBox setImages(ObservableList<imageAttributes> observableList, int index){
        
        ImageView imageView = new ImageView(observableList.get(index).getImage());
        imageView.setFitWidth(150); // Set image width
        imageView.setFitHeight(100); // Set image height
        Label photoName = new Label("images.get(imageAttributeIndex).getName()");
        photoName.setAlignment(Pos.CENTER);
        
        // creates a VBOX so the Image, Name is linked together
        VBox box = new VBox();
        box.setPadding(new Insets(20, 30, 30, 20));
        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(imageView,photoName);
        box.setOnMouseClicked(this::goIntoPhotoDetails); 
        return box;
    
        }

// -------------------------------------------------------------------------------------

    @FXML void logout(ActionEvent event) {
        x.changeScene("/view/login.fxml");
    }

// -------------------------------------------------------------------------------------

    @FXML void returnButton(ActionEvent event) {
        x.changeScene("/view/userPage.fxml");
    }

// -------------------------------------------------------------------------------------

    @FXML void searchbox(ActionEvent event) {

    }

// -------------------------------------------------------------------------------------

    // this is not uplaod this is : "Create Album From Search Results"
    @FXML void upload(ActionEvent event) {
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Captions");
        td.setContentText("Please type enter a Caption: ");
        Optional<String> result = td.showAndWait();
            if (result.isPresent()){
            String name = result.get().trim();
            if (!name.isEmpty()) {
            if (!containsAlbumName(name)) {
                photoAlbumList newAlbum = new photoAlbumList(name, 0,"N/A", "N/A");
            }
            }

        }
    }

// -------------------------------------------------------------------------------------

    private boolean containsAlbumName(String name){
        // need im
        return false;
    }
}





