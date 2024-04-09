package Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class imageTracker {

        private imageAttributes stockImage, selectedImage, copyImage, moveImage, uplaodImage, searchSelectImage;
        private static imageTracker instance;
        protected boolean check, stockImageBoolean;
        protected boolean move = false;
        private String tags;
        private boolean closed = false;
        private int lastSelectedIndex = 0;

    public static imageTracker getInstance(){
        if (instance == null) {
            instance = new imageTracker();
        }
        return instance;
    }

    private ObservableList<String> selectedTagsOnlyList = FXCollections.observableArrayList();

  // -------------------------------------------------------------
   
    /**
    *
    * @return The selected image
    */
    public imageAttributes getSelectedImage() {
        return selectedImage;
    }


    /**
    *
    * @param selectedImage sets the selected image image that the user is currently selecting
    */
    public void setSelectedImage(imageAttributes selectedImage) {
        this.selectedImage = selectedImage;
    }

  // -------------------------------------------------------------

    /**
    * 
    * @param selectedImage The image to be saved as a copy.]
    */
    public void setSaveCopyImage(imageAttributes selectedImage) {
        this.copyImage = selectedImage;
        if(selectedImage != null)
            System.out.println(selectedImage.getURL());
    }

    /**
    * @return The saved copy of the image
    */
    public imageAttributes getSaveCopyImage(){
        return copyImage;
    }

  // -------------------------------------------------------------

    /**
    * 
    * @param selectedImage sets the image to be moved
    */
    public void setMoveImage(imageAttributes selectedImage) {
        this.moveImage = selectedImage;
    }

    /**
    *
    * @return The image to be moved
     */
    public imageAttributes getMoveImage(){
        return moveImage;
    }

// -------------------------------------------------------------

    /**
    *
    * @param stockImage sets the stock image
    */
    public void setStockImage(imageAttributes stockImage){
        this.stockImage = stockImage;
    }

// -------------------------------------------------------------

    /**
    *
    * @return the stock image
    */
    public imageAttributes getStockImage(){
        return stockImage;
    }

// -------------------------------------------------------------

    /**
    *
    * @param tag set the tag to be moved
    */
    public void setMoveTag(String tag){
        this.tags = tag;
    }

// -------------------------------------------------------------

    /**
    *
    * @return The tag to be moved
    */
    public String getMoveTag(){
        return tags;
    }

// -------------------------------------------------------------

    /**
     * @param tag The tag to add to the list
     */
    public void addSelectedTagToList(String tag){
        selectedTagsOnlyList.add(tag);
    }

    /**
     * @return The size of the selected tag list
     */
    public int tagSelectedListSize(){
        return selectedTagsOnlyList.size();
    }

     /**
     * @return An ObservableList of selected tags
     */
    public ObservableList<String> getSelectedTagList(){
        return selectedTagsOnlyList;
    }

// -------------------------------------------------------------

    /**
     * @return The uploaded image
     */
    public imageAttributes getUplaodImage() {
        return uplaodImage;
    }

    /**
     * @param selectedImage The image to set as the uploaded image
     */
    public void setUplaodImage(imageAttributes selectedImage) {
        this.uplaodImage = selectedImage;
    }

// -------------------------------------------------------------
    
     /**
     * @return The selected image from the search
     */
    public imageAttributes getSearchSelectedImage() {
        return searchSelectImage;
    }

// -------------------------------------------------------------
   
     /**
     * @param selectedImage the image to set as the selected image from the search
     */
    public void setSearchSelectedImage(imageAttributes selectedImage) {
        this.searchSelectImage = selectedImage;
    }

    /**
     * @return the closed status
     */
    public boolean getclosed(){
        return closed;
    }

     /**
     * @param x the status to set as the closed status
     */
    public void setClosed(boolean x){
        closed = x;
    }
    
    /**
     * @return the last selected index
     */
    public int getLastSelectIndex(){
        return lastSelectedIndex;
    }

    /**
     * @param set the index to set as the last selected index
     */
    public void setLastSelectedIndex(int set){
        lastSelectedIndex = set;
    }

}




