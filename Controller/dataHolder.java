package Controller;
import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * @author oscar ol78
 */
public class dataHolder implements Serializable{
    login Login;

    private static dataHolder instance;
    
    // Obser ArrayList of Strings of Users
    private static ObservableList<String> users = FXCollections.observableArrayList();

    /**
     * Creates a single instance of dataHolder
     * @return the single instance of dataHolder
     */
    public static dataHolder getInstance() {
        if (instance == null) {
            instance = new dataHolder();
        }
        return instance;
    }

    public ObservableList<String> getUsers(){
        return users;
    }

    /**
     * Save all data that will be need for the next running of photo program
     */
    public void saveData(){
        Login = login.getInstance();
        users = Login.getList();
    }
}
