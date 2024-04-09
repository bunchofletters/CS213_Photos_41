package Controller;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * This class holds a list of imageAtrributes and will be used when attempting 
 * to upload photo to a certain album
 * 
 * @author danny
 * @author oscar
 */
public class listOfPhotos{

    private ObservableList<imageAttributes> photos;

    // -------------------------------------------------------------------------------------

    /**
     * Initalizes the ArrayList of photos
     * @param image this is a imageAttribute ObservableList 
     */
    public listOfPhotos(ObservableList<imageAttributes> image){
            this.photos = image;
    }

// -------------------------------------------------------------------------------------
    /**
     * adds a new image to the photo list
     * @param image image object to add to the photo list
     */
    public void addPhoto(Image image){
        imageAttributes newImage = new imageAttributes(image);
        System.out.println("This is photo File Path: " + image.getUrl());
        photos.add(newImage);
    }

// -------------------------------------------------------------------------------------
    
    /**
     * @return An ObservableList of imageAttributes.
     */
    public ObservableList<imageAttributes> getPhotos(){
        return photos;
    }

// -------------------------------------------------------------------------------------

    /**
     * removes an image from the photo list.
     * @param imageToRemove The image object to remove from the photo list.
     * 
     */
    public void removeImage(Image imageToRemove) {
        if (photos != null) {
            for (int i = 0; i < photos.size(); i++) {
                Image image = photos.get(i).getImage();
                if (image.equals(imageToRemove)) {
                    photos.remove(i); 
                    break; 
                }
            }
        }
    }

// -------------------------------------------------------------------------------------

    /**
     * checks if an image is in the photo album.
     * @param image The imageAttributes object to check for in the photo album.
     * @return A boolean indicating whether the image is in the photo album or not.
     */
    public boolean isImageInAlbum(imageAttributes image) {
            if (photos != null) {
                for (imageAttributes img : photos) {
                    System.out.println(img.getURL());
                    System.out.println(image.getURL());
                    if (img.getURL().equals(image.getURL())) {
                        return true;
                    }
                }
            }
            return false;
        }
}

