package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * This class handles all function the admin will be able to do which
 * include deleting, creating, and viewing created users
 * @author Oscar
 */
public class admin {
    /**
     * This creates a static class of login so that the List of User in
     * login is accessiable
     * @see login
     */
    private login Login = login.getInstance();

    private linkerClass link = linkerClass.getInstance();

    @FXML
    /**
     * This will allow both the creation and deletion of users for the admin
     * based on the input
     */
    private TextField userInput;

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
        Photo x = Photo.getInstance();
        x.changeScene("login.fxml");
    }
    
    /**
     * On start allows the list of user to be display instead of waiting for some input
     * @see #listUser
     */
    public void initialize(){
        System.out.println(Login);
        listUser();
    }

    @FXML
    /**
     * This will method creates a new user profile
     * The created profile is not case sensitive
     * Each user are require to have a unique username
     */
    void createUser() {
        if(!Login.getList().contains(userInput.getText().toLowerCase()) && !userInput.getText().toLowerCase().equals("admin") && !userInput.getText().toLowerCase().equals("stock") && !userInput.getText().toLowerCase().equals("") && !userInput.getText().toLowerCase().substring(0,1).equals(" ")){
            Login.getList().add(userInput.getText().toLowerCase());
            link.setUserAlbum(userInput.getText().toLowerCase());
        }
        userInput.clear();
        listUser();
    }

    @FXML
    /**
     * This method will remove a user profile from typing in the user's name
     */
    void deleteUser() {
        for(int i =0; i<Login.getList().size(); i++){
            if(Login.getList().get(i).equals(userInput.getText().toLowerCase())){
                Login.getList().remove(i);
                link.removeUser(userInput.getText().toLowerCase());
            }
        }
        userInput.clear();
        listUser();

    }

    @FXML
    /**
     * This method will remove a user profile by selecting the user from the userList
     * This method will remove a user profile by selecting the user from the list
     */
    void deleteUser2() {
        if(userList.getSelectionModel().getSelectedIndex() > -1){
            Login.getList().remove(userList.getSelectionModel().getSelectedIndex());
        }
        listUser();
    }

    @FXML
    /**
     * This method atomatically list all created user profile names to the admin
     */
    private void listUser() {
        userList.getItems().clear();
        userList.getItems().addAll(Login.getList());
    }

}
