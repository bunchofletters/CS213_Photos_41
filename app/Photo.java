package app;
import Controller.dataHolder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Photo extends Application{

    private static Photo instance;
    dataHolder data = new dataHolder();

    public static Photo getInstance() {
        if (instance == null) {
            instance = new Photo();
        }
        return instance;
    }

    static Stage mainStage;

    @Override
    public void start(Stage primStage) throws Exception{
        mainStage = primStage;
        try{
            data.loadData();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            mainStage.setScene(scene);
            mainStage.setTitle("Photo Application");
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

    @Override
    public void stop() throws Exception {
        data.saveData();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
