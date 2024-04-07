package Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 * This class represents a list of photo albums.
 * @author oscar
 * @author danny
 */
public class photoAlbumList {

    private SimpleStringProperty AlbumName,EarliestPhotoDate, LatestPhotoDate;
    private SimpleIntegerProperty NumberOfPhotos;
    private ObservableList<photoAlbumList> photoAlbum;

     /**
     * Constructs a photoAlbumList object with the specified list of photo albums.
     *
     * @param photoAlbum The list of photo albums to be associated with this object.
     */
    public photoAlbumList(ObservableList<photoAlbumList> photoAlbum){
        this.photoAlbum = photoAlbum;
    }

     /**
     * Constructs a photoAlbumList object with the specified name, number of photos, and date range.
     *
     * @param name The name of the photo album.
     * @param photoNum The number of photos in the photo album.
     * @param lowestDate The earliest date of the photos in the photo album.
     * @param highDate The latest date of the photos in the photo album.
     */
    public photoAlbumList(String name, int photoNum, String lowestDate, String highDate){
        this.AlbumName = new SimpleStringProperty(name);
        this.NumberOfPhotos = new SimpleIntegerProperty(photoNum);
        this.EarliestPhotoDate = new SimpleStringProperty(lowestDate);
        this.LatestPhotoDate = new SimpleStringProperty(highDate);
    }

     /**
     * Sets the name of the photo album.
     *
     * @param input The name to be set.
     */
    public String getName(){
        return AlbumName.get();
    }
    
    public int getPhotoNum(){
        return NumberOfPhotos.get();
    }
    public String getLowestDate(){
        return EarliestPhotoDate.get();
    }
    public String getHighestDate(){
        return LatestPhotoDate.get();
    }
    public void setName(String input){
         this.AlbumName.set(input);
    }
    public void setPhotoNum(int input){
        this.NumberOfPhotos.set(input);
    }
    public void setLowestDate(String input){
        this.EarliestPhotoDate.set(input);
    }
    public void setHighestDate(String input){
        this.LatestPhotoDate.set(input);
    }

    /**
     * @return the PhotoAlbum list associated with a user
     */
    public ObservableList<photoAlbumList> getAlbumList(){
        return photoAlbum;
    }
}