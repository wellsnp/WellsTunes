/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author wellsnp
 */
import Audio.mp3tags;
import java.util.ArrayList;
import java.io.File;
import FileAndDirectory.FolderInfo;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;

public class SongList extends BaseList{
    File CurrentAlbum;
    mp3tags tagger;
    public SongList(Actions ActionHandler)
    {   
        super(ActionHandler);
        tagger = new mp3tags();
    }
    
    @Override
    public void UpdateList(File InputFile){
        FolderInfo Folders = new FolderInfo();
      //Get Songs of Album
        CurrentAlbum = InputFile;
        System.out.println("Current Album");
        System.out.println(this.CurrentAlbum.getName());
        File[] Songs=Folders.ListSongs(InputFile);
        this.ScrollListFiles = new ArrayList<>();
        listmodel.removeAllElements();
        for(File CurrentSong:Songs){
           
            ScrollListFiles.add(CurrentSong);    
            if(tagger.checkID3v2(CurrentSong)){
                listmodel.addElement(tagger.getSongName());
                //Populate Other Lists That We Will Build For The Tags
                //System.out.println(CurrentSong.getName());
            }else{
                  //If there is no Tagged Song name, use the literal file name. 
                  listmodel.addElement(CurrentSong.getName());
             }
                
        }
  
    }
    public void UpdateListFromSelection(int Index){
        FolderInfo Folders = new FolderInfo();
        //Get Songs of Album
        System.out.println("Current Album");
        System.out.println(this.CurrentAlbum.getName());
        File[] Songs=Folders.ListSongs(this.CurrentAlbum);
        
        this.ScrollListFiles.clear();
        
        for(int j=Index; j<Songs.length; j++){
           
            System.out.println(j);
            System.out.println(Songs[j]);
            
            this.ScrollListFiles.add(Songs[j]);    
         }
    
    }
    public ObservableList<Media>  buildSongList(){
                 List<File> Songs=this.ScrollListFiles;  
                 ObservableList<Media> mediaList = FXCollections.observableArrayList();
                 for (File CurrentSong:Songs) {
                    Media NewSong = new Media(CurrentSong.toURI().toString());
                    mediaList.add(NewSong);
                }
                 return mediaList;
           } 

}
