package Controller;
import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * This class will hold all the attribute of the image such as:
 * image, tags, captions, and uploadDate
 * @author danny
 * @author oscar
 */
public class imageAttributes {

    private SimpleStringProperty captions;
    private ObservableList<String> tags;
    private Image image;
    private LocalDate uploadDate;
    private String url;


    public imageAttributes(Image image){
        this.image = image;
        System.out.println(uploadDate);
        LocalDate date = LocalDate.now();
        this.uploadDate = date;
    }
    
    // public imageAttributes(Image image, )

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void setCaption(String caption) {
        this.captions.set(caption);
    }

    public void setURL(String url){
        this.url = url;
    }

    public Image getImage(){
        return image;
    }
    
    public String getUploadDate(){
        return uploadDate.toString();
    }

    public String getName(){
        return captions.get();
    }

    public String getURL(){
        return url;
    }
    


    
}
