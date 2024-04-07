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
     * Initalizes the ArrayList of photos
     * @param image this is a imageAttribute ObservableList 
     */
    public listOfPhotos(ObservableList<imageAttributes> image){
            this.photos = image;
    }


    public void addPhoto(Image image){
        imageAttributes newImage = new imageAttributes(image);
        System.out.println("This is photo File Path: " + image.getUrl());
        photos.add(newImage);
    }
    
    public ObservableList<imageAttributes> getPhotos(){
        return photos;
    }

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

