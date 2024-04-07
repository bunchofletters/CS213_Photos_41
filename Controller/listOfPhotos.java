package Controller;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * This class holds a list of imageAtrributes and will be used when attempting to upload photo to a certain album
 * @author danny
 * @author oscar
 */
public class listOfPhotos{

    private ObservableList<imageAttributes> photos;

     /**
     * Constructs a listOfPhotos object with the specified list of image attributes.
     *
     * @param image The list of image attributes to be associated with this object.
     */
    public listOfPhotos(ObservableList<imageAttributes> image){
            this.photos = image;
    }

    /**
     * Adds a photo to the list of photos.
     * It also prints the file path of the photo.
     *
     * @param image The photo to be added.
     */
    public void addPhoto(Image image){
        imageAttributes newImage = new imageAttributes(image);
        photos.add(newImage);
    }
    
    /**
     * Returns the list of photos.
     *
     * @return The list of photos.
     */
    public ObservableList<imageAttributes> getPhotos(){
        return photos;
    }

}

