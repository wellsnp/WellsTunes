/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
    //Action Choices(Menus Items Only At This Point).
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
                        this.handelPlay();
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
                        this.handelPlay();
                    }
                    if(choice.equals(App.Buttons.PAUSE)){
                        this.handelPause();
                    }
                    else{
                        System.out.println("Item clicked: "+e.getActionCommand()); 
                    }
                        
                }
       
//Action Methods  
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
                            if(CurrentStatus==Status.PLAYING){
                                //this.handelStop();
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
                                timer.schedule(tasker, 0 , 1000);
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
                             App.SongList.UpdateList(App.SongList.CurrentAlbum);
                            
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
        
        
        }

    
        @Override
    public void itemStateChanged(ItemEvent e) {
       Object choice = e.getItem();
       if(choice.equals(App.Buttons.SHUFFLE)) {
        System.out.println("Shuffle Button");
                if(App.Buttons.SHUFFLE.isSelected()){;
                    App.Buttons.SHUFFLE.setText("Shuffle");
                }
                else{
                    App.Buttons.SHUFFLE.setText("Normal");
                }
        }
    }
    
    
    void handelArtists(int clicks)
    {
        if (clicks == 1) {
                    int selectedItem = (int) App.ArtistList.list.getSelectedIndex();
                    System.out.println(selectedItem);
                    if(App.ArtistList.ScrollListFiles!=null){
                    System.out.println(App.ArtistList.ScrollListFiles.get(selectedItem));
                    App.AlbumList.UpdateList(App.ArtistList.ScrollListFiles.get(selectedItem));
                    }
                }
    }
    void handelAlbums(int clicks)
    {
        if (clicks == 1) {
            
                    int selectedItem = (int) App.AlbumList.list.getSelectedIndex();
                    //Updates the Song List Display with All the Songs in the Album
                    App.SongList.UpdateList(App.AlbumList.ScrollListFiles.get(selectedItem));     
                    //App.MP3Player.mediaList.clear();
                    //App.MP3Player.mediaList=App.SongList.buildSongList();
                  
        }
                
    }
    
    void handelSongs(int clicks){
            
            if (clicks == 2) {
                    int selectedItem = (int) App.SongList.list.getSelectedIndex();
                    
                    //Rebuild The Song List and Play From Here:
                    //if(!MP3Player.status){
                    if(App.SongList.ScrollListFiles!=null){
                    System.out.println(selectedItem);
                    }
                     
                     
                     if(App.MP3Player.mediaPlayer!=null){
                        Status CurrentStatus=App.MP3Player.mediaPlayer.getStatus();
                        System.out.println(CurrentStatus);
                            if(CurrentStatus==Status.STOPPED){
                                App.SongList.UpdateListFromSelection(selectedItem);
                                System.out.println("STOPPED and Clicked");
                                 this.handelPlay();
                            }
                            if(CurrentStatus==Status.PLAYING){
                               
                                App.SongList.UpdateListFromSelection(selectedItem);
                                this.handelPlay();
                                
                            }
                            if(CurrentStatus==Status.PAUSED){
                                this.handelPlay();
                            } 
                      }else{
                                App.SongList.UpdateListFromSelection(selectedItem);
                                this.handelPlay();
                                //MP3Player.playSongList();
                      }
                     
               
                }
            }
    
    //Function for updating Current Song Time For Progress Bar
    //Should Be Called In A Time Task event to update periodically.
    

    public void updatePB(){
    
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
                
        
                


//    @Override
//    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}

    
        
