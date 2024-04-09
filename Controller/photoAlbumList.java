package Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class of list of photo albums.
 * @author oscar
 * @author danny
 */
public class photoAlbumList{

    private SimpleStringProperty AlbumName,EarliestPhotoDate, LatestPhotoDate;
    private SimpleIntegerProperty NumberOfPhotos;
    private ObservableList<photoAlbumList> photoAlbum;

    private ObservableList<String> allTags = FXCollections.observableArrayList();

// -------------------------------------------------------------------------------------
     
    /**
     * Constructor initializes the photo album with a given list of photo albums.
     * @param photoAlbum The list of photo albums to initialize with.
     */
    public photoAlbumList(ObservableList<photoAlbumList> photoAlbum){
        this.photoAlbum = photoAlbum;
    }

// -------------------------------------------------------------------------------------

    /**
     * Constructor that initializes the photo album
     * @param name The name of the photo album.
     * @param photoNum The number of photos in the photo album.
     * @param lowestDate The earliest date of a photo in the photo album.
     * @param highDate The latest date of a photo in the photo album.
     */
    public photoAlbumList(String name, int photoNum, String lowestDate, String highDate){
        this.AlbumName = new SimpleStringProperty(name);
        this.NumberOfPhotos = new SimpleIntegerProperty(photoNum);
        this.EarliestPhotoDate = new SimpleStringProperty(lowestDate);
        this.LatestPhotoDate = new SimpleStringProperty(highDate);
    }

// -------------------------------------------------------------------------------------

    /**
     * name of the photo album. null param, it returns "null".
     * @return The name of the photo album.
     */
    public String getName(){
        if (AlbumName != null)
            return AlbumName.get();
        return "null";
    }

// -------------------------------------------------------------------------------------

     /**
     * @return The number of photos in the photo album.
     */
    public int getPhotoNum(){
        if(NumberOfPhotos != null)
            return NumberOfPhotos.get();
        return 0;
    }

// -------------------------------------------------------------------------------------

    /**
     * @return The earliest date of a photo in the photo album.
     *  If the date is null, it returns "n/a".
     */
    public String getLowestDate(){
        if(EarliestPhotoDate != null)
            return EarliestPhotoDate.get();
        return "n/a";
    }

// -------------------------------------------------------------------------------------

    /**
     * @return The latest date of a photo in the photo album.
     * If the date is null, it returns "n/a".
     */
    public String getHighestDate(){
        if(LatestPhotoDate != null)
            return LatestPhotoDate.get();
        return "n/a";
    }

// -------------------------------------------------------------------------------------

    /**
     * @param input The name to set for the photo album.
     */
    public void setName(String input){
         this.AlbumName.set(input);
    }

// -------------------------------------------------------------------------------------

     /**
     * @param input The number to set for the photo album.
     */
    public void setPhotoNum(int input){
        this.NumberOfPhotos.set(input);
    }

// -------------------------------------------------------------------------------------

    /**
     * @param input The date to set as the earliest date of a photo in the photo album.
     */
    public void setLowestDate(String input){
        this.EarliestPhotoDate.set(input);
    }

// -------------------------------------------------------------------------------------

    /**
     * @param input The date to set as the latest date of a photo in the photo album.
     */
    public void setHighestDate(String input){
        this.LatestPhotoDate.set(input);
    }

// -------------------------------------------------------------------------------------

    /**
     * @return the PhotoAlbum list associated with a user
     */
    public ObservableList<photoAlbumList> getAlbumList(){
        return photoAlbum;
    }

// -------------------------------------------------------------------------------------

    /**
     * @return An ObservableList of tags.
     */
    public ObservableList<String> getTag(){
        return allTags;
    }

// -------------------------------------------------------------------------------------

    /**
     * @param data The ObservableList of tags to set for the photo album.
     */
    public void setTagList(ObservableList<String> data){
        allTags = data;
    }
}