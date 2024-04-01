import java.util.HashMap;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class photoAlbumList {
    private SimpleStringProperty AlbumName;
    private SimpleIntegerProperty NumberOfPhotos, EarliestPhotoDate, LatestPhotoDate;

    private ObservableList<photoAlbumList> photoAlbum;

    /*
     * <String, listOfPhotos>
     * String = photoAlbumName
     * listofPhotos = listofPhoto class
     */
    private HashMap<String, listOfPhotos> albumNPhots = new HashMap<>();


    public photoAlbumList(ObservableList<photoAlbumList> photoAlbum){
        this.photoAlbum = photoAlbum;
    }

    public photoAlbumList(String name, int photoNum, int lowestDate, int highDate){
        this.AlbumName = new SimpleStringProperty(name);
        this.NumberOfPhotos = new SimpleIntegerProperty(photoNum);
        this.EarliestPhotoDate = new SimpleIntegerProperty(lowestDate);
        this.LatestPhotoDate = new SimpleIntegerProperty(highDate);
    }

    public void addPhotoList(String name, listOfPhotos list){
        albumNPhots.put(name, list);
    }

    public String getName(){
        return AlbumName.get();
    }
    public int getPhotoNum(){
        return NumberOfPhotos.get();
    }
    public int getLowestDate(){
        return EarliestPhotoDate.get();
    }
    public int getHighestDate(){
        return LatestPhotoDate.get();
    }
    public void setName(String input){
         this.AlbumName.set(input);
    }
    public void setPhotoNum(int input){
        this.NumberOfPhotos.set(input);
    }
    public void setLowestDate(int input){
        this.EarliestPhotoDate.set(input);
    }
    public void setHighestDate(int input){
        this.LatestPhotoDate.set(input);
    }
    public ObservableList<photoAlbumList> getAlbumList(){
        return photoAlbum;
    }

    /**
     * 
     * @param albumName the name [String] of the album you wish to access the photo of
     * @return the class listOfPhotos from the hashmap {@link #albumNPhots}
     * @see listOfPhotos
     */
    public listOfPhotos geListOfPhotos(String albumName){
        return albumNPhots.get(albumName);
    }
}