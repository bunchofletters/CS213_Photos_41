import java.util.HashMap;
import java.util.List;

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

// -------------------------------------------------------------------------------------

    void setUserAlbum(String user){
    //If user does not have their own photoAblumList create one for them
        if(photoAlbum.get(user) == null){
        ObservableList<photoAlbumList> x = FXCollections.observableArrayList();
        photoAlbumList tmp = new photoAlbumList(x);
        photoAlbum.put(user, tmp);
        }
    }

// -------------------------------------------------------------------------------------

    public void addToAlbum(String user, photoAlbumList photos){
        //Add the photoAlbum to the user's photoAlbumlist
        photoAlbum.get(user).getAlbumList().add(photos);
    }

// -------------------------------------------------------------------------------------

    /**
     * This is used to get the photoAlbumList associated with each user
     * @param user this is the name of the user
     * @return returns the list of photoAlbum assoicated with that user
     */
    public photoAlbumList getPhotoAlbum(String user){
        return photoAlbum.get(user);
    }

    //to do: remove

// -------------------------------------------------------------------------------------

    private static HashMap<photoAlbumList, listOfPhotos> imageList = new HashMap<>();

    void setAlbumImages(photoAlbumList photos){
        if(imageList.get(photos) == null){
            ObservableList<Image> x = FXCollections.observableArrayList();
            listOfPhotos tmp = new listOfPhotos(x);
            imageList.put(photos, tmp);
        }
    }

// -------------------------------------------------------------------------------------

    public void addToImage(photoAlbumList albumnlist, Image image){
    imageList.get(albumnlist).getPhotos().add(image);
    }

// -------------------------------------------------------------------------------------

    /**
     * This method is use to get hte listOfPhoto assoicated with a photoa blum from the hashmap
     * @param list This takes in a photoAlbumList 
     * @return returns back the listofphoto assoicated with that photoalbum
     */
    public listOfPhotos getImageList(photoAlbumList list){
        return imageList.get(list);
    }

// -------------------------------------------------------------------------------------

    public void removeImage(photoAlbumList albumList, Image imageToRemove) {
    listOfPhotos photos = imageList.get(albumList);
        if (photos != null) {
            List<Image> photoList = photos.getPhotos();
            for (int i = 0; i < photoList.size(); i++) {
                Image image = photoList.get(i);
                if (image.equals(imageToRemove)) {
                    photoList.remove(i); 
                    break; 
                }
            }
        }
    }

// -------------------------------------------------------------------------------------

    public boolean isImageInAlbum(photoAlbumList albumList, Image image) {
    listOfPhotos photos = imageList.get(albumList);
        if (photos != null) {
            List<Image> photoList = photos.getPhotos();
            for (Image img : photoList) {
                if (img.getUrl().equals(image.getUrl())) {
                return true;
                }
            }
        }
        return false;
    }


}
