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
import FileAndDirectory.FolderInfo;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.MediaPlayer.Status;

public class Menus{
    JMenuBar menubar;
    FileMenu File; 
    LibMenu  Lib;
    PlayerMenu Player;
    HelpMenu Help;
    AboutMenu About;
    FolderInfo Folders;
    AudioMedia MP3Player;
    SongList SongList;
    Menus(FolderInfo Folders, SongList Songs, AudioMedia MP3Player){
        menubar = new JMenuBar();
        File = new FileMenu(menubar);
        Lib  = new LibMenu(menubar);
        Player  = new PlayerMenu(menubar);
        Help  = new HelpMenu(menubar);
        About  = new AboutMenu(menubar);
        this.Folders= Folders;   
        this.MP3Player = MP3Player;
        this.SongList = Songs;
        }
                        


/////////Menue Classes

       class FileMenu {
        JMenu file;
        MenuItem MusicPath;
        MenuItem Quit;
                    
        FileMenu(JMenuBar menubar){
         //menubar = new JMenuBar();
         file = new JMenu("File");
         MusicPath = new MenuItem("MusicPath");
         Quit = new MenuItem("Quit");
         file.add(MusicPath);
         file.add(Quit);     
         menubar.add(file);
        }
 }
 
       class LibMenu {
       JMenu Lib;
       MenuItem Duplicates;
       
       LibMenu(JMenuBar menubar){
         //menubar = new JMenuBar();
         Lib = new JMenu("Library");
         Duplicates = new MenuItem("Check Duplicates");
         Lib.add(Duplicates);
         Lib.setEnabled(false);
         menubar.add(Lib);
        
        }
 }             

       class HelpMenu {
        JMenu Help;
        MenuItem NoHelp ;
                          
        HelpMenu(JMenuBar menubar){
         //menubar = new JMenuBar();
         Help = new JMenu("Help");
         NoHelp = new MenuItem("NoHelp");
         Help.add(NoHelp);
         menubar.add(Help);
        }
       }
              
       class AboutMenu {
        JMenu About;
        MenuItem version ;
        MenuItem LibInfo ;
        
                          
        AboutMenu(JMenuBar menubar){
         //menubar = new JMenuBar();
         About = new JMenu("About");
         version = new MenuItem("version");
         LibInfo = new MenuItem("LibInfo");
         About.add(version);
         About.add(LibInfo);
         menubar.add(About);
        }
       }

       class PlayerMenu {
       JMenu Player;
       MenuItem Play;
       MenuItem Pause;
       MenuItem Stop;
       
       PlayerMenu(JMenuBar menubar){
         //menubar = new JMenuBar();
         Player = new JMenu("Controls");
         Play = new MenuItem("Play");
         Pause = new MenuItem("Pause");
         Stop = new MenuItem("Stop");
         Player.add(Play);
         Player.add(Pause);
         Player.add(Stop);
         Player.setEnabled(false);
         menubar.add(Player);
        
        }   
       }
       
//Menu Item & Actions 
 
        //Generic Menu Item. All Actions Are Based on Which Menue Item is Slected
         class MenuItem extends JMenuItem implements ActionListener{
                   
                public MenuItem(String Text){
                    super(Text);
                    addActionListener(this);
                }
                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("Item clicked: "+e.getActionCommand());    
                    String choice = e.getActionCommand();
                    if (choice.equals("Quit")) {
                      System.exit(0);
                    }
                    else if(choice.equals("MusicPath")){
                            String path="C:\\Users\\wellsnp\\Documents\\PlayMusicLib";
                                 //Folders = new FolderInfo(path) ;
                                 Folders.setPath(path);
                    }
                    else if((choice.equals("Check Duplicates")) & (Folders.path!=null)){
                        try {
                            Folders.RunGlobalDuplicationCheck();
                        } catch (IOException ex) {
                            Logger.getLogger(Menus.class.getName()).log(Level.SEVERE, null, ex);
                        }
                                 
                    }
                    else if((choice.equals("Play")) & (Folders.path!=null)){
                              this.handelPlay();
                    }
                    else if((choice.equals("Stop")) & (Folders.path!=null)){
                              this.handelStop();
                    }
                    else if((choice.equals("Pause")) & (Folders.path!=null)){
                              this.handelPause();
                           
                    }
                    
                    
                    else{
                        System.out.println("Item clicked: "+e.getActionCommand()); 
                    }
                        
                }
                
                
                public void handelPlay(){
                        
                        if(MP3Player.mediaPlayer!=null){
                        Status CurrentStatus=MP3Player.mediaPlayer.getStatus();
                        System.out.println(CurrentStatus);
                            if(CurrentStatus==Status.STOPPED){
                                MP3Player.mediaPlayer.dispose();
                                SongList.buildSongList();
                                MP3Player.playSongList();
                            }  
                            if(CurrentStatus==Status.PAUSED){
                                MP3Player.mediaPlayer.play();
                            } 
                        }else{
                                //System.out.println("MediaPlayerDoesNotExistYet");
                                MP3Player.playSongList(); 
                                }
                }
                public void handelPause(){
                    Status CurrentStatus=MP3Player.mediaPlayer.getStatus();
                        if(CurrentStatus==Status.PLAYING){
                            MP3Player.pauseSong();
                        }      
                }
                public void handelStop(){
                    Status CurrentStatus=MP3Player.mediaPlayer.getStatus();
                        System.out.println(CurrentStatus);
                        if(CurrentStatus==Status.PLAYING){
                            MP3Player.stopSong();
                        }       
                }
        }

}