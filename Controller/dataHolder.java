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

// -------------------------------------------------------------------------------------

    /**
     * Creates a single instance of dataHolder
     * It saves the data in a file named "saveData.ser".
     * @return the single instance of dataHolder
     * @throws Exception if an error happens during the serialization.
     * 
     */
    public void saveData() throws Exception{
        File f = new File("saveData.ser");
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        dataList list = new dataList();
        Login = login.getInstance();
        list.setDataUser(Login.getList());
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

// -------------------------------------------------------------------------------------


    /**
     * retrieves program data from the saved file "saveData.ser".
     * deserializes the data before loading it into the data structures.
     */
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

// -------------------------------------------------------------------------------------

    
    /**
     * The Login instance receives the user list from the provided dataList.
     * @param list The user list will be loaded from the dataList.
     */
    public void loadUserList(dataList list){
        if(list.getDataUser() != null)
            Login.setUserList(list.getDataUser());
    }

// -------------------------------------------------------------------------------------

    /**
     * Loads the user's album data into the LinkerClass instance using the dataList.
     * @param list The dataList from which to load the user's album data
     */
    public void loadHashMapUserToAlbum(dataList list){
        if(list.getPA() != null)
            link.loadUserToAlbum(list.getphotoAlbum());
    }

// -------------------------------------------------------------------------------------
    
    /**
     * The album data and image lists are loaded into the LinkerClass instance using the dataList provided.
     * @param list The dataList is used to load the album data and image lists.
     */
    public void loadAlbumToList(dataList list){
        if(list.getIL() != null)
            link.loadAlbumToList(list.getimageList(link.getDataPhotoAlbum()));
    }
}
