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
     * initializes an imageAttributes object with the provided image. 
     * sets the upload date to the current date.
     * @param image image to be associated with this imageAttributes object.
     */
    public imageAttributes(Image image){
        this.image = image;
        System.out.println(uploadDate);
        LocalDate date = LocalDate.now();
        this.uploadDate = date;
    }
// -------------------------------------------------------------

    /**
    * adds a tag to the image.
    * @param tag The tag to be added.
    */
    public void addTag(String tag) {
        tags.add(tag);
    }

// -------------------------------------------------------------

    /**
    * sets the caption of the image.
    * @param caption The caption to be set.
    */
    public void setCaption(String caption) {
        this.captions = new SimpleStringProperty();
        this.captions.set(caption);
    }

    // -------------------------------------------------------------

    /**
    * @param url The URL to be set
    */
    public void setURL(String url){
        this.url = url;
    }


    /**
     * @param data the tag to be set 
     */
    public void setTag(ObservableList<String> data){
        tags = data;
    }
// -------------------------------------------------------------

    /**
    * @return The image
    */
    public Image getImage(){
        return image;
    }

// -------------------------------------------------------------

    /**
    * @return The upload date as a string
    */
    public String getUploadDate(){
        return uploadDate.toString();
    }

// -------------------------------------------------------------

    /**
     * @return The upload date as a LocalDate object
     */
    public LocalDate getUploadDateAsDate(){
        return uploadDate;
    }

// -------------------------------------------------------------

    /**
     * @return An ObservableList of tags
     */
    public ObservableList<String> getTags(){
        if(tags != null)
            return tags;
        tags = FXCollections.observableArrayList();
        return tags;
    }

// -------------------------------------------------------------

    /**
     * @return The caption as a string
     */
    public String getCaption(){
        if(captions != null)
            return captions.get();
        return "n/a";
    }

// -------------------------------------------------------------

    /**
     * @param date The date to set as the upload date
     */
    public void setDate(LocalDate date){
        this.uploadDate = date;
    }

// -------------------------------------------------------------

    /**
    * @return The URL of the image.
    */
    public String getURL(){
        return url;
    }


    
}
