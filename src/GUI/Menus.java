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
    Actions ActionHandler;
    Menus(Actions ActionHandler){
        menubar = new JMenuBar();
        File = new FileMenu(menubar,ActionHandler);
        Lib  = new LibMenu(menubar,ActionHandler);
        Player  = new PlayerMenu(menubar,ActionHandler);
        Help  = new HelpMenu(menubar,ActionHandler);
        About  = new AboutMenu(menubar,ActionHandler);
        this.ActionHandler=ActionHandler;
        }
                        


/////////Menue Classes
       class BaseMenu {
             Actions ActionHandler;
                BaseMenu(Actions ActionHandler){
                    this.ActionHandler=ActionHandler;
                }
        }
       class FileMenu extends BaseMenu {
        JMenu file;
        MenuItem MusicPath;
        MenuItem Quit;
                    
        FileMenu(JMenuBar menubar,Actions ActionHandler ){
         super(ActionHandler);
         file = new JMenu("File");
         MusicPath = new MenuItem("MusicPath",this.ActionHandler);
         Quit = new MenuItem("Quit",this.ActionHandler);
         file.add(MusicPath);
         file.add(Quit);     
         menubar.add(file);
        }
 }
 
       class LibMenu extends BaseMenu{
       JMenu Lib;
       MenuItem Duplicates;
       
       LibMenu(JMenuBar menubar, Actions ActionHandler){
         super(ActionHandler);
         Lib = new JMenu("Library");
         Duplicates = new MenuItem("Check Duplicates",this.ActionHandler);
         Lib.add(Duplicates);
         Lib.setEnabled(false);
         menubar.add(Lib);
        
        }
 }             

       class HelpMenu extends BaseMenu{
        JMenu Help;
        MenuItem NoHelp ;
                          
        HelpMenu(JMenuBar menubar,Actions ActionHandler){
         super(ActionHandler);
         Help = new JMenu("Help");
         NoHelp = new MenuItem("NoHelp",this.ActionHandler);
         Help.add(NoHelp);
         menubar.add(Help);
        }
       }
              
       class AboutMenu extends BaseMenu {
        JMenu About;
        MenuItem version ;
        MenuItem LibInfo ;
        
                          
        AboutMenu(JMenuBar menubar,Actions ActionHandler){
         //menubar = new JMenuBar();
         super(ActionHandler);
         About = new JMenu("About");
         version = new MenuItem("version",this.ActionHandler);
         LibInfo = new MenuItem("LibInfo",this.ActionHandler);
         About.add(version);
         About.add(LibInfo);
         menubar.add(About);
        }
       }

       class PlayerMenu extends BaseMenu {
       JMenu Player;
       MenuItem Play;
       MenuItem Pause;
       MenuItem Stop;
       
       PlayerMenu(JMenuBar menubar,Actions ActionHandler){
         super(ActionHandler);
         Player = new JMenu("Controls");
         Play = new MenuItem("Play",this.ActionHandler);
         Pause = new MenuItem("Pause",this.ActionHandler);
         Stop = new MenuItem("Stop",this.ActionHandler);
         Player.add(Play);
         Player.add(Pause);
         Player.add(Stop);
         Player.setEnabled(false);
         menubar.add(Player);
        
        }   
       }
       
       
    
        
//Menu Item
 
        //Generic Menu Item. All Actions Are Based on Which Menue Item is Slected
         class MenuItem extends JMenuItem{
                   
                public MenuItem(String Text, Actions ActionHandler){
                    super(Text);
                    addActionListener(ActionHandler);
                }
        }
}
