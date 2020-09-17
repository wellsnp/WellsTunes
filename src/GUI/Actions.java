/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.TristateButton.TristateState;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.MediaPlayer.Status;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


/**
 *
 * @author wellsnp
 */


public class Actions implements ActionListener, MouseListener, ItemListener{
        WellsTunesGUI App;
        Timer timer;
        TimerTask tasker;
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
        //row new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        

                    //System.out.println("Item clicked: "+e.getActionCommand());    
                    Object choice = e.getSource();
                    if (choice.equals(App.Menu.File.Quit)) {
                      System.exit(0);
                    }
                    if(choice.equals(App.Menu.File.MusicPath)){
                        try {
                            this.handelPath();
                        } catch (IOException ex) {
                            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if(choice.equals(App.Menu.Lib.Duplicates) & (App.Folders.path!=null)){
                        
                        try {
                            App.Folders.RunGlobalDuplicationCheck();
                        } catch (IOException ex) {
                            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
                        }             
                    }
                    if((choice.equals(App.Menu.Player.Play)) & (App.Folders.path!=null)){
                        this.handelPlayButtonMenu();
                      
                    }
                    if((choice.equals(App.Menu.Player.Stop)) & (App.Folders.path!=null)){
                        this.handelStop();
                    }
                    if((choice.equals(App.Menu.Player.Pause)) & (App.Folders.path!=null)){
                        this.handelPause();
                    }
                    if(choice.equals(App.Buttons.STOP)){
                        this.handelStop();
                    }
                    if(choice.equals(App.Buttons.PLAY)){
                        this.handelPlayButtonMenu();
                        
                    }
                    if(choice.equals(App.Buttons.PAUSE)){
                        this.handelPause();
                    }
                    if(choice.equals(App.Search.SearchBox)){
                        System.out.println("Search Enter");
                        if(App.Search.AlbumName.isSelected()){
                            App.SongList.UpdateList(App.Search.searchAlbums(App.Folders));
                        }
                        if(App.Search.ArtistName.isSelected()){
                            App.SongList.UpdateList(App.Search.searchArtists(App.Folders));
                        }
                        if(App.Search.SongName.isSelected()){
                            App.SongList.UpdateList(App.Search.searchSongs(App.Folders));
                        }
                    }
                    else{
                        System.out.println("Item clicked: "+e.getActionCommand()); 
                    }
                        
                }
    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Object choice=e.getComponent();
        int clickCnt=e.getClickCount();
            
        if(choice.equals(App.SongList.list)) {
        System.out.println("Song List");
                this.handelSongs(clickCnt);
        }
         if(choice.equals(App.AlbumList.list)) {
                System.out.println("Album List");
                this.handelAlbums(clickCnt);
        }
         
          if(choice.equals(App.ArtistList.list)) {
        //System.out.println("Artists List");
          this.handelArtists(clickCnt);
        }
          
            if(choice.equals(App.Buttons.REPEAT)) {
        //System.out.println("Artists List");
            App.Buttons.REPEAT.iterateState();
            
        }
        
        
        } 
    @Override
    public void itemStateChanged(ItemEvent e) {
       Object choice = e.getItem();
       if(choice.equals(App.Buttons.SHUFFLE)) {
        System.out.println("Shuffle Button");
                if(App.Buttons.SHUFFLE.isSelected()){;
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
                                String path_save="C:\\Users\\wells\\OneDrive\\Documents\\NetBeansProjects\\WellsTunes\\WellsTunesPathInfo.txt";
                                BufferedWriter writer = new BufferedWriter(new FileWriter(path_save));
                                writer.write(path);
                                writer.close();
                                App.checkMusicLib();
                            }else{
                                System.out.println("Open command canceled");
                            }
                
                
                }
                public void handelPlayButtonMenu(){
                    if(App.Buttons.REPEAT.getState().equals(TristateState.INDETERMINATE)){
                            this.handelRepeatOne();
                    }else if (App.Buttons.REPEAT.getState().equals(TristateState.SELECTED)){
                        if(App.MP3Player.mediaPlayer!=null){
                                Status CurrentStatus=App.MP3Player.mediaPlayer.getStatus(); 
                                 if(CurrentStatus!=Status.PLAYING){
                                            this.handelRepeatAll();
                                    }    
                        }else{
                                this.handelRepeatAll();
                        }
                    }else{
                        
                            if(App.MP3Player.mediaPlayer!=null){
                                Status CurrentStatus=App.MP3Player.mediaPlayer.getStatus(); 
                                    if(CurrentStatus!=Status.PLAYING){
                                            this.handelPlay();
                                    }                                       
                            }else{
                                this.handelPlay();
                            }
                    }
                
                }
                //Player Methods
                public void handelPlay(){
                        
                        if(App.MP3Player.mediaPlayer!=null){
                        Status CurrentStatus=App.MP3Player.mediaPlayer.getStatus();
                        System.out.println(CurrentStatus);
                            if(CurrentStatus==Status.STOPPED){
                                App.MP3Player.mediaPlayer.dispose();
                                App.MP3Player.clearMedia();
                                //App.SongList.UpdateList(App.SongList.CurrentAlbum);
                                App.MP3Player.setMedia(App.SongList.buildSongList());
                                
                                App.MP3Player.playSongList();
                            }  
                            if(CurrentStatus==Status.PAUSED){
                                App.MP3Player.mediaPlayer.play();
                            } 
                            if(CurrentStatus==Status.PLAYING || CurrentStatus==Status.UNKNOWN){
                                System.out.println("Playing");
                                App.MP3Player.mediaPlayer.dispose();
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
                    Status CurrentStatus=App.MP3Player.mediaPlayer.getStatus();
                        if(CurrentStatus==Status.PLAYING){
                            App.MP3Player.pauseSong();
                        }      
                }
                public void handelStop(){
                    Status CurrentStatus=App.MP3Player.mediaPlayer.getStatus();
                        System.out.println(CurrentStatus);
                        if(CurrentStatus==Status.PLAYING){
                            App.MP3Player.stopSong();
                            App.SongList.UpdateListFromSelection(0);
                               
                                
                            }
                        }       
                
                 public void handelRepeatAll(){
                    if(App.Buttons.REPEAT.getState().equals(TristateState.SELECTED)){
                        handelPlay();   
                        Timer timer = new Timer();
                        TimerTask tasker = new TimerTask(){
                            @Override
                            public void run() {
                                System.out.println("Repeat Tasker");
                                Status CurrentStatus=App.MP3Player.mediaPlayer.getStatus();
                                
                                       if(App.MP3Player.MP3Media.getMediaList().isEmpty()){
                                           App.MP3Player.mediaPlayer.setOnEndOfMedia(new Runnable() {
                                            @Override
                                            public void run() { 
                                           
                                            System.out.println("Play Again!");
                                            App.SongList.UpdateListFromSelection(0);
                                            timer.cancel();
                                            handelRepeatAll();
                                        }});            
                                    }
                                       if(CurrentStatus==Status.STOPPED){
                                        timer.cancel();
                                        this.cancel();
                                       }
                                
                            }
                        };
                        timer.schedule(tasker, 1000, 1000);
                        
                        
                 }
                 }
                public void handelRepeatOne(){
                    //System.out.println("HandelReapeatOne");
                    if(App.Buttons.REPEAT.getState().equals(TristateState.INDETERMINATE)){
                        if(App.MP3Player.mediaPlayer!=null){
                            
                        Status CurrentStatus=App.MP3Player.mediaPlayer.getStatus();
                        //System.out.println(CurrentStatus);
                            
                            if(CurrentStatus==Status.PAUSED){
                                App.MP3Player.mediaPlayer.play();
                            }else{//PLAYING OR STOPPED
                                
                                App.MP3Player.clearMedia();
                                if(CurrentStatus==Status.STOPPED){
                                    App.MP3Player.setMedia(App.SongList.buildSongList());
                                
                                }
                                if(CurrentStatus==Status.PLAYING || CurrentStatus==Status.UNKNOWN){
                                    App.SongList.UpdateListFromSelection(App.SongList.getCurrentSongIndex());
                                    App.MP3Player.setMedia(App.SongList.buildSongList());
                                }
                                App.MP3Player.mediaPlayer.dispose();
                                App.MP3Player.repeatSong();
                                App.MP3Player.mediaPlayer.setOnEndOfMedia(new Runnable() {
                                @Override
                                    public void run() {
                                        handelRepeatOne();
                                    }
                                }); 
                            } 
                        }else{//MP3Player.mediaPlayer is Not Null
                                App.SongList.UpdateListFromSelection(App.SongList.getCurrentSongIndex());
                                App.MP3Player.setMedia(App.SongList.buildSongList());
                                App.MP3Player.repeatSong();
                                App.MP3Player.mediaPlayer.setOnEndOfMedia(new Runnable() {
                                @Override
                                    public void run() {
                                        handelRepeatOne();
                                    }
                                });
                                timer.schedule(tasker, 0 , 200);
                                }
                    }else{//No Longer Repeating
                        System.out.println("Exit Handel Repeat One");
                        handelStop();
                        return;
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
                        int selectedItem = (int) App.SongList.list.getSelectedIndex();
                 
                        if(App.MP3Player.mediaPlayer!=null){
                            Status CurrentStatus=App.MP3Player.mediaPlayer.getStatus();
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
                    App.PB.setCurrentTime(App.MP3Player.mediaPlayer.getCurrentTime().toSeconds());
                    App.PB.setSongLength(App.MP3Player.mediaPlayer.getMedia().getDuration().toSeconds());
                    App.PB.setString(App.MP3Player.getCurrentSongName());
                    //System.out.println(App.PB.SongLength);
                    //System.out.println(App.PB.CurrentTime);
                    App.PB.UpdatePB();
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
     
