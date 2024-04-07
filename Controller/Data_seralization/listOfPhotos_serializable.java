package Controller.Data_seralization;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class holds a list of imageAtrributes and will be used when attempting to upload photo to a certain album
 * @author danny
 * @author oscar
 */
public class listOfPhotos_serializable implements Serializable{

    private ArrayList<imageAttributes_serializable> photos = new ArrayList<>();

    /**
     * Initalizes the ArrayList of photos
     * @param image this is a imageAttribute ObservableList 
     */
    public listOfPhotos_serializable(ArrayList<imageAttributes_serializable> image){
            this.photos = image;
    }
    
    public ArrayList<imageAttributes_serializable> getPhotos(){
        return photos;
    }

}

