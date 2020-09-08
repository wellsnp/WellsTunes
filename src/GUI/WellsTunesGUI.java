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
        ArtistList ArtistList;
        AlbumList  AlbumList;
        SongList   SongList;
        AudioMedia MP3Player;
        Menus Menu;
        Buttons Buttons;
        Boxes Boxes;
        Panels Panels;
        JFrame frame;
        Actions ActionHandler;
        ProgressBar PB;
        public static InitThread InitGUIThread;
        public static LibCheckThread CheckLibThread;
        WellsTunesGUI(){
        InitGUIThread = new InitThread();
        CheckLibThread = new LibCheckThread(); 
        MP3Player = new AudioMedia();
        Folders = new FolderInfo();
        Panels = new Panels();
        Boxes = new Boxes();
        PB=new ProgressBar();
        ActionHandler = new Actions(this);
        //These Objects Need To Be Registered With The ActionHandler. 
        SongList = new SongList(ActionHandler);
        AlbumList = new AlbumList(ActionHandler);
        ArtistList = new ArtistList(ActionHandler);
        Menu = new Menus(ActionHandler);
        Buttons = new Buttons(ActionHandler);        

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
    }
    private void initFrame(){
       frame = new JFrame("WellsTunes v0.1");
       frame.setPreferredSize(new Dimension(1600,1000));
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
       Panels.pLeft.add(Boxes.Box3.Field);
       frame.repaint();
    }
    private void initCenterPanel(){
       Panels.pCenter.add(SongList.TrackTag);
       Panels.pCenter.add(SongList);
       Panels.pCenter.add(SongList.AlbumTag);
       Panels.pCenter.add(SongList.ArtistTag);
       Panels.pCenter.add(SongList.LengthTag);
       frame.repaint();
    }
    private void initBottomPanel(){
       Panels.pBottom.add(Buttons.PLAY);
       Panels.pBottom.add(Buttons.PAUSE);
       Panels.pBottom.add(Buttons.STOP);
       Panels.pBottom.add(PB);
       frame.repaint();
    }
    public void checkMusicLib() throws InterruptedException, FileNotFoundException, IOException{
        while(true){
               
                String path_save="C:\\Users\\wells\\OneDrive\\Documents\\NetBeansProjects\\WellsTunes\\WellsTunesPathInfo.txt";
                File tempDir = new File(path_save);
                if(tempDir.exists()){
                    BufferedReader br = new BufferedReader(new FileReader(tempDir)); 
                    String path=br.readLine();
                    Folders.setPath(path);
                    Thread.sleep(300);
                }
               if(Folders.path!=null){
                        
                        String Num = Integer.toString(Folders.Artists.length);
                        
                        Boxes.ArtistInfo.Field.setText(Num);
                        Boxes.AlbumInfo.Field.setText(Integer.toString(Folders.Albums));
                        //Library is the Second component and Java is a zero based index code. 
                        Menu.menubar.getComponent(1).setEnabled(true);
                        Menu.menubar.getComponent(2).setEnabled(true);
                        ArtistList.UpdateList(Folders);
                        frame.repaint();
                        
                        
                        break;
                }else{
                        Thread.sleep(300);
                }
                    
               
       }
    
    }


    public final class InitThread extends Thread {

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

    public void run(){
        try {
            checkMusicLib();
            System.out.println("Lib Check Is Running");
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(WellsTunesGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  }
}
    
