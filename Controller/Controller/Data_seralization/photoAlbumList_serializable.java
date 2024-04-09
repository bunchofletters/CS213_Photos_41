package Controller.Data_seralization;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author oscar
 * @author danny
 */
public class photoAlbumList_serializable implements Serializable{

    private String AlbumName = "",EarliestPhotoDate ="n/a", LatestPhotoDate="n/a";
    private int NumberOfPhotos = 0;
    private ArrayList<photoAlbumList_serializable> photoAlbum = new ArrayList<>();

    private ArrayList<String> allTags = new ArrayList<>();


    public photoAlbumList_serializable(ArrayList<photoAlbumList_serializable> photoAlbum){
        this.photoAlbum = photoAlbum;
    }

    public photoAlbumList_serializable(String name, int photoNum, String lowestDate, String highDate){
        this.AlbumName = name;
        this.NumberOfPhotos = photoNum;
        this.EarliestPhotoDate = lowestDate;
        this.LatestPhotoDate = highDate;
    }

    public String getName(){
        return AlbumName;
    }
    public int getPhotoNum(){
        return NumberOfPhotos;
    }
    public String getLowestDate(){
        return EarliestPhotoDate;
    }
    public String getHighestDate(){
        return LatestPhotoDate;
    }
    public void setName(String input){
         this.AlbumName = input;
    }
    public void setPhotoNum(int input){
        this.NumberOfPhotos = input;
    }
    public void setLowestDate(String input){
        this.EarliestPhotoDate = input;
    }
    public void setHighestDate(String input){
        this.LatestPhotoDate = input;
    }

    /**
     * @return the PhotoAlbum list associated with a user
     */
    public ArrayList<photoAlbumList_serializable> getAlbumList(){
        return photoAlbum;
    }

    public ArrayList<String> getTag(){
        return allTags;
    }

    public void setTagList(ArrayList<String> data){
        allTags = data;
    }
}