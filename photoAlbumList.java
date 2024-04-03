import java.util.HashMap;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class photoAlbumList {

    private SimpleStringProperty AlbumName;
    private SimpleIntegerProperty NumberOfPhotos, EarliestPhotoDate, LatestPhotoDate;
    private ObservableList<photoAlbumList> photoAlbum;


    public photoAlbumList(ObservableList<photoAlbumList> photoAlbum){
        this.photoAlbum = photoAlbum;
    }

    public photoAlbumList(String name, int photoNum, int lowestDate, int highDate){
        this.AlbumName = new SimpleStringProperty(name);
        this.NumberOfPhotos = new SimpleIntegerProperty(photoNum);
        this.EarliestPhotoDate = new SimpleIntegerProperty(lowestDate);
        this.LatestPhotoDate = new SimpleIntegerProperty(highDate);
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
}