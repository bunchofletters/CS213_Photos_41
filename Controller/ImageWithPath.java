package Controller;
import javafx.scene.image.Image;

/**
 * This class represents an image with an associated path.
 * @author Danny 
 */
public class ImageWithPath {

    private Image image;
    private String path;
    
     /**
     * Constructs an ImageWithPath object with the specified image and path.
     *
     * @param image The image to be associated with this object.
     * @param path The path of the image.
     */ 
    public ImageWithPath(Image image, String path) {
        this.image = image;
        this.path = path;
    }

    /**
     * Returns the image associated with this object.
     *
     * @return The image.
     */
    public Image getImage() {
        return image;
    }

     /**
     * Returns the path of the image.
     *
     * @return The path of the image.
     */
    public String getPath() {
        return path;
    }
}

