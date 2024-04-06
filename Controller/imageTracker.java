package Controller;
import javafx.scene.image.Image;

public class imageTracker {

        private Image selectedImage, copyImage, moveImage;
        private static imageTracker instance;
        protected boolean check, move;


    public static imageTracker getInstance(){
        if (instance == null) {
            instance = new imageTracker();
        }
        return instance;
    }

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

    public void setSaveCopyImage(Image selectedImage) {
        this.copyImage = selectedImage;
    }

    public Image getSaveCopyImage(){
        return copyImage;
    }

    public void setMoveImage(Image selectedImage) {
        this.moveImage = selectedImage;
    }

    public Image getMoveImage(){
        return moveImage;
    }

}


