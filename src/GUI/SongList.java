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
import Audio.AudioMedia;
import java.util.ArrayList;

import java.io.File;
import FileAndDirectory.FolderInfo;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer.Status;
//import javax.swing.JList;
public class SongList extends BaseList{
    File CurrentAlbum;
    public SongList(Actions ActionHandler)
    {   
        super(ActionHandler);
        //MP3Player=Player;
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
            //ScrollListNames.add(CurrentArtist.getName());
            ScrollListFiles.add(CurrentSong);    
            listmodel.addElement(CurrentSong.getName());
            
            System.out.println(CurrentSong.getName());
        }
        //this.buildSongList();
       
     
    }
    
    public void UpdateListFromSelection(int Index){
        FolderInfo Folders = new FolderInfo();
        //Get Songs of Album
        System.out.println("Current Album");
        System.out.println(this.CurrentAlbum.getName());
        File[] Songs=Folders.ListSongs(this.CurrentAlbum);
        //this.ScrollListFiles = new ArrayList<>();
        this.ScrollListFiles.clear();
        //listmodel.removeAllElements();
        for(int j=Index; j<Songs.length; j++){
            //ScrollListNames.add(CurrentArtist.getName());
            System.out.println(j);
            System.out.println(Songs[j]);
            
            this.ScrollListFiles.add(Songs[j]);    
            //listmodel.addElement(Songs[j].getName());
            
        }
        //this.buildSongList();
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
//     @Override
//    public void initMouseAdapter(){  
//       mouseListener = new MouseAdapter() {
//      @Override
//            public void mouseClicked(MouseEvent e) {
//                
//        };
//    }

}
