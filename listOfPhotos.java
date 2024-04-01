import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class listOfPhotos {
    Image photos;

    ObservableList<listOfPhotos> photoList = FXCollections.observableArrayList();

    public listOfPhotos(ObservableList<listOfPhotos> photoList){
        this.photoList = photoList;
    }

    public listOfPhotos(Image uplaod){
        this.photos = uplaod;
    }

    public ObservableList<listOfPhotos> getPhotoList(){
        return photoList;
    }

}
