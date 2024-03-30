package Controller;

import java.io.File;
import java.io.FilenameFilter;

import app.photo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class stockImageUser {

    private static ObservableList<String> Stock_Image = FXCollections.observableArrayList();
    private File dir = new File("./StockPhotos");
    private static File[] images;
    @FXML
    private ImageView ImageViewer;

    @FXML
    private ListView<String> stockPhotoList;

    public void initialize(){
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
                Stock_Image.add(x.getPath().substring(x.getPath().lastIndexOf('\\')+1,x.getPath().lastIndexOf(".")));
            }
        }

        stockPhotoList.getItems().clear();
        stockPhotoList.getItems().addAll(Stock_Image);

        String filepath = images[0].getPath();
        filepath = "." + filepath;
        filepath.replace('\\', '/');
        Image imageview = new Image(getClass().getResourceAsStream(filepath));
        ImageViewer.setImage(imageview);
    }

    @FXML
    void setThisImage() {
        int photoPos = stockPhotoList.getSelectionModel().getSelectedIndex();
        String filepath = images[photoPos].getPath();
        filepath = "." + filepath;
        filepath.replace('\\', '/');
        Image imageview = new Image(getClass().getResourceAsStream(filepath));
        ImageViewer.setImage(imageview);
    }

    @FXML
    void backToLogin() {
        photo x = new photo();
        x.changeScene("/view/login.fxml");
    }


}
