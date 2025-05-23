package Controller;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * This class allows each user to have unique photo album along with each photo album having their own list of photos
 * @author oscar
 */
public class linkerClass{
    
    // user to photoAlbum
    private static HashMap<String, photoAlbumList> photoAlbum = new HashMap<>();

    private static linkerClass instance;
    
    public static linkerClass getInstance() {
        if (instance == null) {
            instance = new linkerClass();
        }
        return instance;
    }

    public void loadUserToAlbum(HashMap<String, photoAlbumList> loadData){
        photoAlbum = loadData;
    }

    public void loadAlbumToList(HashMap<photoAlbumList, listOfPhotos> loadData){
        imageList = loadData;
    }

// -------------------------------------------------------------------------------------
    public HashMap<String, photoAlbumList> getDataPhotoAlbum(){
        return photoAlbum;
    }
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

    /**
     * Remove the user and all photo album from the hashmap, this should only be called the the class that has the ability to remove users
     * @param user the name of the user
     */
    public void removeUser(String user){
        //Remove all photoALbum->listofPhoto connection first
        for(int i =0; i<photoAlbum.get(user).getAlbumList().size(); i++){
            removePhotoList(photoAlbum.get(user).getAlbumList().get(i));
        }
        //remove the user->photoAlbumlist connection
        photoAlbum.remove(user);
    }

// -------------------------------------------------------------------------------------
    
    private static HashMap<photoAlbumList, listOfPhotos> imageList = new HashMap<>();

    public HashMap<photoAlbumList, listOfPhotos> getPhotoList(){
        return imageList;
    }


    void setAlbumImages(photoAlbumList photos){
        if(imageList.get(photos) == null){
            ObservableList<imageAttributes> x = FXCollections.observableArrayList();
            listOfPhotos tmp = new listOfPhotos(x);
            imageList.put(photos, tmp);
        }
    }
    

// -------------------------------------------------------------------------------------

    public void addToImage(photoAlbumList albumnlist, imageAttributes image){
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

    /**
     * should be called by whatever class has the ability to remove photoAlbums
     * @param list the photoAlbum that is to be deleted
     */
    public void removePhotoList(photoAlbumList list){
        imageList.remove(list);
    }
}
