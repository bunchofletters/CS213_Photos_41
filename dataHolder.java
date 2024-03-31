import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class dataHolder implements Serializable{
    login login = new login();

    private static ObservableList<String> users = FXCollections.observableArrayList();


    public ObservableList<String> getUsers(){
        return users;
    }

    /**
     * Save all data that will be need for the next running of photo program
     */
    public void saveData(){
        users = login.getList();
    }
}
