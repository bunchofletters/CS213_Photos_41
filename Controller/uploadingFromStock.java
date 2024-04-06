package Controller;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import app.Photo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class uploadingFromStock {

    private listOfPhotos photoList;
    linkerClass link = linkerClass.getInstance();
    private imageTracker track = imageTracker.getInstance();
    private userPage user = userPage.getInstance();
    private Photo photo = Photo.getInstance();

    private static ObservableList<String> Stock_Image = FXCollections.observableArrayList();
    private File dir = new File("./data");
    private static File[] images;
    
    @FXML
    private ImageView ImageViewer;

    @FXML
    private Button SaveButton;

    @FXML
    private ListView<String> stockPhotoList;

    @FXML
    void save(ActionEvent event) {
        int index = stockPhotoList.getSelectionModel().getSelectedIndex() < 0 ? 0 : stockPhotoList.getSelectionModel().getSelectedIndex();
        Image images1 = new Image(getClass().getResourceAsStream(images[index].getPath().substring(1)));
        imageAttributes newImage = new imageAttributes(images1);
        newImage.setURL(images[index].getPath().substring(1));
        track.setStockImage(newImage);
        Stage stage = (Stage) SaveButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void setThisImage() {
        long st = System.nanoTime();
        int photoPos = stockPhotoList.getSelectionModel().getSelectedIndex();
        String filepath = images[photoPos].getPath();
        filepath = filepath.substring(1);
        if(filepath.contains("\\"))
            filepath.replace('\\', '/');
        Image imageview = new Image(getClass().getResourceAsStream(filepath));
        ImageViewer.setImage(imageview);
        long n = System.nanoTime();
        double na = n-st;
        System.out.println(filepath+ ": " + na);
    }

    public void initialize(){
        long st = System.nanoTime();
            if(Stock_Image.size() == 0 || images.length > Stock_Image.size()){
                images = dir.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name){
                        if(name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".bmp") || name.toLowerCase().endsWith(".gif")){
                            return true;
                        }
                    return false;
                    }
                });
            
                for (File x : images){
                    String photoName = x.getPath().substring(x.getPath().lastIndexOf('\\')+1,x.getPath().lastIndexOf("."));
                    if(photoName.contains("/")){
                        photoName = x.getPath().substring(x.getPath().lastIndexOf("/")+1, x.getPath().lastIndexOf("."));
                    }
                    Stock_Image.add(photoName);
                }
            }
    
            stockPhotoList.getItems().clear();
            stockPhotoList.getItems().addAll(Stock_Image);
    
            String filepath = images[0].getPath();
            filepath = filepath.substring(1);
            if(filepath.contains("\\")){
                filepath.replace('\\', '/');
            }
            
            Image imageview = new Image(getClass().getResourceAsStream(filepath));
            ImageViewer.setImage(imageview);

    
            long n = System.nanoTime();
            double na = n-st;
            System.out.println(na);
        }

}
