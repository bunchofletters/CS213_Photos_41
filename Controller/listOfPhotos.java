package Controller;
<<<<<<< HEAD
=======
import java.time.LocalDate;
>>>>>>> 4989309 (:))

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
     * Initalizes the ArrayList of photos
     * @param image this is a imageAttribute ObservableList 
     */
    public listOfPhotos(ObservableList<imageAttributes> image){
            this.photos = image;
    }


    public void addPhoto(Image image){
        imageAttributes newImage = new imageAttributes(image);
<<<<<<< HEAD
        System.out.println("This is photo File Path: " + image.getUrl());
=======
>>>>>>> 4989309 (:))
        photos.add(newImage);
    }
    
    public ObservableList<imageAttributes> getPhotos(){
        return photos;
    }

}

