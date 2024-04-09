package Controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * @author oscar ol78
 */
public class dataHolder implements Serializable{
    login Login;
    linkerClass link;

    /**
     * Creates a single instance of dataHolder
     * @return the single instance of dataHolder
     */
    /**
     * Save all data that will be need for the next running of photo program
     */
    public void saveData() throws Exception{
        File f = new File("./saveData.ser");
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        dataList list = new dataList();
        Login = login.getInstance();
        list.setDataUser(Login.getList());
        list.setstockUserAlbumn(Login.getcreatedPhotoAlbum());
        list.setstockUserPage(Login.getcreatedUserPage());
        link = linkerClass.getInstance();
        list.setPhotoAlbum(link.getDataPhotoAlbum());
        for(int i = 0; i<link.getDataPhotoAlbum().size(); i++){ //run up to the amount of user
            for(int j = 0; j<link.getDataPhotoAlbum().get(Login.getList().get(i)).getAlbumList().size(); j++){ //run up to the amount of photoAlbum in that users
                //feed it the user, photoAlbumList, and HashMap
                list.setimageList(Login.getList().get(i), j, link.getPhotoAlbum(Login.getList().get(i)).getAlbumList().get(j), link.getPhotoList());
            }
        }
        oos.writeObject(list);
        System.out.println(list.getDataUser().get(0));
        fos.close();
        oos.close();
    }

    public void loadData(){
        File f = new File("saveData.ser");
        try{
            dataList list = null;
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (dataList) ois.readObject();
            Login = login.getInstance();
            loadUserList(list);
            link = linkerClass.getInstance();
            loadHashMapUserToAlbum(list);
            loadAlbumToList(list);
            fis.close();
            ois.close();
        }
        catch(Exception e){
            System.out.println("No file");
        }
    }

    public void loadUserList(dataList list){
        if(list.getDataUser() != null)
            Login.setUserList(list.getDataUser());
        Login.setcreatedUserPage(list.getstockUserPage());
        Login.setcreatedPhotoAlbum(list.getstockUserAlbumn());
    }

    public void loadHashMapUserToAlbum(dataList list){
        if(list.getPA() != null)
            link.loadUserToAlbum(list.getphotoAlbum());
    }

    public void loadAlbumToList(dataList list){
        if(list.getIL() != null)
            link.loadAlbumToList(list.getimageList(link.getDataPhotoAlbum()));
    }
}
