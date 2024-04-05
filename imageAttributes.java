import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class imageAttributes {

    private SimpleStringProperty captions;
    private ObservableList<String> tags;
    
    

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void setCaption(String caption) {
        this.captions.set(caption);
    }


    
}
