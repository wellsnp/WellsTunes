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
import javax.swing.*;

import java.awt.*;
import FileAndDirectory.FolderInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public final class WellsTunesGUI {
        FolderInfo Folders;
        AAList ArtistList;
        AAList AlbumList;
        SongList   SongList;
        AudioMedia MP3Player;
        Menus Menu;
        Buttons Buttons;
        Boxes Boxes;
        Panels Panels;
        JFrame frame;
        Actions ActionHandler;
        ProgressBar PB;
        LibraryFilter Search;
        public static InitThread InitGUIThread;
        public static LibCheckThread CheckLibThread;
        WellsTunesGUI(){
        InitGUIThread = new InitThread();
        CheckLibThread = new LibCheckThread(); 
        CheckLibThread.setPriority(Thread.MAX_PRIORITY);
        MP3Player = new AudioMedia();
        Folders = new FolderInfo();
        Panels = new Panels();
        Boxes = new Boxes();
        PB=new ProgressBar();
        ActionHandler = new Actions(this);
        //These Objects Need To Be Registered With The ActionHandler. 
        SongList = new SongList(ActionHandler);
        AlbumList = new AAList(ActionHandler);
        ArtistList = new AAList(ActionHandler);
        Menu = new Menus(ActionHandler);
        Buttons = new Buttons(ActionHandler);  
        Search = new LibraryFilter(ActionHandler);

        }
     
        public static void main(String args[]) throws InterruptedException, IOException{
      // JFrame frame = new JFrame("WellsTunes v0.1");
       WellsTunesGUI App=new WellsTunesGUI();
       InitGUIThread.start();
       CheckLibThread.start();
       boolean check=true;
       while(check){
            check=CheckLibThread.isAlive();
   
       }
       System.out.println("LibCheck Done");

    }
    public void initGUI() throws InterruptedException{
        this.initFrame();
        //Thread.sleep(10);
        this.initLeftPanel();
        //Thread.sleep(10);
        this.initCenterPanel();
        //Thread.sleep(10);
        this.initBottomPanel();
        //Thread.sleep(10);
        this.initRightPanel();
    }
    private void initFrame(){
       frame = new JFrame("WellsTunes v0.1");
       frame.setPreferredSize(new Dimension(1600,700));
       frame.setLayout(new BorderLayout());
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       //frame.setResizable(false);
       JFrame.setDefaultLookAndFeelDecorated(true);
 
       
       frame.getContentPane().add(BorderLayout.NORTH, Menu.menubar);
       frame.getContentPane().add(BorderLayout.CENTER,Panels.pCenter);
       frame.getContentPane().add(BorderLayout.EAST,Panels.pRight);
       frame.getContentPane().add(BorderLayout.WEST,Panels.pLeft);
       frame.getContentPane().add(BorderLayout.SOUTH,Panels.pBottom);

       frame.pack();
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
    }
    private void initLeftPanel(){
       Panels.pLeft.add(Boxes.ArtistInfo.Label);
       Panels.pLeft.add(Boxes.ArtistInfo.Field);
       Panels.pLeft.add(ArtistList);
       Panels.pLeft.add(Boxes.AlbumInfo.Label);
       Panels.pLeft.add(Boxes.AlbumInfo.Field);
       Panels.pLeft.add(AlbumList);
       //Panels.pLeft.add(Boxes.Box3.Field);
       frame.repaint();
    }
    private void initCenterPanel(){
       
 
        
        
       Panels.pCenter.add(SongList.getTrackTag());
       Panels.pCenter.add(SongList.getSongTag());
       Panels.pCenter.add(SongList.getAlbumTag());
       Panels.pCenter.add(SongList.getArtistTag());
       Panels.pCenter.add(SongList.getLengthTag());
       frame.repaint();
    }
    private void initBottomPanel(){
       //Panels.pBottom.c.anchor = GridBagConstraints.CENTER; //bottom of space
       Panels.pBottom.c.fill=GridBagConstraints.HORIZONTAL;
       Panels.pBottom.c.gridy=0;
       Panels.pBottom.c.gridx=0;       
       Panels.pBottom.add(Buttons.PLAY,Panels.pBottom.c);
       Panels.pBottom.c.gridx=1;
       Panels.pBottom.add(Buttons.PAUSE,Panels.pBottom.c);
       Panels.pBottom.c.gridx=2;
       Panels.pBottom.add(Buttons.STOP,Panels.pBottom.c);
       Panels.pBottom.c.gridx=3;
       Panels.pBottom.add(Buttons.SHUFFLE,Panels.pBottom.c);
       Panels.pBottom.c.gridx=4;
       Panels.pBottom.add(Buttons.REPEAT,Panels.pBottom.c);
       Panels.pBottom.c.gridy=5;
       Panels.pBottom.c.gridx=0; 
       Panels.pBottom.c.gridwidth = 5;
       //Panels.pBottom.c.weighty = 1.0;   //request any extra vertical space
       Panels.pBottom.c.insets = new Insets(20,0,0,0);  //top padding
      // Panels.pBottom.c.anchor = GridBagCo2nstraints.PAGE_END; //bottom of space
       Panels.pBottom.add(PB,Panels.pBottom.c);
       frame.repaint();
    }
    private void initRightPanel(){
       Panels.pRight.add(Search.SearchBox);
       Panels.pRight.add(Search.SongName);
       Panels.pRight.add(Search.AlbumName);
       Panels.pRight.add(Search.ArtistName);
       //Panels.pRight.add(Boxes.Box3.Field);
            
       
       frame.repaint();
    }
    
    public void checkMusicLib() throws InterruptedException, FileNotFoundException, IOException{
        while(true){
               
                String path_save="/WellsTunesPathInfo.txt";
                File tempDir = new File("./"+path_save);
               
                if(tempDir.exists()){
                    BufferedReader br = new BufferedReader(new FileReader(tempDir)); 
                    String path=br.readLine();
                    Folders.setPath(path);
                    //Thread.sleep(300); 
                }
               if(Folders.getPath()!=null){
                        
                        String Num = Integer.toString(Folders.getArtists().length);
                        
                        Boxes.ArtistInfo.Field.setText(Num);
                        Boxes.AlbumInfo.Field.setText(Integer.toString(Folders.getAlbums()));
                        //Library is the Second component and Java is a zero based index code. 
                        Menu.menubar.getComponent(1).setEnabled(true);
                        Menu.menubar.getComponent(2).setEnabled(true);
                        System.out.println(Folders.getPath());
                        
                        //ArtistList.UpdateList(new File(Folders.getPath()));
                        //ArtistList.UpdateList(new File(Folders.getPath()));
                        //frame.repaint();
                        ArtistList.UpdateList(new File(Folders.getPath()));
                        frame.repaint();
                        
                      
                        break;
                }else{
                        Thread.sleep(300);
                }
                    
               
       }
    
    }

    public final class InitThread extends Thread {

    @Override
    public void run(){
        try {
            initGUI();
        } catch (InterruptedException ex) {
            Logger.getLogger(WellsTunesGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("My GUI Is Running");
    }
  }
    public final class LibCheckThread extends Thread {

    @Override
    public void run(){
        try {
            System.out.println("Lib Check Is Running");
            checkMusicLib();
            
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(WellsTunesGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }
}
    
