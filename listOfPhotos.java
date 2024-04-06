import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * This class holds a list of imageAtrributes and will be used when attempting to upload photo to a certain album
 * @author danny
 * @author oscar
 */
public class listOfPhotos {

    private ObservableList<imageAttributes> photos;

    /**
     * Initalizes the ArrayList of photos
     * @param image this is a imageAttribute ObservableList 
     */
    public listOfPhotos(ObservableList<imageAttributes> image){
            this.photos = image;
    }


    public void addPhoto(Image image){
        LocalDate x = LocalDate.now();
        imageAttributes newImage = new imageAttributes(image, x);
        photos.add(newImage);
    }
    
    public ObservableList<imageAttributes> getPhotos(){
        return photos;
    }

}

