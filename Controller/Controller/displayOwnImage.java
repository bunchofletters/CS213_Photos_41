package Controller;

import app.Photo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class displayOwnImage {

    private Photo photo = Photo.getInstance();
    linkerClass link = linkerClass.getInstance();
    private imageTracker track = imageTracker.getInstance();
    private userPage user = userPage.getInstance();
 

    @FXML private Label CaptionLabel;
    @FXML private Label DateLabel;

    @FXML private Button ReturnButton;

    @FXML private TableColumn<tagsAndValue, String> Tags;
    @FXML private TableColumn<tagsAndValue, String> Values;
    @FXML private TableView<tagsAndValue> Table;

    @FXML private Button logoutButton;

    @FXML private ImageView ImageViewer;

    @FXML
    void logout(ActionEvent event) {
        photo.changeScene("/view/login.fxml");
    }

    @FXML
    void returnButton(ActionEvent event) {
        photo.changeScene("/view/insidePhotoAlbum.fxml");
    }

    public void initialize(){
        ImageViewer.setImage(track.getSelectedImage().getImage());
        imageAttributes attribute = link.getImageList(user.getAlbum()).getPhotos().get(track.getLastSelectIndex());

        Tags.setCellValueFactory(new PropertyValueFactory<tagsAndValue, String>("tags"));
        Values.setCellValueFactory(new PropertyValueFactory<tagsAndValue, String>("value"));
        Tags.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getTag()));
        Values.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getValue()));

        Table.getColumns().forEach(e -> e.setReorderable(false));
        ObservableList<tagsAndValue> forTable = FXCollections.observableArrayList();
        for(int i = 0; i< attribute.getTags().size();i++){
            tagsAndValue tmp = new tagsAndValue(attribute.getTags().get(i).substring(0, attribute.getTags().get(i).indexOf("=")), attribute.getTags().get(i).substring(attribute.getTags().get(i).indexOf("=")+1));
            forTable.add(tmp);
        }
        Table.setItems(forTable);

        CaptionLabel.setText(attribute.getCaption());
        DateLabel.setText(attribute.getUploadDate());
        
    }

}
