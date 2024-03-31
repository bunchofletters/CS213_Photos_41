import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * This class handles all function the admin will be able to do which
 * include deleting, creating, and viewing created users
 * @author Oscar ol78
 */
public class admin {
    /**
     * This creates a static class of login so that the List of User in
     * login is accessiable
     * @see login
     */
    private static login login;

    @FXML
    /**
     * This will allow both the creation and deletion of users for the admin
     * based on the input
     */
    private TextField Createuser;
    
    @FXML
    /**
     * This is the list of user: This is use to display the list of user to the admin
     * it will also allow the user to select and delete from the list instead of 
     * typing in the user's name
     */
    private ListView<String> userList;

    @FXML
    /**
     * Scene change back to the login screen
     */
    void backToLogin() {
        Photo x = new Photo();
        x.changeScene("login.fxml");
    }
    
    /**
     * On start allows the list of user to be display instead of waiting for some input
     * @see #listUser
     */
    public void initialize(){
        listUser();
    }

    @FXML
    /**
     * This will method creates a new user profile
     * The created profile is not case sensitive
     * Each user are require to have a unique username
     */
    void createUser() {
        if(!login.getList().contains(Createuser.getText().toLowerCase()) && !Createuser.getText().toLowerCase().equals("admin") && !Createuser.getText().toLowerCase().equals("stock") && !Createuser.getText().toLowerCase().equals("") && !Createuser.getText().toLowerCase().substring(0,1).equals(" ")){
            login.getList().add(Createuser.getText().toLowerCase());
        }
        Createuser.clear();
        listUser();
    }

    @FXML
    /**
     * This method will remove a user profile from typing in the user's name
     */
    void deleteUser() {
        for(int i =0; i<login.getList().size(); i++){
            if(login.getList().get(i).equals(Createuser.getText().toLowerCase())){
                login.getList().remove(i);
            }
        }
        Createuser.clear();
        listUser();

    }

    @FXML
    /**
     * This method will remove a user profile by selecting the user from the userList
     */
    void deleteUser2() {
        if(userList.getSelectionModel().getSelectedIndex() > -1){
            login.getList().remove(userList.getSelectionModel().getSelectedIndex());
        }
        listUser();
    }

    @FXML
    /**
     * This method atomatically list all created user profile names to the admin
     */
    private void listUser() {
        userList.getItems().clear();
        userList.getItems().addAll(login.getList());
    }

    /**
     * For composition
     * @param x this is the Login Controller
     */
    public void setLogin(login x){
        login = x;
    }

}
