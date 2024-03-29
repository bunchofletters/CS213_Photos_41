package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class photo extends Application{

    Stage mainStage;

    @Override
    public void start(Stage primStage) throws Exception{
        mainStage = primStage;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/login.fxml"));
            Pane root = loader.load();
            Scene scene = new Scene(root);

            mainStage.setScene(scene);
            mainStage.setTitle("Login");
            mainStage.setResizable(false);
            mainStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
