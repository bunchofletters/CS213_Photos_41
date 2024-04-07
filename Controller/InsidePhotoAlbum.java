package Controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import app.Photo;

public class InsidePhotoAlbum{

    //Singleton Photos so it doesnt create new object of scene
    private Photo photo = Photo.getInstance();
    linkerClass link = linkerClass.getInstance();
    private userPage user = userPage.getInstance();
    private imageTracker track = imageTracker.getInstance();
    private ObservableList<imageAttributes> images;
    private int imageAttributeIndex;

    @FXML private Button ReturnButton;
    @FXML private Button logoutButton;
    @FXML private Button SearchInput;
    @FXML private Button CopyPhotosButton;
    @FXML private Button UploadButton;
    @FXML private Button RemoveButton;
    @FXML private Button EditButton;
    @FXML private Button MoveButton;
    @FXML private Button PreviewButton;
    @FXML private Button PasteButton;
    @FXML private Button UploadFromStockButtom;

    //Pane
    @FXML private TilePane tilePane;
    @FXML private ScrollPane scrollPane;

    //Searches
    @FXML private DatePicker DatePickerBar;
    @FXML private TextField FilterInput;

    //label
    @FXML private Label AlbumNameItsIn;
    @FXML private Label SelectedImage;
    @FXML private Label StatusLabel;
    @FXML private Label StatusUrlLabel;

    static int x = 0;

// -------------------------------------------------------------------------------------

    public void initialize() {
    imageAttributeIndex = 0;
        // SHOWS WHAT ALBUM YOURE IN, SHOWS THE NAME OF THE ALBUM IN THE MIDDLE
    AlbumNameItsIn.setText(user.getAlbum().getName());

    Image image = new Image("data/Frog.jpeg");
    link.addToImage(user.getAlbum(), image);
    
    images = link.getImageList(user.getAlbum()).getPhotos();
    user.getAlbum().setPhotoNum(images.size());
    for (imageAttributes img : images){
        if (img != null) {
            tilePane.getChildren().add(setImages(img.getImage()));
        }
        imageAttributeIndex++;
        scrollPane.setContent(tilePane);
        scrollPane.setFitToWidth(true); // Fit content to width
    }
}
// -------------------------------------------------------------------------------------

    public VBox setImages(Image image){
    // Load the image (replace with your image file path)
    // Image image = new Image("data/Beach.jpeg");
    ImageView imageView = new ImageView(image);
    // userpage.getlListOfPhotos().getPhotoList().add(image);
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

    // When you click on a photo image
    private VBox selectedVBox = null;
    private Image selectImage;

    @FXML void goIntoPhotoDetails(MouseEvent event) {
        imageAttributeIndex =0;
        if (event.getButton() == MouseButton.PRIMARY) {

            // Check if the source of the event is a VBox
            if (event.getSource() instanceof VBox) {
                VBox vbox = (VBox) event.getSource();
                
                if (selectedVBox != null) {
                    selectedVBox.setStyle(""); 
                }
                selectedVBox = vbox;
                selectedVBox.setStyle("-fx-border-color: blue; -fx-background-color: lightblue;"); 
                ObservableList<Node> children = vbox.getChildren();

                for (Node child : children) {
                    if (child instanceof ImageView){
                    ImageView imageView = (ImageView) child;
                    selectImage = imageView.getImage();
                    SelectedImage.setText(" " + "images.get(imageAttributeIndex).getName()");
                    break;
                    }
                    imageAttributeIndex++;
                }
            }
        }
    }


// -------------------------------------------------------------------------------------

    /**
     * when logout button is clicked changes scene back to login page
     */
    @FXML void logout() {
        photo.changeScene("/view/login.fxml");
    }

// -------------------------------------------------------------------------------------

    /**
     * goes back to the userPage when its clieked
     */
    @FXML void returnButton() {
        photo.changeScene("/view/userPage.fxml");
    }

// -------------------------------------------------------------------------------------

    // using the date picker it filters the image browers
    @FXML void pickDate(ActionEvent event) {

    }

// -------------------------------------------------------------------------------------
    
    // useing the box to filter
    @FXML void filterBox(ActionEvent event) {

    }

// -------------------------------------------------------------------------------------

    @FXML void searchbox(ActionEvent event) {
        
        ObservableList<imageAttributes> images = link.getImageList(user.getAlbum()).getPhotos();
        for (imageAttributes img : images){ //img.get
            //search result 
           
        }
    }

// -------------------------------------------------------------------------------------

    @FXML void paste() {
        // link.addToImage(user.getAlbum(), track.getSaveCopyImage());
        // tilePane.getChildren().add(setImages(track.getSaveCopyImage()));
        Image image = track.getSaveCopyImage();

        if (!link.isImageInAlbum(user.getAlbum(), image)) {
            link.addToImage(user.getAlbum(), image);
            tilePane.getChildren().add(setImages(image));
        }
    }
    
// -------------------------------------------------------------------------------------

    // copies photos
    @FXML void copyPhotos(ActionEvent event) {
        if (selectedVBox != null && selectImage != null) {
            track.setSaveCopyImage(selectImage);
            selectedVBox.setStyle("-fx-border-color: red; -fx-background-color: LIGHTPINK;");
            StatusLabel.setText("STATUS COPYING:");
            StatusUrlLabel.setText(selectImage.getUrl());
        }
    }

// -------------------------------------------------------------------------------------
    
    // uplaod button goest to personal computer to uplaod
    private Stage popupStage;

    @FXML void upload() { 
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpeg", "*.gif"));
        try {
            if (popupStage != null && popupStage.isShowing()) {
                popupStage.toFront();
                return;
            }
                File file = chooser.showOpenDialog(new Stage());
                if (file != null){
                String path = file.getAbsolutePath();
                InputStream stream = new FileInputStream(path);
                Image image = new Image(stream);
                ImageWithPath imageWithPath = new ImageWithPath(image, path);

                // set setSelectedImage() to image in imageTracker.java
                track.setSelectedImage(image);

                // testing to see what path it prints
                System.out.println(imageWithPath.getPath());

                // Loader
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/previewImageUpload.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Popup Stage
                popupStage = new Stage();
                popupStage.initModality(Modality.APPLICATION_MODAL); 
                popupStage.setScene(scene);
                popupStage.setResizable(false);

                // When the Popup Window CLoses by Any Means (X or EXIT BUTTON)
                popupStage.setOnHidden(e -> {
                    if (track.check == true){
                        // uses to get list size and updates to the newly added
                        user.getAlbum().setPhotoNum(link.getImageList(user.getAlbum()).getPhotos().size());
                        tilePane.getChildren().add(setImages(track.getSelectedImage()));
                        track.check = false;
                    }
                    
                       
                });
                // waits for window before it runs (popupStage.setOnHidden) ABOVE
                popupStage.showAndWait();;
                    
            }

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }  

// -------------------------------------------------------------------------------------

    // remoing photos with this button
    @FXML void remove() {
        if (selectedVBox != null){
            Parent parent = selectedVBox.getParent();
            if (parent instanceof Pane) {
                Pane pane = (Pane) parent;
                pane.getChildren().remove(selectedVBox);
                selectedVBox = null;
            }
        
        if (selectImage != null){
            link.removeImage(user.getAlbum(),selectImage);
            SelectedImage.setText(null);
            selectImage = null;
            }
        }
    }

// -------------------------------------------------------------------------------------

    // cahnge caption
    @FXML void edit(ActionEvent event) {
        
    }

// -------------------------------------------------------------------------------------

    @FXML void move() {
        try {
            if (popupStage != null && popupStage.isShowing()) {
                popupStage.toFront();
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/movingPhotos.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL); 
            popupStage.setScene(scene);
            popupStage.setResizable(false);

            popupStage.setOnHidden(e -> {
                if(track.move = true){
                    remove();
                    track.move = false;
                }
            });
            popupStage.showAndWait();;
                    
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

// -------------------------------------------------------------------------------------

    @FXML
    void SlideShow(ActionEvent event) {
        photo.changeScene("/view/PhotoSlideshow.fxml");
    }


}
