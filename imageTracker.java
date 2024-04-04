import javafx.scene.image.Image;

public class imageTracker {

        private Image selectedImage;
        private static imageTracker instance;

    public static imageTracker getInstance(){
        if (instance == null) {
            instance = new imageTracker();
        }
        return instance;
    }

        public Image getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(Image selectedImage) {
        this.selectedImage = selectedImage;
    }
}
