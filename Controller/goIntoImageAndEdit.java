package Controller;
import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class goIntoImageAndEdit {
    
    linkerClass link = linkerClass.getInstance();
    private userPage user = userPage.getInstance();
    private imageTracker track = imageTracker.getInstance();

    @FXML private Label CaptionName;
    @FXML private Label DateLabel;

    @FXML private Button CloseButton;
    @FXML private Button DeleteTag;
    @FXML private Button SaveButton;
    @FXML private Button changeValueButton;
    @FXML private Button AddTagButton;
    

    @FXML private ImageView ImageViewer;

    @FXML private TableColumn<tagsAndValue, String> TagColumn;
    @FXML private TableColumn<tagsAndValue, String> ValueColum;
    @FXML private TableView<tagsAndValue> infoTable;

    ObservableList<tagsAndValue> selectedTagsList = FXCollections.observableArrayList();

    imageAttributes attribute;

// -------------------------------------------------------------------------------------

    @FXML void changeValue(ActionEvent event) {
        int index = infoTable.getSelectionModel().getSelectedIndex();
        if(index < 0){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Attempting to Add Value Without Selecting a Tag. Try Selecting a Tag and Clicking Add Value");
            alert.setHeaderText("An Error Occured");
            alert.showAndWait();
        }
        else{
            TextInputDialog td = new TextInputDialog();
            td.setContentText("Input Tag Value");
            td.setTitle("Tag Value");
            Optional<String> result = td.showAndWait();
            if(result.isPresent()){
                if(!result.get().equals("") && !result.get().contains(" ")){
                    boolean run = false;
                    for(int i = 0; i<selectedTagsList.size(); i++){
                        if(selectedTagsList.get(i).getTag().equals(TagColumn.getCellData(index))){
                            if(result.get().equals(ValueColum.getCellData(index))){
                                Alert alert = new Alert(AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("There was an Error");
                                alert.setContentText("Attempting to have multiple [tag] with the same [value]");
                                alert.showAndWait();
                                run = true;
                                break;
                            }
                        }
                    }
                    if(!run){
                        selectedTagsList.get(index).setValue(result.get());
                        infoTable.refresh();
                    }
                }
                else if(result.get().contains(" ")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("An Error has Occured When Attempting to Add Value to a Tag");
                    alert.setContentText("Values can't have any spaces in them");
                    alert.showAndWait();
                }
                else if(result.get().contains("=")){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("An Error has Occured When Attempting to Add Value to a Tag");
                    alert.setContentText("Values can't have any = in them");
                    alert.showAndWait();
                }
            }
        }
    }

// -------------------------------------------------------------------------------------

    @FXML void close(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

// -------------------------------------------------------------------------------------

    @FXML void delTag(ActionEvent event) {
        int index = infoTable.getSelectionModel().getSelectedIndex();
        if(index < 0){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Attempting to Delete Without Selecting a Tag. Try Selecting a Tag and Clicking Delete");
            alert.setHeaderText("An Error Occured");
            alert.showAndWait();
        }
        else{
            selectedTagsList.remove(index);
            infoTable.refresh();
        }
    }

// -------------------------------------------------------------------------------------

    @FXML void save() {
    if(CaptionName != null){
        attribute.setCaption(CaptionName.getText());
    }
    boolean run = false;
        for(int i = 0; i<selectedTagsList.size(); i++){
            if(ValueColum.getCellData(i).equals("")){
                run = true;
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("An Error has Occured");
                alert.setContentText("You Have [Tags] with no [Value] either remove them or add a value to them");
                alert.showAndWait();
            }
        }
        if(!run){
            //add tags to the imageAttribute
            attribute.getTags().clear();
            for(int i = 0; i<selectedTagsList.size(); i++){
                attribute.addTag(selectedTagsList.get(i).getTag()+"="+selectedTagsList.get(i).getValue());
            }
            user.updateUserAlbum();
            track.setSelectedImage(attribute);

            Stage stage = (Stage) SaveButton.getScene().getWindow();
            stage.close();    
        }
    }

// -------------------------------------------------------------------------------------

    public void initialize() {

        ImageViewer.setImage(track.getSelectedImage().getImage());
        attribute = link.getImageList(user.getAlbum()).getPhotos().get(track.getLastSelectIndex());

        TagColumn.setCellValueFactory(new PropertyValueFactory<tagsAndValue, String>("tags"));
        ValueColum.setCellValueFactory(new PropertyValueFactory<tagsAndValue, String>("value"));
        TagColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getTag()));
        ValueColum.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getValue()));
        infoTable.getColumns().forEach(e -> e.setReorderable(false));

        // ObservableList<tagsAndValue> tagValue = FXCollections.observableArrayList();
        for(int i = 0; i< track.getSelectedImage().getTags().size(); i++){
            tagsAndValue tmp = new tagsAndValue(attribute.getTags().get(i).substring(0, attribute.getTags().get(i).indexOf("=")), attribute.getTags().get(i).substring(attribute.getTags().get(i).indexOf("=")+1));
            selectedTagsList.add(tmp);
        }
        infoTable.setItems(selectedTagsList);
        CaptionName.setText(attribute.getCaption());
        DateLabel.setText(attribute.getUploadDate());
    }

// -------------------------------------------------------------------------------------
    private Stage secondPopUp;
    @FXML void addTag(ActionEvent event) {  
        try {
            if (secondPopUp != null && secondPopUp.isShowing()) {
                secondPopUp.toFront();
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/tagViewPopup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            secondPopUp = new Stage();
            secondPopUp.initModality(Modality.APPLICATION_MODAL); 
            secondPopUp.setScene(scene);
            secondPopUp.setResizable(false);

            secondPopUp.setOnHidden(e -> {
                if(track.getMoveTag()!= null && !track.getMoveTag().equals("")){
                    tagsAndValue x = new tagsAndValue(track.getMoveTag(), "");
                    selectedTagsList.add(x);
                    infoTable.setItems(selectedTagsList);
                }
            });
            secondPopUp.showAndWait();;
                    
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changeCaption() {
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Captions");
        td.setContentText("Please type enter a Caption: ");
        Optional<String> result = td.showAndWait();
        if (result.isPresent()){
            if(result.get().equals("")){
                CaptionName.setText("Untitled");
                attribute.setCaption("Untitled");
            }
            else if(result.get().substring(0,1).equals(" ")){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setHeaderText("A Warning has Occured");
                alert.setTitle("Warning");
                alert.setContentText("You have started the context with 'space'. This is not allow. Setting context back to default:[Untitled]");
                CaptionName.setText("Untitled");
                attribute.setCaption("Untitled");
            }
            else if(result.get().contains("=")){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("A Error has Occured");
                alert.setTitle("Error");
                alert.setContentText("You have attempted to add '=' to your caption. Try another caption without '='");
            }
            else if(result.get().contains("/")){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("A Error has Occured");
                alert.setTitle("Error");
                alert.setContentText("You have attempted to add '/' to your caption. Try another caption without '/'");
            }
            else{
                CaptionName.setText(result.get());
                attribute.setCaption(result.get());
            }

        }
    }


}
