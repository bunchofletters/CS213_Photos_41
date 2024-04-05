import javafx.scene.image.Image;

public class ImageWithPath {

        private Image image;
        private String path;
    
        public ImageWithPath(Image image, String path) {
            this.image = image;
            this.path = path;
        }
    
        public Image getImage() {
            return image;
        }

    
        public String getPath() {
            return path;
        }
    }

