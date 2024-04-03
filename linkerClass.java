import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class linkerClass {
    
    // user to photoAlbum
    private static HashMap<String, photoAlbumList> photoAlbum = new HashMap<>();

    private static linkerClass instance;
    public static linkerClass getInstance() {
        if (instance == null) {
            instance = new linkerClass();
        }
        return instance;
    }

    void setUserAlbum(String user){
        //If user does not have their own photoAblumList create one for them
        if(photoAlbum.get(user) == null){
            ObservableList<photoAlbumList> x = FXCollections.observableArrayList();
            photoAlbumList tmp = new photoAlbumList(x);
            photoAlbum.put(user, tmp);
        }
    }

    public void addToAlbum(String user, photoAlbumList photos){
        //Add the photoAlbum to the user's photoAlbumlist
        photoAlbum.get(user).getAlbumList().add(photos);
    }

    public photoAlbumList getPhotoAlbum(String user){
        return photoAlbum.get(user);
    }

    //to do: remove
    // -----------------------------------------------------------------------------------

    private static HashMap<photoAlbumList, listOfPhotos> imageList = new HashMap<>();

    void setAlbumImages(photoAlbumList photos){
        if(imageList.get(photos) == null){
            ObservableList<Image> x = FXCollections.observableArrayList();
            listOfPhotos tmp = new listOfPhotos(x);
            imageList.put(photos, tmp);
        }
    }

    public void addToImage(photoAlbumList albumnlist, Image image){
        imageList.get(albumnlist).getPhotos().add(image);
    }

    public listOfPhotos getImageList(photoAlbumList list){
        return imageList.get(list);
    }

    public String getAlbumName(photoAlbumList photos){
        return photos.getName();
    }


}
