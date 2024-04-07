package Controller;
import java.io.File;
import java.io.FilenameFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import app.Photo;


public class stockImageUser {

    private static ObservableList<String> Stock_Image = FXCollections.observableArrayList();
    private File dir = new File("./data");
    private static File[] images;

    @FXML private ImageView ImageViewer;

    @FXML private ListView<String> stockPhotoList;

// -------------------------------------------------------------------------------------

    /**
     * This will populate the list with the name of each stock photo 
     * and will automatically display the first stock photo
     */
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
                String photoName = x.getPath().substring(x.getPath().lastIndexOf("\\")+1,x.getPath().lastIndexOf("."));
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
        System.out.println(filepath);
        if(filepath.contains("\\")){
            filepath = filepath.replace("\\", "/");
        }
        System.out.println(filepath);
        
        Image imageview = new Image(getClass().getResourceAsStream(filepath));
        ImageViewer.setImage(imageview);

        long n = System.nanoTime();
        double na = n-st;
        System.out.println(na);
    }

// -------------------------------------------------------------------------------------

    /**
     * This method changes the stock photo display depending on which image the user
     * selected on the list
     */
    @FXML void setThisImage() {
        long st = System.nanoTime();
        int photoPos = stockPhotoList.getSelectionModel().getSelectedIndex();
        String filepath = images[photoPos].getPath();
        filepath = filepath.substring(1);
        if(filepath.contains("\\"))
            filepath = filepath.replace("\\", "/");
        Image imageview = new Image(getClass().getResourceAsStream(filepath));
        ImageViewer.setImage(imageview);
        long n = System.nanoTime();
        double na = n-st;
        System.out.println(filepath+ ": " + na);
        
    }

// -------------------------------------------------------------------------------------
    /**
     * return back to the login page
     */
    @FXML void backToLogin() {
        Photo x = new Photo();
        x.changeScene("/view/login.fxml");
    }


}
