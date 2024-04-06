import java.io.File;
import java.io.FilenameFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class uploadingFromStock {

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
        
    }

    @FXML
    void setThisImage() {
        long st = System.nanoTime();
        int photoPos = stockPhotoList.getSelectionModel().getSelectedIndex();
        String filepath = images[photoPos].getPath();
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
