package Controller;
import java.time.LocalDate;
import java.util.Optional;

import app.Photo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class searchMode {

    private Photo x = Photo.getInstance();
    linkerClass link = linkerClass.getInstance();
    private userPage user = userPage.getInstance();
    private ObservableList<imageAttributes> filterList;

    @FXML private TextField FilterInput;

    @FXML private Button ReturnButton;
    @FXML private Button SearchInput;
    @FXML private Button UploadButton;
    @FXML private Button logoutButton;

    @FXML private ScrollPane scrollPane;

    @FXML private TilePane tilePane;


// -------------------------------------------------------------------------------------

    public void initialize() {
        populate();      
    }
// -------------------------------------------------------------------------------------

    private VBox selectedVBox = null;

    @FXML void goIntoPhotoDetails(MouseEvent event) {
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

    private void clear(){
        tilePane.getChildren().clear();
    }

    private void populate(){
        clear();
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

    public VBox setImages(ObservableList<imageAttributes> observableList, int index){
        
        ImageView imageView = new ImageView(observableList.get(index).getImage());
        imageView.setFitWidth(150); // Set image width
        imageView.setFitHeight(100); // Set image height
        Label photoName = new Label(observableList.get(index).getCaption());
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

    @FXML void logout() {
        x.changeScene("/view/login.fxml");
    }

// -------------------------------------------------------------------------------------

    @FXML void returnButton() {
        x.changeScene("/view/userPage.fxml");
    }

// -------------------------------------------------------------------------------------

    @FXML void searchbox() {
        clear();
        if(filterList != null)
            filterList.clear();
        filterList = FXCollections.observableArrayList();
        int filterType = -1;
        if(FilterInput.getText().contains("=")){
            filterType = 0; //0 for single tag-value searching
            if(FilterInput.getText().lastIndexOf("=") != FilterInput.getText().indexOf("="))
                filterType = 1; //1 for two tag-value serching
        }
        else if(FilterInput.getText().contains("/")){
            filterType = 2; //2 for date search
        }
        else{
            filterType = 3; //3 for caption search
        }
        for(int i = 0; i<link.getPhotoAlbum(user.getUser()).getAlbumList().size(); i++){
            listOfPhotos tmp = link.getImageList(link.getPhotoAlbum(user.getUser()).getAlbumList().get(i));
            int counter = 0;
            for(imageAttributes x : tmp.getPhotos()){
                if(filterType == 0){ //single tag filter
                    for(int m = 0; m < tmp.getPhotos().get(counter).getTags().size(); m++){
                        String check = tmp.getPhotos().get(counter).getTags().get(m);
                        if((check).equals(FilterInput.getText()))
                            filterList.add(x);
                    }
                }
                else if(filterType == 1){ //double tag filter
                    for(int m = 0; m <tmp.getPhotos().get(counter).getTags().size(); m++){
                        String check = tmp.getPhotos().get(counter).getTags().get(m);
                        if(FilterInput.getText().equals(check) || FilterInput.getText().equals(check))
                            filterList.add(x);
                    }
                }
                else if (filterType == 2){
                    String date1 = FilterInput.getText().substring(0,10); //1234(5)67(8)910
                    String date2 = FilterInput.getText().substring(11);
                    LocalDate earlyDate = LocalDate.of(Integer.parseInt(date1.substring(0,4)), Integer.parseInt(date1.substring(5, 7)), Integer.parseInt(date1.substring(8)));
                    LocalDate lateDate = LocalDate.of(Integer.parseInt(date2.substring(0,4)), Integer.parseInt(date2.substring(5, 7)), Integer.parseInt(date2.substring(8)));
                    LocalDate photoDate = x.getUploadDateAsDate();
                    if ((photoDate.isEqual(earlyDate) || photoDate.isEqual(lateDate)) || (photoDate.isAfter(earlyDate) && photoDate.isBefore(lateDate))){
                        filterList.add(x);
                    }
                }
                else if(filterType == 3){
                    //check for match case maybe
                    if(tmp.getPhotos().get(counter).getCaption().toLowerCase().contains(FilterInput.getText().toLowerCase())){
                        filterList.add(x);
                    }
                }
                counter++;
            }
        }
        //run dup removal
        removeDup(filterList);
        //displayto vbox
        for(int i = 0; i<filterList.size();i++){
            tilePane.getChildren().add(setImages(filterList,i));
        }
        scrollPane.setContent(tilePane);
        scrollPane.setFitToWidth(true); // Fit content to width
    }

// -------------------------------------------------------------------------------------

    // this is not uplaod this is : "Create Album From Search Results"
    @FXML void makeAlbumn() {
        if(filterList != null && filterList.size() > 0){
            TextInputDialog td = new TextInputDialog();
            td.setTitle("Album Title");
            td.setHeaderText("Enter a Name for the New Album");
            td.setContentText("Name: ");
            Optional<String> result = td.showAndWait();
            if(result.isPresent()){
                if(result.get().equals("")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("An Error Has Occured");
                    alert.setContentText("You Didn't Enter a Name for the New Albumn. Discarding New Albumn");
                    alert.showAndWait();
                }
                else if(result.get().substring(0,1).equals(" ")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("An Error Has Occured");
                    alert.setContentText("Illegal Starting Expression of [' ']. Discarding New Albumn");
                    alert.showAndWait();
                }
                else{
                    boolean alreadyMade = false;
                    for(int i = 0; i< link.getPhotoAlbum(user.getUser()).getAlbumList().size();i++){
                        if(link.getPhotoAlbum(user.getUser()).getAlbumList().get(i).getName().equals(result.get())){
                            alreadyMade = true;
                            break;
                        }
                    }
                    if(alreadyMade){
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("An Error Has Occured");
                        alert.setContentText("You Enter a Name for an Album that Already Has That Name. Do You Wish to Rename the New Album");
                        Optional<ButtonType> click= alert.showAndWait();
                        if(click.isPresent()){
                            if(click.get() == ButtonType.OK){
                                makeAlbumn();
                            }
                        }
                    }
                    else{
                        createAlbumn(result.get());
                    }
                }
            }
            else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("An Error Has Occured");
                alert.setContentText("You Didn't Enter a Name for the New Albumn. Discarding New Albumn");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An Error Has Occured");
            alert.setContentText("You've Attempted to Create an Albumn with Zero Images. Please Try Again with 1 or more images");
            alert.showAndWait();
        }
    }

    private void createAlbumn(String name){
        photoAlbumList newAlbumn = new photoAlbumList(name, filterList.size(), "N/A", "N/A");
        link.addToAlbum(user.getUser(), newAlbumn);
        ObservableList<imageAttributes> copyFilter = FXCollections.observableArrayList();
        copyFilter.addAll(filterList);
        listOfPhotos newPhotoList = new listOfPhotos(copyFilter);
        link.getPhotoList().put(newAlbumn, newPhotoList);
        //Resetting search page back to empty
        FilterInput.clear();
        filterList.clear();
        populate();
    }

// -------------------------------------------------------------------------------------

    private void removeDup(ObservableList<imageAttributes> name){
        for(int i =0; i<name.size(); i++){
            String url = name.get(i).getURL();
            for(int j = i+1; j <name.size(); j++){
                if(name.get(j).getURL().equals(url)){
                    name.remove(j);
                    j--;
                }
            }
        }
    }
}





