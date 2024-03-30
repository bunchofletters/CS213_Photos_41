package Controller;

import app.photo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class admin {

    private static login login;

    @FXML
    private TextField Createuser;

    @FXML
    private TextField Deleteuser;
    
    @FXML
    private ListView<String> userList;

    @FXML
    void backToLogin(ActionEvent event) {
        photo x = new photo();
        x.changeScene("/view/login.fxml");
    }

    public void initialize(){
        listUser();
    }

    @FXML
    /**
     * This will method creates a new user profile
     * The created profile is not case sensitive
     * Each user are require to make a unqiue username
     */
    void createUser() {
        if(!login.getList().contains(Createuser.getText().toLowerCase()) && !Createuser.getText().toLowerCase().equals("admin") && !Createuser.getText().toLowerCase().equals("") && !Createuser.getText().toLowerCase().substring(0,1).equals(" ")){
            login.getList().add(Createuser.getText().toLowerCase());
        }
        listUser();
    }

    @FXML
    /**
     * This method will remove a user profile
     */
    void deleteUser() {
        for(int i =0; i<login.getList().size(); i++){
            if(login.getList().get(i).equals(Deleteuser.getText().toLowerCase())){
                login.getList().remove(i);
            }
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