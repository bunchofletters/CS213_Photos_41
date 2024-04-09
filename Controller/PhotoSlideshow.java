package Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PhotoSlideshow {

    private Photo photo = Photo.getInstance();
    linkerClass link = linkerClass.getInstance();
    private userPage user = userPage.getInstance();

    @FXML private Button ExitButton;

    @FXML private ImageView PictureView;

    @FXML private Pagination pagination;

// -------------------------------------------------------------------------------------

    @FXML void exit(ActionEvent event) {
        photo.changeScene("/view/insidePhotoAlbum.fxml");
    }

// -------------------------------------------------------------------------------------

    @FXML void picture(MouseEvent event) {
        
    }

// -------------------------------------------------------------------------------------

    public void initialize(){
        ObservableList<imageAttributes> images = link.getImageList(user.getAlbum()).getPhotos();

        pagination.setPageCount(images.size());

        pagination.setPageFactory((pageIndex) -> {
            ImageView imageView = new ImageView(images.get(pageIndex).getImage());
            imageView.setFitWidth(450);  
            imageView.setPreserveRatio(true); 
            return imageView;
        });
    }

}

