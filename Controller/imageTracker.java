package Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class imageTracker {

        private imageAttributes stockImage, selectedImage, copyImage, moveImage, uplaodImage;
        private static imageTracker instance;
        protected boolean check, move, stockImageBoolean;
        protected ObservableList<String> allTags;
        private String tags;
        private boolean closed = false;

    public static imageTracker getInstance(){
        if (instance == null) {
            instance = new imageTracker();
        }
        return instance;
    }

    private ObservableList<String> selectedTagsOnlyList = FXCollections.observableArrayList();

 // -------------------------------------------------------------

    /**
    * This constructor initializes an imageTracker object.
    * It also sets up an observable list for all tags.
    */
    public imageTracker(){
        this.allTags = FXCollections.observableArrayList();
    }

  // -------------------------------------------------------------
   
    /**
    * This method returns the selected image.
    *
    * @return The selected image.
    */
    public imageAttributes getSelectedImage() {
        return selectedImage;
    }


    /**
    * This method sets the selected image.
    *
    * @param selectedImage The image that the user is currently selecting.
    */
    public void setSelectedImage(imageAttributes selectedImage) {
        this.selectedImage = selectedImage;
    }

  // -------------------------------------------------------------

    /**
    * This method sets the saved copy of the image.
    *
    * @param selectedImage The image to be saved as a copy.
    */
    public void setSaveCopyImage(imageAttributes selectedImage) {
        this.copyImage = selectedImage;
        System.out.println(selectedImage.getURL());
    }

    /**
    * This method returns the saved copy of the image.
    *
    * @return The saved copy of the image.
    */
    public imageAttributes getSaveCopyImage(){
        return copyImage;
    }

  // -------------------------------------------------------------

    /**
    * This method sets the image to be moved.
    *
    * @param selectedImage The image to be moved.
    */
    public void setMoveImage(imageAttributes selectedImage) {
        this.moveImage = selectedImage;
    }

    /**
    * This method returns the image to be moved.
    *
    * @return The image to be moved.
     */
    public imageAttributes getMoveImage(){
        return moveImage;
    }

// -------------------------------------------------------------

    /**
    * This method sets the stock image.
    *
    * @param stockImage The stock image.
    */
    public void setStockImage(imageAttributes stockImage){
        this.stockImage = stockImage;
    }

    /**
    * This method returns the stock image.
    *
    * @return The stock image.
    */
    public imageAttributes getStockImage(){
        return stockImage;
    }
// -------------------------------------------------------------

    /**
    * This method adds a tag to the list of all tags.
    *
    * @param tag The tag to be added.
    */
    public void addTagToList(String tag){
        allTags.add(tag);
    }

    /**
    * This method returns the size of the list of all tags.
    *
    * @return The size of the list of all tags.
    */
    public int tagListSize(){
        return allTags.size();
    }

    /**
    * This method returns the list of all tags.
    *
    * @return The list of all tags.
    */
    public ObservableList<String> getTagList(){
        return allTags;
    }

// -------------------------------------------------------------

    /**
    * This method sets the tag to be moved.
    *
    * @param tag The tag to be moved.
    */
    public void setMoveTag(String tag){
        this.tags = tag;
    }

    /**
    * This method returns the tag to be moved.
    *
    * @return The tag to be moved.
    */
    public String getMoveTag(){
        return tags;
    }

// -------------------------------------------------------------


    public void addSelectedTagToList(String tag){
        selectedTagsOnlyList.add(tag);
    }

    
    public int tagSelectedListSize(){
        return selectedTagsOnlyList.size();
    }

   
    public ObservableList<String> getSelectedTagList(){
        return selectedTagsOnlyList;
    }

// -------------------------------------------------------------


    
    public imageAttributes getUplaodImage() {
        return uplaodImage;
    }



    public void setUplaodImage(imageAttributes selectedImage) {
        this.uplaodImage = selectedImage;
    }

    public boolean getclosed(){
        return closed;
    }

    public void setClosed(boolean x){
        closed = x;
    }
}




