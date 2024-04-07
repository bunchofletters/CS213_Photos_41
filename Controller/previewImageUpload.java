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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class previewImageUpload {

    linkerClass link = linkerClass.getInstance();
    private userPage user = userPage.getInstance();
    private imageTracker track = imageTracker.getInstance();

    @FXML private Button AddCaptionButton;
    @FXML private Button AddTagButton;
    @FXML private Button CloseButton;
    @FXML private Button SaveButton;
    @FXML private Button AddValueButton;
    @FXML private Button DeleteButton;

    @FXML private Label CurrentCaptionLabel;

    @FXML private ImageView imagePreviewer;

    @FXML private TableColumn<tagsAndValue, String> TagColum;
    @FXML private TableColumn<tagsAndValue, String> ValueColumn;
    @FXML private TableView<tagsAndValue> Table;

    @FXML private Pane pane;

    ObservableList<tagsAndValue> selectedTagsList = FXCollections.observableArrayList();

// -------------------------------------------------------------------------------------

    @FXML
    void save() { //Confirm that tag=value get send correctly
        System.out.println("Preview:" + track.getUplaodImage().getImage());
        boolean run = false;
        for(int i = 0; i<selectedTagsList.size(); i++){
            if(ValueColumn.getCellData(i).equals("")){
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
            for(int i = 0; i<selectedTagsList.size(); i++){
                imgAttr.addTag(selectedTagsList.get(i).getTag()+"="+selectedTagsList.get(i).getValue());
            }
            user.updateUserAlbum();
            track.setUplaodImage(imgAttr);
            Stage stage = (Stage) SaveButton.getScene().getWindow();
            track.setClosed(true);
            stage.close();
        }
    }

    imageAttributes imgAttr;
// -------------------------------------------------------------------------------------

    public void initialize() {

    imagePreviewer.setImage(track.getUplaodImage().getImage());
    imgAttr = new imageAttributes(track.getUplaodImage().getImage());
    System.out.println("Preview INTIZ: " + track.getUplaodImage().getImage());

    if(track.getSelectedTagList() != null){
        for (int i = 0; i < track.tagSelectedListSize(); i++){
            if(track.getSelectedTagList().get(i) != null){
                tagsAndValue adding = new tagsAndValue(track.getSelectedTagList().get(i), null);
                selectedTagsList.add(adding);
            }
        }
    }
    
    TagColum.setCellValueFactory(new PropertyValueFactory<tagsAndValue, String>("tags"));
    ValueColumn.setCellValueFactory(new PropertyValueFactory<tagsAndValue, String>("value"));

    TagColum.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getTag()));
    ValueColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getValue()));

    Table.getColumns().forEach(e -> e.setReorderable(false));
    Table.setItems(selectedTagsList);
    }

// -------------------------------------------------------------------------------------


    @FXML void addCaption(ActionEvent event) {
        TextInputDialog td = new TextInputDialog();
        td.setTitle("Captions");
        td.setContentText("Please type enter a Caption: ");
        Optional<String> result = td.showAndWait();
        if (result.isPresent()){
            if(result.get().equals("")){
                CurrentCaptionLabel.setText("Untitled");
                imgAttr.setCaption("Untitled");
            }
            else if(result.get().substring(0,1).equals(" ")){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setHeaderText("A Warning has Occured");
                alert.setTitle("Warning");
                alert.setContentText("You have started the context with 'space'. This is not allow. Setting context back to default:[Untitled]");
                CurrentCaptionLabel.setText("Untitled");
                imgAttr.setCaption("Untitled");
            }
            else{
                CurrentCaptionLabel.setText(result.get());
                imgAttr.setCaption(result.get());
            }

        }

        // still need to implement adding the caption to the actual photo, it should do a method call to imageTracker or something so it stores the caption with the image so if its abanoned it doesnt save so eveything should go to save
        
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
            secondPopUp.setTitle("Preview Image Upload");
            secondPopUp.initModality(Modality.APPLICATION_MODAL); 
            secondPopUp.setScene(scene);
            secondPopUp.setResizable(false);

            

            secondPopUp.setOnHidden(e -> {
                if(track.getMoveTag()!= null && !track.getMoveTag().equals("")){
                    tagsAndValue x = new tagsAndValue(track.getMoveTag(), "");
                    selectedTagsList.add(x);
                    Table.setItems(selectedTagsList);
                }
            });
            secondPopUp.showAndWait();            
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

// -------------------------------------------------------------------------------------

    @FXML void close(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        track.setClosed(false);
        stage.close();
    }

// -------------------------------------------------------------------------------------


    @FXML void addValue(ActionEvent event) {
        int index = Table.getSelectionModel().getSelectedIndex();
        TextInputDialog td = new TextInputDialog();
        td.setContentText("Input Tag Value");
        td.setTitle("Tag Value");
        Optional<String> result = td.showAndWait();
        if(result.isPresent()){
            if(!result.get().equals("") && !result.get().substring(0,1).equals(" ")){
                boolean run = false;
                for(int i = 0; i<selectedTagsList.size(); i++){
                    if(selectedTagsList.get(i).getTag().equals(TagColum.getCellData(index))){
                        if(result.get().equals(ValueColumn.getCellData(index))){
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
                    Table.refresh();
                }
            }
            else if(result.get().substring(0,1).equals(" ")){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("An Warning has Occured");
                alert.setContentText("Attempting to set [value] with a value that starts with 'space'. S    tart with a number or letter instead");
                alert.showAndWait();
            }
        }
    }

// -------------------------------------------------------------------------------------
    @FXML void deleteTagValue(){
        int index = Table.getSelectionModel().getSelectedIndex();
        selectedTagsList.remove(index);
        Table.refresh();
    }

}