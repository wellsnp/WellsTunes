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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer.Status;
import javax.swing.JList;
public class SongList extends BaseList{
 
    AudioMedia MP3Player;
    File CurrentAlbum;
    public SongList(AudioMedia Player){
        MP3Player=Player;
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
        this.buildSongList();
        System.out.println("Media List Size");
        System.out.println(MP3Player.mediaList.size());
     
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
        this.buildSongList();
    }
            public void buildSongList(){
                 List<File> Songs=this.ScrollListFiles;   
                 MP3Player.mediaList.clear();
                for (File CurrentSong:Songs) {
                    Media NewSong = new Media(CurrentSong.toURI().toString());
                    MP3Player.mediaList.add(NewSong);
                }
           } 
     @Override
    public void initMouseAdapter(JList list){  
       mouseListener = new MouseAdapter() {
      @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedItem = (int) list.getSelectedIndex();
                    
                    //Rebuild The Song List and Play From Here:
                    //if(!MP3Player.status){
                    if(ScrollListFiles!=null){
                    System.out.println(selectedItem);
                    }
                     
                     
                     if(MP3Player.mediaPlayer!=null){
                        Status CurrentStatus=MP3Player.mediaPlayer.getStatus();
                        System.out.println(CurrentStatus);
                            if(CurrentStatus==Status.STOPPED){
                                MP3Player.mediaPlayer.dispose();
                                UpdateListFromSelection(selectedItem);
                                MP3Player.playSongList();
                            }
                            if(CurrentStatus==Status.PLAYING){
                                MP3Player.mediaPlayer.stop();
                                MP3Player.mediaPlayer.dispose();
                                UpdateListFromSelection(selectedItem);
                                MP3Player.playSongList();
                            }
                            if(CurrentStatus==Status.PAUSED){
                                MP3Player.mediaPlayer.play();
                            } 
                      }else{
                                UpdateListFromSelection(selectedItem);
                                MP3Player.playSongList();
                      }
                     //MP3Player.playSongList();
                    //}else{
                    //    MP3Player.stopSong();
                    //    MP3Player.setStatus(false);
                    //    MP3Player.Song=null;
                    //    MP3Player.mediaPlayer=null;
                        //MP3Player.addSong(Song);
                       // MP3Player.playSong();
                        
                    //}
                    //System.out.println(selectedItem);
               
                }
            }
        };
    }

}
