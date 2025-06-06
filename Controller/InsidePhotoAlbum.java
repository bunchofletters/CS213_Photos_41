package Controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML private Button CopyPhotosButton;
    @FXML private Button UploadButton;
    @FXML private Button RemoveButton;
    @FXML private Button EditButton;
    @FXML private Button MoveButton;
    @FXML private Button PreviewButton;
    @FXML private Button PasteButton;
    @FXML private Button UploadFromStockButtom;
    @FXML private Button DisplayButton;

    //Pane
    @FXML private TilePane tilePane;
    @FXML private ScrollPane scrollPane;

    //Searches
    @FXML private DatePicker DatePickerBar;

    //label
    @FXML private Label AlbumNameItsIn;

    static int x = 0;

    private static String lastUser = "";

// -------------------------------------------------------------------------------------
public void initialize() throws FileNotFoundException{
    if(!lastUser.equals(user.getUser())){
        track.clean();
        lastUser = user.getUser();
    }
    if(user.getUser().toLowerCase().equals("stock") && !user.Login.getcreatedPhotoAlbum()){
        stockInit();
        user.Login.setcreatedPhotoAlbum(true);
    }
    reload(); 
}

private void stockInit() throws FileNotFoundException{
    File dir = new File("./data");
    File[] images;

    images = dir.listFiles(new FilenameFilter() {
        public boolean accept(File dir, String name){
            if(name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".bmp") || name.toLowerCase().endsWith(".gif")){
                return true;
            }
        return false;
        }
    });

    for(int i = 0; i< images.length; i++){
        InputStream stream = new FileInputStream(images[i].getAbsolutePath());
        Image image = new Image(stream);
        imageAttributes attr = new imageAttributes(image);
        attr.setURL(images[i].getAbsolutePath());
        attr.setCaption(images[i].getName());
        link.addToImage(user.getAlbum(), attr);
    }


}

private void reload(){
    tilePane.getChildren().clear();
    imageAttributeIndex = 0;
    // SHOWS WHAT ALBUM YOURE IN, SHOWS THE NAME OF THE ALBUM IN THE MIDDLE
    AlbumNameItsIn.setText(user.getAlbum().getName());

    images = link.getImageList(user.getAlbum()).getPhotos();
    user.getAlbum().setPhotoNum(images.size());
    for (imageAttributes img : images){
        if (img != null) {
            tilePane.getChildren().add(setImages(img));
        }
        imageAttributeIndex++;
        scrollPane.setContent(tilePane);
        scrollPane.setFitToWidth(true); // Fit content to width
    }
}
// -------------------------------------------------------------------------------------

    public VBox setImages(imageAttributes image){
    // Load the image (replace with your image file path)
    // Image image = new Image("data/Beach.jpeg");
    ImageView imageView = new ImageView(image.getImage());
    // userpage.getlListOfPhotos().getPhotoList().add(image);
    imageView.setFitWidth(150); // Set image width
    imageView.setFitHeight(100); // Set image height
    Label photoName = new Label(image.getCaption());
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
    private imageAttributes persistance;

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
                ObservableList<Node> children = vbox.getChildren();

                for (Node child : children) {
                    if (child instanceof ImageView){
                        ImageView imageView = (ImageView) child;
                        selectImage = imageView.getImage();
                        for(imageAttributeIndex = 0; imageAttributeIndex< images.size();imageAttributeIndex++){
                            if(imageView.getImage().equals(images.get(imageAttributeIndex).getImage())){
                                track.setLastSelectedIndex(imageAttributeIndex);
                                persistance = images.get(imageAttributeIndex);
                                break;
                            }
                        }
                        System.out.println("GointoPhtoDetail: " + images.get(imageAttributeIndex).getURL());
                        track.setSelectedImage(persistance);
                        System.out.println("selectImage: " + selectImage);
                        System.out.println("images size: " + images.size());
                        break;
                    }
                }
            }
        }
    }


// -------------------------------------------------------------------------------------

    /**
     * when logout button is clicked changes scene back to login page
     */
    @FXML void logout() {
        track.clean();
        photo.changeScene("/view/login.fxml");
    }

// -------------------------------------------------------------------------------------

    /**
     * goes back to the userPage when its clieked
     */
    @FXML void returnButton() {
        user.getAlbum().setPhotoNum(link.getImageList(user.getAlbum()).getPhotos().size());
        photo.changeScene("/view/userPage.fxml");
    }

// -------------------------------------------------------------------------------------

    @FXML void paste() {
    if(track.getSaveCopyImage() != null && track.getSaveCopyImage().getImage() != null){
        try {
            // imageAttributes image = track.getSaveCopyImage();
            // System.out.println(track.getSaveCopyImage().getURL());
            if (track.getSaveCopyImage() != null && !link.getImageList(user.getAlbum()).isImageInAlbum(track.getSaveCopyImage())) {
                System.out.println("PASTING PRINT: " + track.getSaveCopyImage().getURL());
                link.addToImage(user.getAlbum(), track.getSaveCopyImage());
                tilePane.getChildren().add(setImages(track.getSaveCopyImage()));
                user.updateUserAlbum();
                track.setSaveCopyImage(null);

            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    }
    
// -------------------------------------------------------------------------------------

    // copies photos
    @FXML void copyPhotos() {
          try {
            if (selectedVBox != null && track.getSelectedImage() != null) {
                // imageAttributes images1 = new imageAttributes(selectImage);
                System.out.println("CopyPhoto: "+ track.getSelectedImage().getURL());
                track.setSaveCopyImage(track.getSelectedImage());
                selectedVBox.setStyle("-fx-border-color: red; -fx-background-color: LIGHTPINK;");
                track.setSelectedImage(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                    System.out.println("Selected file: " + file.getAbsolutePath());
                String path = file.getAbsolutePath();
                InputStream stream = new FileInputStream(path);
                Image image = new Image(stream);
                imageAttributes newImage = new imageAttributes(image);
                newImage.setURL(path);
                System.out.println("Preview: IMAGE ATTR" + newImage);
                // set setSelectedImage() to image in imageTracker.java
                track.setUplaodImage(newImage);

                // Loader
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/previewImageUpload.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Popup Stage
                popupStage = new Stage();
                popupStage.setTitle("Uploading Application");
                popupStage.initModality(Modality.APPLICATION_MODAL); 
                popupStage.setScene(scene);
                popupStage.setResizable(false);

                // When the Popup Window CLoses by Any Means (X or EXIT BUTTON)
                popupStage.setOnHidden(e -> {
                    System.out.println(track.getUplaodImage());
                    if (track.getclosed() && !link.getImageList(user.getAlbum()).isImageInAlbum(track.getUplaodImage())){
                        link.addToImage(user.getAlbum(), track.getUplaodImage());
                        user.getAlbum().setPhotoNum(link.getImageList(user.getAlbum()).getPhotos().size());
                        tilePane.getChildren().add(setImages(track.getUplaodImage()));
                        track.setClosed(false);
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
            tilePane.getChildren().remove(selectedVBox);
            selectedVBox = null;
        }
        
        if (selectImage != null){
            link.getImageList(user.getAlbum()).removeImage(selectImage);
            user.getAlbum().setPhotoNum(link.getImageList(user.getAlbum()).getPhotos().size());
            selectImage = null;
            }
        }


// -------------------------------------------------------------------------------------

    // cahnge caption
    @FXML void edit(ActionEvent event) {
        if (track.getSelectedImage() != null){
            try {
                if (popupStage != null && popupStage.isShowing()) {
                    popupStage.toFront();
                    return;
                }
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/goIntoImageAndEdit.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                
                popupStage = new Stage();
                popupStage.initModality(Modality.APPLICATION_MODAL); 
                popupStage.setScene(scene);
                popupStage.setResizable(false);
    
                popupStage.setOnHidden(e -> {
                    reload();
                });
                popupStage.showAndWait();;
                        
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    
    }

// -------------------------------------------------------------------------------------

    @FXML void move() {
        if (track.getSelectedImage() != null ){
            System.out.println(track.getSelectedImage());
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
                    if (track.move == true && selectedVBox != null){
                    System.out.println(user.getAlbum().getPhotoNum());
                    System.out.println(track.getSelectedImage());
                    tilePane.getChildren().remove(selectedVBox);
                    selectedVBox.setStyle("-fx-border-color: red; -fx-background-color: LIGHTPINK;");
                    link.getImageList(user.getAlbum()).removeImage(selectImage);
                    user.updateUserAlbum(); //update the album that the image was moved from
                    user.getAlbum().setPhotoNum(link.getImageList(user.getAlbum()).getPhotos().size());
                    track.setSelectedImage(null);
                    
                    }   
                });
                popupStage.showAndWait();;
                        
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

// -------------------------------------------------------------------------------------

    @FXML
    void SlideShow(ActionEvent event) {
        if(link.getImageList(user.getAlbum()).getPhotos().size() > 0){
            photo.changeScene("/view/PhotoSlideshow.fxml");
        }
    }
// -------------------------------------------------------------------------------------

    @FXML void UploadFromStock(ActionEvent event) {
        try {
            if (popupStage != null && popupStage.isShowing()) {
                popupStage.toFront();
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/uploadingFromStock.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(scene);
            popupStage.setResizable(false);

            popupStage.setOnHidden(e -> {
                if (track.getStockImage() != null && !link.getImageList(user.getAlbum()).isImageInAlbum(track.getStockImage())) {
                    link.addToImage(user.getAlbum(), track.getStockImage());
                    user.updateUserAlbum();
                    user.getAlbum().setPhotoNum(link.getImageList(user.getAlbum()).getPhotos().size());
                    tilePane.getChildren().add(setImages(track.getStockImage()));
                    track.setStockImage(null);
                    track.setSelectedImage(null);
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
    void display(ActionEvent event) {
        if (selectImage != null){
            photo.changeScene("/view/displayOwnImage.fxml");
        }
    }
    
}