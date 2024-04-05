import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;


public class imageAttributes {

    private SimpleStringProperty captions;
    private ObservableList<String> tags;
    private Image image;
    private LocalDate uploadDate;


    public imageAttributes(Image image, LocalDate uploadDate){
        this.image = image;
        this.uploadDate = uploadDate;
    }
    
    // public imageAttributes(Image image, )

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void setCaption(String caption) {
        this.captions.set(caption);
    }

    public Image getImage(){
        return image;
    }
    
    public LocalDate getUploadDate(){
        return uploadDate;
    }


    
}
