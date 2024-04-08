package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import app.Photo;

/**
 * This class handles all function the admin will be able to do which
 * include deleting, creating, and viewing created users
 * @author Oscar
 */
public class admin {

    private login Login = login.getInstance();

    private linkerClass link = linkerClass.getInstance();

    @FXML private TextField userInput;

    @FXML private ListView<String> userList;

// -------------------------------------------------------------------------------------

    @FXML void backToLogin() {
        Photo x = Photo.getInstance();
        x.changeScene("/view/login.fxml");
    }

// -------------------------------------------------------------------------------------

    public void initialize(){
        System.out.println(Login);
        listUser();
    }

// -------------------------------------------------------------------------------------

    @FXML void createUser() {
        if(!Login.getList().contains(userInput.getText().toLowerCase()) && !userInput.getText().toLowerCase().equals("admin") && !userInput.getText().toLowerCase().equals("stock") && !userInput.getText().toLowerCase().equals("") && !userInput.getText().toLowerCase().substring(0,1).equals(" ")){
            Login.getList().add(userInput.getText().toLowerCase());
            link.setUserAlbum(userInput.getText().toLowerCase());
        }
        userInput.clear();
        listUser();
    }

// -------------------------------------------------------------------------------------

    @FXML
    void deleteUser() {
        int index = userList.getSelectionModel().getSelectedIndex();
        if(index > -1){
            link.removeUser(userList.getItems().get(index));
            Login.getList().remove(index);
        }
        for(int i =0; i<Login.getList().size(); i++){
            if(Login.getList().get(i).equals(userInput.getText().toLowerCase())){
                Login.getList().remove(i);
                link.removeUser(userInput.getText().toLowerCase());
            }
        }
        userInput.clear();
        listUser();

    }

// -------------------------------------------------------------------------------------

    @FXML private void listUser() {
        userList.getItems().clear();
        userList.getItems().addAll(Login.getList());
    }

}
