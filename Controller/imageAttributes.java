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
    private ObservableList<String> tags = FXCollections.observableArrayList();
    private Image image;
    private LocalDate uploadDate;
    private String url;




// -------------------------------------------------------------
    /**
     * This constructor initializes an imageAttributes object with the provided image. 
     * It also sets the upload date to the current date.
     * 
     * @param image image The image to be associated with this imageAttributes object.
     */
    public imageAttributes(Image image){
        this.image = image;
        System.out.println(uploadDate);
        LocalDate date = LocalDate.now();
        this.uploadDate = date;
    }
// -------------------------------------------------------------

    /**
    * This method adds a tag to the image.
    *
    * @param tag The tag to be added.
    */
    public void addTag(String tag) {
        tags.add(tag);
    }

// -------------------------------------------------------------

    /**
    * This method sets the caption of the image.
    *
    * @param caption The caption to be set.
    */
    public void setCaption(String caption) {
        this.captions = new SimpleStringProperty();
        this.captions.set(caption);
    }

    // -------------------------------------------------------------

    /**
    * This method sets the URL of the image.
    *
    * @param url The URL to be set.
    */
    public void setURL(String url){
        this.url = url;
    }

    public void setTag(ObservableList<String> data){
        tags = data;
    }
// -------------------------------------------------------------

    /**
    * This method returns the image.
    *
    * @return The image.
    */
    public Image getImage(){
        return image;
    }
    

    /**
    * This method returns the upload date of the image.
    *
    * @return The upload date as a string.
    */
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

    // -------------------------------------------------------------

    /**
    * This method returns the URL of the image.
    *
    * @return The URL of the image.
    */
    public String getURL(){
        return url;
    }


    
}
