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
    private File CurrentAlbum;
    private int CurrentSongIndex;
    private int[] ShuffleArray;
    private final mp3tags tagger;
    private final TaggList AlbumTag;
    private final TaggList GenreTag;
    private final TaggList TrackTag;
    private final TaggList LengthTag;
    private final TaggList ArtistTag;
    private ArrayList Songs;
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
        
        CurrentAlbum = InputFile;
        System.out.println("Current Album");
        System.out.println(this.CurrentAlbum.getName());
        File[] Songs=Folders.ListSongs(InputFile);
        
        this.UpdateListGuts(Songs);

    }
    public void UpdateList(ArrayList<File> Songs){
     
        File SongList[] =Songs.toArray(new File[Songs.size()]);
        this.UpdateListGuts(SongList);

    }
    
    private void UpdateListGuts(File[] Songs){
    
    
        this.CurrentSongIndex=0;
        setScrollListFiles(new ArrayList<>());
        setScrollListNames(new ArrayList<>());
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
           System.out.println("ShuffleArray");
           System.out.println(ShuffleArray[n]);
           
           n++;        
            this.addToScrollListFiles(CurrentSong);
            //ScrollListNames.add(CurrentSong.getName());
           
            if(tagger.checkID3v2(CurrentSong)){
                listmodel.addElement((tagger.getSongName()==null)? "-":tagger.getSongName());
                AlbumTag.listmodel.addElement((tagger.getAlbumName()==null)? "-":tagger.getAlbumName());
                ArtistTag.listmodel.addElement((tagger.getArtistName()==null)? "-":tagger.getArtistName());
                TrackTag.listmodel.addElement((tagger.getTrackNumber()==null)? "-":tagger.getTrackNumber());
                //Base Model Used A String List
                //GenreTag.listmodel.addElement(tagger.getGenre());
                LengthTag.listmodel.addElement((tagger.getSongLength()==null)? "-":tagger.getSongLength());
                this.addToScrollListNames((tagger.getSongName()==null)? "-":tagger.getSongName());
                                
//Populate Other Lists That We Will Build For The Tags
                //System.out.println(CurrentSong.getName());
            }else{
                  //If there is no Tagged Song name, use the literal file name. 
                 listmodel.addElement(CurrentSong.getName());
                 this.addToScrollListNames(CurrentSong.getName());
             }
                
        }
        //Sets the Master List of Songs. Never WIll Change Size By Clicking List. 
            this.Songs=this.copyFileList();
    
    }
    
    public void UpdateListFromSelection(int Index){
         this.clearScrollLists();
        //Update From Master Songs List//
        for(int j=Index; j<Songs.size(); j++){        
            this.addToScrollListFiles((File) Songs.get(ShuffleArray[j]));
            this.addToScrollListNames(listmodel.get(j));
         }
    
    }
    
    public void ShuffleList(){
            Random rng = new Random();   // i.e., java.util.Random.
            int n = listmodel.getSize();       // The number of items left to shuffle (loop invariant).
            System.out.println("List Size");
            System.out.println(n);
            //FolderInfo Folders = new FolderInfo();
            //File[] Songs=Folders.ListSongs(this.CurrentAlbum);
             while (n > 1){
                int k = rng.nextInt(n);  // 0 <= k < n.
                n--;
                this.listmodel=ShuffleGuts(listmodel, n, k);
                this.TrackTag.listmodel=ShuffleGuts(TrackTag.listmodel, n, k);
                this.ArtistTag.listmodel=ShuffleGuts(ArtistTag.listmodel, n, k);
                this.AlbumTag.listmodel=ShuffleGuts(AlbumTag.listmodel, n, k);
                this.LengthTag.listmodel=ShuffleGuts(LengthTag.listmodel, n, k); 
                this.ShuffleArray=ShuffleGuts(this.ShuffleArray,n,k);
            }
           ArrayList<File> Songs = this.copyFileList();
           System.out.println("Songs Size");
            System.out.println(Songs.size());
           this.clearScrollLists();
           for(int j=0; j<Songs.size(); j++){
            //Songs Is Not Shuffled So We Need The Shuffle Array   
            this.addToScrollListFiles(Songs.get(ShuffleArray[j]));
            //listmode is Shuffled So We Do Not Need The Shuffle Array.
            this.addToScrollListNames(listmodel.get(j));
            System.out.println("ShuffleArray");
                System.out.println(ShuffleArray[j]);
         }

    };
    public void UnShuffleList(){
            int n = listmodel.getSize();   
            System.out.println("List Size");
            System.out.println(n);// The number of items left to shuffle (loop invariant).
            int k = 0;
            for(int j=0;j<n;j++){
                for (int l=0;l<n;l++){
                 
                    if(j==ShuffleArray[l]){
                        k=l;
                        break;
                    }
                }
                        
                this.listmodel=UnShuffleGuts(listmodel, j, k);
                this.TrackTag.listmodel=UnShuffleGuts(TrackTag.listmodel, j, k);
                this.ArtistTag.listmodel=UnShuffleGuts(ArtistTag.listmodel, j, k);
                this.AlbumTag.listmodel=UnShuffleGuts(AlbumTag.listmodel, j, k);
                this.LengthTag.listmodel=UnShuffleGuts(LengthTag.listmodel, j, k); 
                this.ShuffleArray=UnShuffleGuts(ShuffleArray,j,k);
            }
            //ArrayList<File> Songs = this.copyFileList();
            System.out.println("Songs Size");
            System.out.println(Songs.size());
            this.clearScrollLists();
            for(int j=0; j<Songs.size(); j++){
                this.addToScrollListFiles((File) Songs.get(j));
                this.addToScrollListNames(listmodel.get(j));
                System.out.println("ShuffleArray");
                System.out.println(ShuffleArray[j]);
            }
     }

    private DefaultListModel<String> ShuffleGuts(DefaultListModel<String> listmodel, int n, int k){
                String temp = listmodel.get(n); // n is now the last pertinent index;
                listmodel.setElementAt(listmodel.get(k), n);
                listmodel.setElementAt(temp, k);
                
                return listmodel;
        
    }
    private int[] ShuffleGuts(int[] Array, int n, int k){
                int temp = Array[n]; // n is now the last pertinent index;
                Array[n]=Array[k];
                Array[k]=temp;
                return Array;
        
    }
    private DefaultListModel<String> UnShuffleGuts(DefaultListModel<String> listmodel, int n, int k){
                String temp1 = listmodel.get(n); // n is now the last pertinent index;
                String temp2 = listmodel.get(k);
                listmodel.setElementAt(temp2, n);
                listmodel.setElementAt(temp1, k);
                return listmodel;
        
    }
    private int[] UnShuffleGuts(int[] Array, int n, int k){
                //Swap Indicied n and k
                int temp1 = Array[n];
                int temp2 = Array[k];
                Array[n]=temp2;
                Array[k]=temp1;
                return Array;
        
    }
 
    public SongMedia  buildSongList(){
                 System.out.println("buildSongList");
                  List<File> Songs=this.getScrollListFiles();  
                  ObservableList<Media>   mediaList = FXCollections.observableArrayList();
                  ObservableList<String> mediaName = FXCollections.observableArrayList();
                  SongMedia MP3Media = new SongMedia();
                  for (File CurrentSong:Songs) {
                    Media NewSong = new Media(CurrentSong.toURI().toString());
                    mediaList.add(NewSong);
                    mediaName.add(removeFromScrollListNames());
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

    
    public ArrayList<File> copyFileList(){
        ArrayList<File> Songs = new ArrayList<>();
        
        int N=getScrollListFilesSize();
        List<File> CurrentSongList = this.getScrollListFiles();
        for (int j=0; j<N; j++){
            Songs.add(CurrentSongList.get(j));
        } 
        return Songs;
    }
    
    public TaggList getAlbumTag() {
        return AlbumTag;
    }

    public TaggList getGenreTag() {
        return GenreTag;
    }

    public TaggList getTrackTag() {
        return TrackTag;
    }

    public TaggList getLengthTag() {
        return LengthTag;
    }

    public TaggList getArtistTag() {
        return ArtistTag;
    }  
    
    public File getCurrentAlbum() {
        return CurrentAlbum;
    }

    public int getCurrentSongIndex() {
        return CurrentSongIndex;
    }    
    
//This Class Is Used To Package Up The Data Needed For The AudioMedia Object.
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