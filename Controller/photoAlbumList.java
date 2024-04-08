package Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 * 
 * @author oscar
 * @author danny
 */
public class photoAlbumList{

    private SimpleStringProperty AlbumName,EarliestPhotoDate, LatestPhotoDate;
    private SimpleIntegerProperty NumberOfPhotos;
    private ObservableList<photoAlbumList> photoAlbum;


    public photoAlbumList(ObservableList<photoAlbumList> photoAlbum){
        this.photoAlbum = photoAlbum;
    }

    public photoAlbumList(String name, int photoNum, String lowestDate, String highDate){
        this.AlbumName = new SimpleStringProperty(name);
        this.NumberOfPhotos = new SimpleIntegerProperty(photoNum);
        this.EarliestPhotoDate = new SimpleStringProperty(lowestDate);
        this.LatestPhotoDate = new SimpleStringProperty(highDate);
    }

    public String getName(){
        if (AlbumName != null)
            return AlbumName.get();
        return "null";
    }
    public int getPhotoNum(){
        if(NumberOfPhotos != null)
            return NumberOfPhotos.get();
        return 0;
    }
    public String getLowestDate(){
        if(EarliestPhotoDate != null)
            return EarliestPhotoDate.get();
        return "n/a";
    }
    public String getHighestDate(){
        if(LatestPhotoDate != null)
            return LatestPhotoDate.get();
        return "n/a";
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