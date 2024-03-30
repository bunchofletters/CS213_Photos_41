package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class photo extends Application{

    static Stage mainStage;

    @Override
    public void start(Stage primStage) throws Exception{
        mainStage = primStage;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            mainStage.setScene(scene);
            mainStage.setTitle("Login");
            mainStage.setResizable(false);
            mainStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void changeScene(String resource){
        try{
            Parent x = FXMLLoader.load(getClass().getResource(resource));
            Scene change = new Scene(x);

            mainStage.setScene(change);
            mainStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
