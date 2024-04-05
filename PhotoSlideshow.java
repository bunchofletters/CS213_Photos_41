import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class PhotoSlideshow {

    private Photo photo = Photo.getInstance();
    linkerClass link = linkerClass.getInstance();
    private userPage user = userPage.getInstance();

    @FXML private Button ExitButton;

    @FXML private ImageView PictureView;

    @FXML private Pagination pagination;

// -------------------------------------------------------------------------------------

    @FXML void exit(ActionEvent event) {
        photo.changeScene("insidePhotoAlbum.fxml");
    }

// -------------------------------------------------------------------------------------

    @FXML void picture(MouseEvent event) {
        
    }

// -------------------------------------------------------------------------------------

    public void initialize(){
        ObservableList<Image> images = link.getImageList(user.getAlbum()).getPhotos();

        pagination.setPageCount(images.size());

        pagination.setPageFactory((pageIndex) -> {
            ImageView PictureView = new ImageView(images.get(pageIndex));
            PictureView.setFitWidth(400);  // Set the width of the ImageView
            PictureView.setFitHeight(400);  
            PictureView.setPreserveRatio(true); 
            return PictureView;
        });
    }

}

