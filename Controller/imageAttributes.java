package Controller;
import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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


    public imageAttributes(Image image){
        this.image = image;
        System.out.println(uploadDate);
        uploadDate = LocalDate.now();
    }
    
    // public imageAttributes(Image image, )

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void setCaption(String caption) {
        this.captions.set(caption);
    }

    public void setTag(ObservableList<String> data){
        tags = data;
    }

    public Image getImage(){
        if(image != null)
            return image;
        return null;
    }
    
    public String getUploadDate(){
        return uploadDate.toString();
    }

    public LocalDate getUploadDateAsDate(){
        return uploadDate;
    }

    public ObservableList<String> getTags(){
        if(tags != null)
            return tags;
        tags = FXCollections.observableArrayList();
        return tags;
    }

    public String getCaption(){
        if(captions != null)
            return captions.get();
        return "n/a";
    }
    public void setDate(LocalDate date){
        this.uploadDate = date;
    }


    
}
