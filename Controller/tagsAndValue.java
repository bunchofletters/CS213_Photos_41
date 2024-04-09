package Controller;

import javafx.beans.property.SimpleStringProperty;

public class tagsAndValue {
    
    private SimpleStringProperty tags, value;

    public tagsAndValue(String tag, String values){
        tags = new SimpleStringProperty();
        value = new SimpleStringProperty();
        tags.set(tag);
        value.set(values);
    }

    public void setTag(String stuff){
        tags.set(stuff);
    }

    public void setValue(String stuff){
        value.set(stuff);
    }

    public String getTag(){
        if(tags != null)
            return tags.get();
        return null;
    }
    public String getValue(){
        if(value != null)
            return value.get();
        return null;
    }

}
