import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class listOfPhotos {

    private ObservableList<Image> photos;

    public listOfPhotos(ObservableList<Image> image){
        this.photos = image;
    }


    public void addPhoto(Image image){
        photos.add(image);
    }
    
    public ObservableList<Image> getPhotos(){
        return photos;
    }

}

