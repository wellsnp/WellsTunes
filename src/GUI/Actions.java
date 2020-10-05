/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Audio.mp3tags;
import GUI.TristateButton.TristateState;
import com.mpatric.mp3agic.NotSupportedException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.MediaPlayer.Status;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;


/**
 *
 * @author wellsnp
 */


public class Actions implements ActionListener, MouseListener, ItemListener, ListSelectionListener, CellEditorListener{
        WellsTunesGUI App;
        Timer timer;
        TimerTask tasker;
        private int TaggIndex;
//Constructor
    public Actions(WellsTunesGUI App){
            this.App=App;
            timer = new Timer();
            tasker = new TimerTask() {
                @Override
                public void run() {
                    updatePB();
                }
            };
    } 
//Action Choices
    @Override
    public void actionPerformed(ActionEvent e) {
        Object choice = e.getSource();
        if (choice.equals(App.Menu.File.Quit)) {
            System.exit(0);
        }
        if(choice.equals(App.Menu.File.MusicPath)){
            try {
                this.handelPath();
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (App.Folders.getPath() != null) {
            // <editor-fold defaultstate="collapsed" desc=" Long List of Actions ">
            if (choice.equals(App.Menu.Lib.Tags)) {
                
                BaseList tracks = App.SongList.getTrackTag();
                BaseList albums = App.SongList.getAlbumTag();
                BaseList artists = App.SongList.getArtistTag();
                BaseList songs = App.SongList.getSongTag();
                App.Menu.Lib.TagWindow.TrackNum.LoadTable(tracks);
                App.Menu.Lib.TagWindow.SongName.LoadTable(songs);
                App.Menu.Lib.TagWindow.AlbumName.LoadTable(albums);
                App.Menu.Lib.TagWindow.ArtistName.LoadTable(artists);
                App.Menu.Lib.TagWindow.show();
            }
            
            if (choice.equals(App.Menu.Lib.Duplicates)) {
                try {
                    this.handelDupCheck();
                } catch (IOException ex) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (choice.equals(App.Menu.Lib.DupWindow.Button)) {
                try {
                    this.handelDupDelete();
                } catch (IOException ex) {
                    Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (choice.equals(App.Menu.Player.Play)) {
                this.handelPlayButtonMenu();
                
            }
            if (choice.equals(App.Menu.Player.Stop)) {
                this.handelStop();
            }
            if (choice.equals(App.Menu.Player.Pause)) {
                this.handelPause();
            }
            if (choice.equals(App.Buttons.STOP)) {
                this.handelStop();
            }
            if (choice.equals(App.Buttons.PLAY)) {
                this.handelPlayButtonMenu();
                
            }
            if (choice.equals(App.Buttons.PAUSE)) {
                this.handelPause();
            }
            if (choice.equals(App.Search.SearchBox)) {
                System.out.println("Search Enter");
                if (App.Search.AlbumName.isSelected()) {
                    App.SongList.UpdateList(App.Search.searchAlbums(App.Folders));
                }
                if (App.Search.ArtistName.isSelected()) {
                    App.SongList.UpdateList(App.Search.searchArtists(App.Folders));
                }
                if (App.Search.SongName.isSelected()) {
                    App.SongList.UpdateList(App.Search.searchSongs(App.Folders));
                }
            } else {
                System.out.println("Item clicked: " + e.getActionCommand());
            }

// </editor-fold>
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Object choice=e.getComponent();
        int clickCnt=e.getClickCount();
            
        if(choice.equals(App.SongList.getSongTag().list)) {
        //System.out.println("Song List");
                this.handelSongs(clickCnt);
        }
        if(choice.equals(App.AlbumList.list)) {
        //        System.out.println("Album List");
                this.handelAlbums(clickCnt);
        }
        if(choice.equals(App.ArtistList.list)) {
          this.handelArtists(clickCnt);
        }
          
        if(choice.equals(App.Buttons.REPEAT)) {
            App.Buttons.REPEAT.iterateState();   
        }

        }  
    @Override
    public void itemStateChanged(ItemEvent e) {
       Object choice = e.getItem();
       if(choice.equals(App.Buttons.SHUFFLE)) {
        System.out.println("Shuffle Button");
                if(App.Buttons.SHUFFLE.isSelected()){
                    App.Buttons.SHUFFLE.setText("Shuffle On");
                    App.SongList.ShuffleList();
                }
                else{
                    App.Buttons.SHUFFLE.setText("Shuffle Off");
                    App.SongList.UnShuffleList();
                    //App.SongList.UpdateListFromSelection(0);
                }
        }
    }
    //JTable (Tagg Editor)
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
            Object choice = e.getSource();
            System.out.println(choice.getClass());
            if(choice.equals(App.Menu.Lib.TagWindow.SongName.getCellSelectionModel())){
                TaggIndex=App.Menu.Lib.TagWindow.SongName.getSelectedRow();
            }
            if(choice.equals(App.Menu.Lib.TagWindow.TrackNum.getCellSelectionModel())){
                TaggIndex= App.Menu.Lib.TagWindow.TrackNum.getSelectedRow();
             }
            if(choice.equals(App.Menu.Lib.TagWindow.AlbumName.getCellSelectionModel())){
                TaggIndex = App.Menu.Lib.TagWindow.AlbumName.getSelectedRow();
            }
            if(choice.equals(App.Menu.Lib.TagWindow.ArtistName.getCellSelectionModel())){
                TaggIndex = App.Menu.Lib.TagWindow.ArtistName.getSelectedRow();
            }
 
    }
    @Override
    public void editingStopped(ChangeEvent e) {
            try {
                handelTagEdit(e.getSource());
            } catch (IOException | NotSupportedException ex) {
                Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
            }
        
          }
    
    
//Action Methods  
               //Menus Methods
                public void handelPath() throws IOException, InterruptedException{
                            JFrame frame = new JFrame("Swing Tester");
                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                            int option = fileChooser.showOpenDialog(frame);
                            if(option == JFileChooser.APPROVE_OPTION){
                            File file = fileChooser.getSelectedFile();
                                System.out.println("Folder Selected: " + file.getAbsolutePath());
                                String path = file.getAbsolutePath(); 
                                //App.Folders.setPath(path);
                                //App.checkMusicLib();
                                String path_save="/WellsTunesPathInfo.txt";
                                BufferedWriter writer = new BufferedWriter(new FileWriter("./"+path_save));
                                writer.write(path);
                                writer.close();
                                App.checkMusicLib();
                            }else{
                                System.out.println("Open command canceled");
                            }

                }
                public void handelDupCheck() throws IOException{
                    
                    App.Folders.RunGlobalDuplicationCheck();
                    
                    int NumDuplicates = App.Folders.getDupDetector().getDupcnt();
                    //List<File> Duplicates = App.Folders.DupDetector.DuplicationList;
                    List<String>  Names = App.Folders.getDupDetector().getDuplicationListNames(); 
                    final JList<String> list = new JList<String>(Names.toArray(new String[Names.size()]));
                    App.Menu.Lib.DupWindow.TextField.setText(String.valueOf(NumDuplicates));
                    if(NumDuplicates>0){
                          App.Menu.Lib.DupWindow.ScrollPane.setViewportView(list);            
                    }
                    App.Menu.Lib.DupWindow.show();
                }                
                public void handelDupDelete() throws IOException{
                            App.Folders.DeleteDuplication();
                            App.Menu.Lib.DupWindow.close();
                            App.Menu.Lib.DupWindow.ScrollPane.removeAll();
                }
                public void handelPlayButtonMenu(){
                    switch (App.Buttons.REPEAT.getState()) {
                        case INDETERMINATE:
                            this.handelRepeatOne();
                            break;
                        case SELECTED:
                            if (App.MP3Player.getMediaPlayer() != null) {
                                Status CurrentStatus = App.MP3Player.getMediaPlayer().getStatus();
                                if (CurrentStatus != Status.PLAYING) {
                                    this.handelRepeatAll();
                                }
                            } else {
                                this.handelRepeatAll();
                            }
                            break;
                        default:
                            if (App.MP3Player.getMediaPlayer() != null) {
                                Status CurrentStatus = App.MP3Player.getMediaPlayer().getStatus();
                                if (CurrentStatus != Status.PLAYING) {
                                    this.handelPlay();
                                }
                            } else {
                                this.handelPlay();
                            }
                            break;
                    }
                
                }
                //Player Methods
                public void handelPlay(){        
                        if(App.MP3Player.getMediaPlayer()!=null){
                        Status CurrentStatus=App.MP3Player.getMediaPlayer().getStatus();
                        System.out.println(CurrentStatus);
                            if(CurrentStatus==Status.STOPPED){
                                App.MP3Player.getMediaPlayer().dispose();
                                App.MP3Player.clearMedia();
                                //App.SongList.UpdateList(App.SongList.CurrentAlbum);
                                App.MP3Player.setMedia(App.SongList.buildSongList());
                                App.MP3Player.playSongList();
                            }  
                            if(CurrentStatus==Status.PAUSED){
                                App.MP3Player.getMediaPlayer().play();
                            } 
                            if(CurrentStatus==Status.PLAYING || CurrentStatus==Status.UNKNOWN){
                                System.out.println("Playing");
                                App.MP3Player.getMediaPlayer().dispose();
                                App.MP3Player.clearMedia();
                                App.MP3Player.setMedia(App.SongList.buildSongList());
                               
                                App.MP3Player.playSongList();
                            } 
                        }else{
                                System.out.println("MediaPlayerDoesNotExistYet");
                                //App.MP3Player.clearMedia();
                                App.MP3Player.setMedia(App.SongList.buildSongList());
                                App.MP3Player.playSongList(); 
                                timer.schedule(tasker, 0 , 200);
                                }
                }
                public void handelPause(){
                    Status CurrentStatus=App.MP3Player.getMediaPlayer().getStatus();
                        if(CurrentStatus==Status.PLAYING){
                            App.MP3Player.pauseSong();
                        }      
                }
                public void handelStop(){
                    Status CurrentStatus=App.MP3Player.getMediaPlayer().getStatus();
                        System.out.println(CurrentStatus);
                        if(CurrentStatus==Status.PLAYING){
                            App.MP3Player.stopSong();
                            App.SongList.UpdateListFromSelection(0);
                               
                                
                            }
                        }       
                public void handelRepeatAll(){
                    if(App.Buttons.REPEAT.getState().equals(TristateState.SELECTED)){
                        handelPlay();   
                        Timer repeatTimer = new Timer();
                        TimerTask repeatTasker = new TimerTask(){
                            @Override
                            public void run() {
                                //System.out.println("Repeat Tasker");
                                Status CurrentStatus=App.MP3Player.getMediaPlayer().getStatus();
                                
                                       if(App.MP3Player.getMedia().getMediaList().isEmpty()){
                                           repeatTimer.cancel();
                                           App.MP3Player.getMediaPlayer().setOnEndOfMedia(() -> {
                                               System.out.println("Play Again!");
                                               App.SongList.UpdateListFromSelection(0);
                                               //timer.cancel();
                                               handelRepeatAll();
                                           });            
                                        }
                                       if(CurrentStatus==Status.STOPPED){
                                            repeatTimer.cancel();
                                            this.cancel();
                                       }                          
                            }
                        };
                        repeatTimer.schedule(repeatTasker, 1000, 1000);
                    }
                 }
                public void handelRepeatOne(){
                    //System.out.println("HandelReapeatOne");
                    if(App.Buttons.REPEAT.getState().equals(TristateState.INDETERMINATE)){
                        if(App.MP3Player.getMediaPlayer()!=null){
                            
                        Status CurrentStatus=App.MP3Player.getMediaPlayer().getStatus();
                        //System.out.println(CurrentStatus);
                            
                            if(CurrentStatus==Status.PAUSED){
                                App.MP3Player.getMediaPlayer().play();
                            }else{//PLAYING OR STOPPED
                                
                                App.MP3Player.clearMedia();
                                if(CurrentStatus==Status.STOPPED){
                                    App.MP3Player.setMedia(App.SongList.buildSongList());
                                
                                }
                                if(CurrentStatus==Status.PLAYING || CurrentStatus==Status.UNKNOWN){
                                    App.SongList.UpdateListFromSelection(App.SongList.getCurrentSongIndex());
                                    App.MP3Player.setMedia(App.SongList.buildSongList());
                                }
                                App.MP3Player.getMediaPlayer().dispose();
                                App.MP3Player.repeatSong();
                                App.MP3Player.getMediaPlayer().setOnEndOfMedia(() -> {
                                    handelRepeatOne();
                                }); 
                            } 
                        }else{//MP3Player.getMediaPlayer() is Not Null
                                App.SongList.UpdateListFromSelection(App.SongList.getCurrentSongIndex());
                                App.MP3Player.setMedia(App.SongList.buildSongList());
                                App.MP3Player.repeatSong();
                                App.MP3Player.getMediaPlayer().setOnEndOfMedia(() -> {
                                    handelRepeatOne();
                                });
                                timer.schedule(tasker, 0 , 200);
                                }
                    }else{//No Longer Repeating
                        System.out.println("Exit Handel Repeat One");
                        handelStop();
                    }
                }
                //List Methods
                public void handelArtists(int clicks){
                    if (clicks == 1) {
                        int selectedItem = (int) App.ArtistList.list.getSelectedIndex();
                        if(!App.ArtistList.isNullScrollListFiles()){
                            App.AlbumList.UpdateList(App.ArtistList.getScrollListFiles(selectedItem));
                        }
                    }
                }
                public void handelAlbums(int clicks){
                    if (clicks == 1){
                        int selectedItem = (int) App.AlbumList.list.getSelectedIndex();
                        //Updates the Song List Display with All the Songs in the Album
                        App.SongList.UpdateList(App.AlbumList.getScrollListFiles(selectedItem));           
                    }
                }
                public void handelSongs(int clicks){
                    if (clicks == 2) {
                        int selectedItem = (int) App.SongList.getSongTag().list.getSelectedIndex();
                 
                        if(App.MP3Player.getMediaPlayer()!=null){
                            Status CurrentStatus=App.MP3Player.getMediaPlayer().getStatus();
                            System.out.println("Handel Songs Player Status");
                            System.out.println(CurrentStatus);

                            if(CurrentStatus==Status.PAUSED){
                                this.handelPlay();
                            }else{
                                App.SongList.UpdateListFromSelection(selectedItem);
                                if(App.Buttons.REPEAT.getState().equals(TristateState.INDETERMINATE)){
                                    this.handelRepeatOne();
                                }else if (App.Buttons.REPEAT.getState().equals(TristateState.SELECTED)){
                                    this.handelRepeatAll();
                                }else{
                                    this.handelPlay();
                                }
                            } 
                        }else{
                            System.out.println("Handel Songs Null Player");
                            App.SongList.UpdateListFromSelection(selectedItem);
                            if(App.Buttons.REPEAT.getState().equals(TristateState.INDETERMINATE)){
                                this.handelRepeatOne();
                            }else if (App.Buttons.REPEAT.getState().equals(TristateState.SELECTED)){
                                this.handelRepeatAll();
                            }else{
                                this.handelPlay();
                            }
                        }
                    }
                }
                //Progress Bar Methods
                public void updatePB(){
                //Function for updating Current Song Time For Progress Bar
                //Should Be Called In A Time Task event to update periodically.
                    App.PB.setCurrentTime(App.MP3Player.getMediaPlayer().getCurrentTime().toSeconds());
                    App.PB.setSongLength(App.MP3Player.getMediaPlayer().getMedia().getDuration().toSeconds());
                    App.PB.setString(App.MP3Player.getCurrentSongName());
                    //System.out.println(App.PB.SongLength);
                    //System.out.println(App.PB.CurrentTime);
                    App.PB.UpdatePB();
                }
                private void handelTagEdit(Object choice) throws IOException, NotSupportedException{
                    //System.out.println(choice.getClass());

                    TableCellEditor Songs = App.Menu.Lib.TagWindow.SongName.getCellEditor(TaggIndex, 0);
                    TableCellEditor Album = App.Menu.Lib.TagWindow.AlbumName.getCellEditor(TaggIndex, 0);
                    TableCellEditor Track = App.Menu.Lib.TagWindow.TrackNum.getCellEditor(TaggIndex, 0);
                    TableCellEditor Artist = App.Menu.Lib.TagWindow.ArtistName.getCellEditor(TaggIndex, 0);

                    String SongName = App.Menu.Lib.TagWindow.SongName.getValueAt(TaggIndex, 0).toString();
                    String AlbumName = App.Menu.Lib.TagWindow.AlbumName.getValueAt(TaggIndex, 0).toString();
                    String TrackNum = App.Menu.Lib.TagWindow.TrackNum.getValueAt(TaggIndex, 0).toString();
                    String ArtistName = App.Menu.Lib.TagWindow.ArtistName.getValueAt(TaggIndex, 0).toString();

                    File CurrentFile = App.SongList.getSongTag().getScrollListFiles(TaggIndex);
                    mp3tags tagger = new mp3tags();
                    tagger.checkID3v2(CurrentFile);

                    if (choice.equals(Songs)) {
                        //if(App.Menu.Lib.TagWindow.SongName)
                        System.out.println("Cell is Done Editing");
                        System.out.println("Song");
                        tagger.setSongName(SongName);

                    }
                    if (choice.equals(Track)) {
                        System.out.println("Cell is Done Editing");
                        System.out.println("Track");
                        tagger.setTrackNumber(TrackNum);
                    }
                    if (choice.equals(Album)) {
                        System.out.println("Cell is Done Editing");
                        System.out.println("Album");
                        tagger.setAlbumName(AlbumName);
                    }
                    if (choice.equals(Artist)) {
                        System.out.println("Cell is Done Editing");
                        System.out.println("Artist");
                        tagger.setArtistName(ArtistName);
                        System.out.println(ArtistName);
                    }
                    tagger.UpdateTags(CurrentFile);
 
                }


    // Unimplemented Abstract Action Events
    @Override
    public void editingCanceled(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
  
    /////More Mouse Stuff Needed For Abstact Implementation
    @Override
    public void mousePressed(MouseEvent e) {
    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}                
     
