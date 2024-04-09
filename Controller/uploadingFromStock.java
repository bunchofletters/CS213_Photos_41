package Controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.InputStream;

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

    linkerClass link = linkerClass.getInstance();
    private imageTracker track = imageTracker.getInstance();

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
    void save(ActionEvent event) throws FileNotFoundException {
        int index = stockPhotoList.getSelectionModel().getSelectedIndex() < 0 ? 0 : stockPhotoList.getSelectionModel().getSelectedIndex();
        String filepath = images[index].getAbsolutePath();
        InputStream stream = new FileInputStream(filepath);
        Image images1 = new Image(stream);
        imageAttributes newImage = new imageAttributes(images1);
        newImage.setURL(images[index].getAbsolutePath());
        newImage.setCaption(images[index].getName());
        track.setStockImage(newImage);
        System.out.println(newImage);
        Stage stage = (Stage) SaveButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void setThisImage() {
        int photoPos = stockPhotoList.getSelectionModel().getSelectedIndex();
        String filepath = images[photoPos].getPath();
        filepath = filepath.substring(1);
        if(filepath.contains("\\"))
            filepath = filepath.replace('\\', '/');
        Image imageview = new Image(getClass().getResourceAsStream(filepath));
        ImageViewer.setImage(imageview);

    }

    public void initialize() throws FileNotFoundException{
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
    
            String filepath = images[0].getAbsolutePath();
            System.out.println(filepath);
            InputStream stream = new FileInputStream(filepath);
            Image imageview = new Image(stream);
            ImageViewer.setImage(imageview);
        }

}
