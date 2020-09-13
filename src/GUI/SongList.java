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
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import java.util.List;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class SongList extends BaseList{
    File CurrentAlbum;
    int CurrentSongIndex;
    int[] ShuffleArray;
    mp3tags tagger;
    TaggList AlbumTag;
    TaggList GenreTag;
    TaggList TrackTag;
    TaggList LengthTag;
    TaggList ArtistTag;
    public SongList(Actions ActionHandler)
    {   
        super(ActionHandler);
        tagger =    new mp3tags();
        AlbumTag =  new TaggList();
        GenreTag =  new TaggList();
        TrackTag =  new TaggList();
        LengthTag = new TaggList();
        ArtistTag = new TaggList();
        ListCellRenderer<? super String> renderer = getRenderer();
        
        this.list.setCellRenderer(getRenderer());
    }
    
    @Override
    public void UpdateList(File InputFile){
        FolderInfo Folders = new FolderInfo();
      //Get Songs of Album
        CurrentSongIndex=0;
        CurrentAlbum = InputFile;
        System.out.println("Current Album");
        System.out.println(this.CurrentAlbum.getName());
        File[] Songs=Folders.ListSongs(InputFile);
        this.ScrollListFiles = new ArrayList<>();
        this.ScrollListNames = new ArrayList<>();
        ShuffleArray = new int[Songs.length];
        listmodel.removeAllElements();
        AlbumTag.clearList();
        GenreTag.clearList();
        ArtistTag.clearList();
        TrackTag.clearList();
        LengthTag.clearList();
        
        int n=0;
        for(File CurrentSong:Songs){
           ShuffleArray[n]=n;
           n++;        
            ScrollListFiles.add(CurrentSong);
            //ScrollListNames.add(CurrentSong.getName());
            if(tagger.checkID3v2(CurrentSong)){
                listmodel.addElement(tagger.getSongName());
                AlbumTag.listmodel.addElement(tagger.getAlbumName());
                ArtistTag.listmodel.addElement(tagger.getArtistName());
                TrackTag.listmodel.addElement(tagger.getTrackNumber());
                //Base Model Used A String List
                //GenreTag.listmodel.addElement(tagger.getGenre());
                LengthTag.listmodel.addElement(tagger.getSongLength());
                ScrollListNames.add(tagger.getSongName());
                                
//Populate Other Lists That We Will Build For The Tags
                //System.out.println(CurrentSong.getName());
            }else{
                  //If there is no Tagged Song name, use the literal file name. 
                  listmodel.addElement(CurrentSong.getName());
                  ScrollListNames.add(CurrentSong.getName());
             }
                
        }
  
    }
    public void UpdateListFromSelection(int Index){
        CurrentSongIndex=Index;
        FolderInfo Folders = new FolderInfo();
        //Get Songs of Album
        System.out.println("Current Album");
        System.out.println(this.CurrentAlbum.getName());
        File[] Songs=Folders.ListSongs(this.CurrentAlbum);
        
        this.ScrollListFiles.clear();
        this.ScrollListNames.clear();
        for(int j=Index; j<Songs.length; j++){
           
            //System.out.println(j);
            //System.out.println(Songs[j]);
            
            this.ScrollListFiles.add(Songs[ShuffleArray[j]]);
            this.ScrollListNames.add(listmodel.get(j));
         }
    
    }
    
    public void ShuffleList(){
            Random rng = new Random();   // i.e., java.util.Random.
            int n = listmodel.getSize();       // The number of items left to shuffle (loop invariant).
            System.out.println("List Size");
            System.out.println(n);
            FolderInfo Folders = new FolderInfo();
            File[] Songs=Folders.ListSongs(this.CurrentAlbum);
             while (n > 1){
                int k = rng.nextInt(n);  // 0 <= k < n.
                n--;
                this.listmodel=ShuffleGuts(listmodel, n, k);
                this.TrackTag.listmodel=ShuffleGuts(TrackTag.listmodel, n, k);
                this.ArtistTag.listmodel=ShuffleGuts(ArtistTag.listmodel, n, k);
                this.AlbumTag.listmodel=ShuffleGuts(AlbumTag.listmodel, n, k);
                this.LengthTag.listmodel=ShuffleGuts(LengthTag.listmodel, n, k); 
                this.ShuffleArray=ShuffleGuts(ShuffleArray,n,k);
            }
           this.ScrollListFiles.clear();
           this.ScrollListNames.clear();
           for(int j=0; j<n; j++){
            this.ScrollListFiles.add(Songs[ShuffleArray[j]]);
            this.ScrollListNames.add(listmodel.get(j));
         }

    };
    
    private DefaultListModel<String> ShuffleGuts(DefaultListModel<String> listmodel, int n, int k){
                String temp = listmodel.get(n); // n is now the last pertinent index;
                listmodel.setElementAt(listmodel.get(k), n);
                listmodel.setElementAt(temp, k);
                return listmodel;
        
    }
     private int[] ShuffleGuts(int[] Array, int n, int k){
                int temp = Array[n]; // n is now the last pertinent index;
                Array[n]=k;
                Array[k]=temp;
                return Array;
        
    }
 
    public SongMedia  buildSongList(){
                  List<File> Songs=this.ScrollListFiles;  
                  ObservableList<Media>   mediaList = FXCollections.observableArrayList();
                  ObservableList<String> mediaName = FXCollections.observableArrayList();
                  SongMedia MP3Media = new SongMedia();
                  for (File CurrentSong:Songs) {
                    Media NewSong = new Media(CurrentSong.toURI().toString());
                    mediaList.add(NewSong);
                    mediaName.add(ScrollListNames.remove(0));
                }
                  MP3Media.setMediaList(mediaList);
                  MP3Media.setMediaName(mediaName);
                 return MP3Media;
           } 
    
    @Override
    public final ListCellRenderer<? super String> getRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.white));
               listCellRendererComponent.setFont(new Font("Times", Font.BOLD, 16));
                if (index % 2 == 0) {    
                    listCellRendererComponent.setBackground(getEven());
                    
                }else {
                   
                    listCellRendererComponent.setBackground(getOdd());
                }
                return listCellRendererComponent;
            }
        };
    }


public static class SongMedia{
private ObservableList<Media>   mediaList;
private ObservableList<String>   mediaName;
        public SongMedia(){
        
        };

        public ObservableList<Media> getMediaList() {
            return mediaList;
        }

        public ObservableList<String> getMediaName() {
            return mediaName;
        }
        public void setMediaList(ObservableList<Media> mediaList) {
            this.mediaList = mediaList;
        }

        public void setMediaName(ObservableList<String> mediaName) {
            this.mediaName = mediaName;
        }




}

}