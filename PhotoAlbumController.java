import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class PhotoAlbumController implements Initializable{

    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private TilePane tilePane;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (int i = 0; i < 21; i++){
            tilePane.setPrefColumns(3); // Set the number of columns
            tilePane.setHgap(15); // Set horizontal gap
            tilePane.setVgap(10); // Set vertical gap
             
            Label naming = new Label("stuff");
            naming.setAlignment(Pos.CENTER);
            ImageView img = new ImageView("./data/Beach.jpeg");
            img.setFitWidth(250);

            img.setFitHeight(150);
            

            
            VBox boxes = new VBox();
            boxes.setAlignment(Pos.CENTER);
            boxes.getChildren().addAll(img,naming);

            tilePane.getChildren().add(boxes);
            // Button name = new Button("Hello");
            // name.setLayoutX(100);
            // name.setLayoutY(100);
            // tilePane.getChildren().add(name);

            scrollPane.setContent(tilePane);
            scrollPane.setFitToWidth(true); // Fit content to width
        }
    }

}
