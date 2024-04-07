package Controller.Data_seralization;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 * This class will hold all the attribute of the image such as:
 * image, tags, captions, and uploadDate
 * @author danny
 * @author oscar
 */
public class imageAttributes_serializable implements Serializable{

    private String captions = "";
    private ArrayList<String> tags = new ArrayList<>();
    transient private Image image;
    private String url="";
    private LocalDate uploadDate;


    public imageAttributes_serializable(Image image){
        this.image = image;
        url = image.getUrl();
        System.out.println(uploadDate);
        uploadDate = LocalDate.now();
    }

    public String getUrl() {
        return url;
    }
    

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void setTag(ArrayList<String> tags){
        this.tags = tags;
    }

    public void setUploadDate(LocalDate date){
        uploadDate = date;
    }

    public void setCaption(String caption) {
        this.captions = caption;
    }

    public Image getImage(){
        return image;
    }
    
    public String getUploadDate(){
        return uploadDate.toString();
    }

    public LocalDate getUploadDateAsDate(){
        return uploadDate;
    }

    public ArrayList<String> getTags(){
        return tags;
    }

    public String getCaption(){
        return captions;
    }


    
}
