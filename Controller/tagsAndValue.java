package Controller;

import javafx.beans.property.SimpleStringProperty;
/**
 * Class of tag and its associated value
 * @author Danny
 * @author Oscar
 */
public class tagsAndValue {
    
    private SimpleStringProperty tags, value;

// -------------------------------------------------------------------------------------

    /**
     * Constructor that initializes the tag and value
     * @param tag The tag to initialize with
     * @param values The value to initialize with
     */
    public tagsAndValue(String tag, String values){
        tags = new SimpleStringProperty();
        value = new SimpleStringProperty();
        tags.set(tag);
        value.set(values);
    }

// -------------------------------------------------------------------------------------

    /**
     * @param stuff The tag to set
     */
    public void setTag(String stuff){
        tags.set(stuff);
    }

// -------------------------------------------------------------------------------------

     /**
     * @param stuff The value to set
     */
    public void setValue(String stuff){
        value.set(stuff);
    }

// -------------------------------------------------------------------------------------

     /**
     * @return The tag
     * if the tag is null, it returns null
     */
    public String getTag(){
        if(tags != null)
            return tags.get();
        return null;
    }

// -------------------------------------------------------------------------------------

    /**
     * @return The value.
     * if the value is null, it returns null.
     */
    public String getValue(){
        if(value != null)
            return value.get();
        return null;
    }


}
