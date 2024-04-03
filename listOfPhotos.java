import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class listOfPhotos {

    private SimpleStringProperty captions;
    private ObservableList<String> tags;
    private ObservableList<Image> photos;

    public listOfPhotos(ObservableList<Image> image){
        this.photos = image;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void setCaption(String caption) {
        this.captions.set(caption);
    }

    public void addPhoto(Image image){
        photos.add(image);
    }
    
    public ObservableList<Image> getPhotos(){
        return photos;
    }

}

