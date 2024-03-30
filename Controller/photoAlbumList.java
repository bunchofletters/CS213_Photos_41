package Controller;

public class photoAlbumList {
    String name;
    int photoNum, lowestDate, highDate;
    
    public photoAlbumList(String name, int photoNum, int lowestDate, int highDate){
        this.name = name;
        this.photoNum = photoNum;
        this.lowestDate = lowestDate;
        this.highDate = highDate;
    }
    public String getName(){
        return name;
    }
    public int getPhotoNum(){
        return photoNum;
    }
    public int getLowestDate(){
        return lowestDate;
    }
    public int getHighestDate(){
        return highDate;
    }
    public void setName(String input){
         this.name = input;
    }
    public void setPhotoNum(int input){
        this.photoNum = input;
    }
    public void setLowestDate(int input){
        this.lowestDate = input;
    }
    public void setHighestDate(int input){
        this.highDate = input;
    }
}
