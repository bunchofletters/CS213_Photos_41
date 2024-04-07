package Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import Controller.Data_seralization.imageAttributes_serializable;
import Controller.Data_seralization.listOfPhotos_serializable;
import Controller.Data_seralization.photoAlbumList_serializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class dataList implements Serializable{
        // Obser ArrayList of Strings of Users
        ArrayList<String> users = new ArrayList<>();

        // HashMap<String, photoAlbumList> photoAlbum = new HashMap<>();
        HashMap<String, photoAlbumList_serializable> photoAlbum = new HashMap<>();
        // HashMap<photoAlbumList, listOfPhotos> imageList = new HashMap<>();
        HashMap<photoAlbumList_serializable, listOfPhotos_serializable> imageList = new HashMap<>();

        public ArrayList<String> getDataUser(){
            return users;
        }
        public HashMap<String, photoAlbumList_serializable> getPA(){
            return photoAlbum;
        }
        public HashMap<photoAlbumList_serializable, listOfPhotos_serializable> getIL(){
            return imageList;
        }
        public HashMap<String, photoAlbumList> getphotoAlbum(){
            HashMap<String, photoAlbumList> returnAlbum = new HashMap<>();
            for(int i = 0; i < photoAlbum.size(); i++){
                ObservableList<photoAlbumList> tmp = FXCollections.observableArrayList();
                photoAlbumList tmpList = new photoAlbumList(tmp);
                for(int j = 0; j<photoAlbum.get(users.get(i)).getAlbumList().size();j++){
                    photoAlbumList tmp3 = new photoAlbumList(photoAlbum.get(users.get(i)).getAlbumList().get(j).getName(), photoAlbum.get(users.get(i)).getAlbumList().get(j).getPhotoNum(), photoAlbum.get(users.get(i)).getAlbumList().get(j).getLowestDate(), photoAlbum.get(users.get(i)).getAlbumList().get(j).getHighestDate());
                    tmp.add(tmp3);
                }
                returnAlbum.put(users.get(i), tmpList);
            }
            return returnAlbum;
        }

        public HashMap<photoAlbumList, listOfPhotos> getimageList(HashMap<String, photoAlbumList> dataToCopy){
            HashMap<photoAlbumList, listOfPhotos> returnList = new HashMap<>();
            //Loop for each user
            for(int i = 0; i< users.size(); i++){
                String user = users.get(i);
                //Loop for each album the user has
                for(int j = 0; j<photoAlbum.get(user).getAlbumList().size(); j++){
                    photoAlbumList_serializable userAlbum = photoAlbum.get(user).getAlbumList().get(j);
                    photoAlbumList uploadUserAlbumn = dataToCopy.get(user).getAlbumList().get(j);
                    //Make a ObservableList of imageAttribute
                    ObservableList<imageAttributes> copydata = FXCollections.observableArrayList();
                    //Make a listofPhotos to add to the hashmap
                    listOfPhotos copyPhotoList = new listOfPhotos(copydata);
                    //Connect the uploadUserAlbum and copyPhotoList together
                    returnList.put(uploadUserAlbumn, copyPhotoList);
                    //Loop for each listOfPhoto that user album has
                    for(int k = 0; k<imageList.get(userAlbum).getPhotos().size(); k++){
                        imageAttributes_serializable data = imageList.get(userAlbum).getPhotos().get(k);
                        //copy photo
                        try {
                            String url = data.getUrl();
                            if(url.contains("\\")){
                                url.replace("\\", "/");
                            }
                            url = url.substring(6);
                            System.out.println(url);
                            InputStream stream = new FileInputStream(url);
                            Image image = new Image(stream);
                            imageAttributes imageDataCopy = new imageAttributes(image);
                            System.out.println("Pass Image copying");
                            //copy date
                            imageDataCopy.setDate(data.getUploadDateAsDate());
                            System.out.println("Pass Date copying");
                            //copy caption
                            imageDataCopy.setCaption(data.getCaption());
                            System.out.println("Pass Caption copying");
                            //copy tag
                            if(imageDataCopy.getTags().size()>0){
                                //make a observableArrayList of String
                                ObservableList<String> tagCopy = FXCollections.observableArrayList();
                                imageDataCopy.setTag(tagCopy);
                                tagCopy.addAll(imageDataCopy.getTags());
                            }
                            System.out.println("Pass Tag copying");
                            copydata.add(imageDataCopy);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return returnList;
        }

        public void setDataUser(ObservableList<String> data){
            users.addAll(data);
        }

        public void setPhotoAlbum(HashMap<String, photoAlbumList> data){
            if(data != null){
                for(int i = 0; i< users.size(); i++){
                    ArrayList<photoAlbumList_serializable> tmp = new ArrayList<>();
                    photoAlbumList_serializable tmp2 = new photoAlbumList_serializable(tmp);
                    for(int j = 0; j<data.get(users.get(i)).getAlbumList().size(); j++){
                        photoAlbumList_serializable tmp3 = new photoAlbumList_serializable(data.get(users.get(i)).getAlbumList().get(j).getName(), data.get(users.get(i)).getAlbumList().get(j).getPhotoNum(), data.get(users.get(i)).getAlbumList().get(j).getLowestDate(), data.get(users.get(i)).getAlbumList().get(j).getHighestDate());
                        tmp.add(tmp3);
                    }
                    photoAlbum.put(users.get(i), tmp2);
                }
            }
        }

        public void setimageList(String users,int index ,photoAlbumList theAlbum, HashMap<photoAlbumList, listOfPhotos> map){
            photoAlbumList_serializable input = getAlbum(users).getAlbumList().get(index); //1 to 1 copy of theAlbum input
            //initalization of listofphotos
            ArrayList<imageAttributes_serializable> tmp = new ArrayList<>();
            listOfPhotos_serializable listSerial = new listOfPhotos_serializable(tmp);
            //add listofphotos to hashmap
            imageList.put(input, listSerial);
            //Grab information out of the providied hashmap
            listOfPhotos mapPhoto = map.get(theAlbum);
            //Copy information from mapPhoto into listSerial
            //Copy ObservableList information over to ArrayList
            for(int i = 0; i< mapPhoto.getPhotos().size(); i++){
                //Copy image_attribute in mapPhoto and make them into imageAttribute_ser
                //Copy image first
                imageAttributes_serializable copyData = new imageAttributes_serializable(mapPhoto.getPhotos().get(i).getImage());
                System.out.println("DataList:SetImageList" + copyData.getUrl());
                //copy date
                copyData.setUploadDate(mapPhoto.getPhotos().get(i).getUploadDateAsDate());
                //copy caption
                copyData.setCaption(mapPhoto.getPhotos().get(i).getCaption());
                //copy tags over
                if(mapPhoto.getPhotos().get(i).getTags().size() > 0){
                    ArrayList<String> copyTag = new ArrayList<>();
                    copyData.setTag(copyTag);
                    copyTag.addAll(mapPhoto.getPhotos().get(i).getTags());
                }
                tmp.add(copyData);
            }
        }

        private photoAlbumList_serializable getAlbum(String user){
            return photoAlbum.get(user);
        }
}
