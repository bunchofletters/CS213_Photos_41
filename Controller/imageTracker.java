package Controller;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class imageTracker {

        private Image selectedImage, copyImage, moveImage, stockImage;
        private static imageTracker instance;
        protected boolean check, move, stockImageBoolean;
        protected ObservableList<String> allTags;
        private String tags;

    public static imageTracker getInstance(){
        if (instance == null) {
            instance = new imageTracker();
        }
        return instance;
    }

 // -------------------------------------------------------------

    public imageTracker(){
        this.allTags = FXCollections.observableArrayList();
    }

  // -------------------------------------------------------------

    public Image getSelectedImage() {
        return selectedImage;
    }

    /**
     * 
     * @param selectedImage passes through the image that the user is currently selecting
     */
    public void setSelectedImage(Image selectedImage) {
        this.selectedImage = selectedImage;
    }

  // -------------------------------------------------------------

    public void setSaveCopyImage(Image selectedImage) {
        this.copyImage = selectedImage;
    }

    public Image getSaveCopyImage(){
        return copyImage;
    }

  // -------------------------------------------------------------

    public void setMoveImage(Image selectedImage) {
        this.moveImage = selectedImage;
    }

    public Image getMoveImage(){
        return moveImage;
    }

// -------------------------------------------------------------

    public void setStockImage(Image stockImage){
        this.stockImage = stockImage;
    }

    public Image getStockImage(){
        return stockImage;
    }

    public void turnOnStockImage(){
        stockImageBoolean = true;
    }

    public void turnOffStockImage(){
        stockImageBoolean = false;
    }
// -------------------------------------------------------------

    public void addTagToList(String tag){
        allTags.add(tag);
    }

    public int tagListSize(){
        return allTags.size();
    }

    public ObservableList<String> getTagList(){
        return allTags;
    }

// -------------------------------------------------------------


    public void setMoveTag(String tag){
        this.tags = tag;
    }

    public String getMoveTag(){
        return tags;
    }

}


