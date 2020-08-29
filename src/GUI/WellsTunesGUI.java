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
        WellsTunesGUI(){
        MP3Player = new AudioMedia();
        Folders = new FolderInfo();
        Panels = new Panels();
        Boxes = new Boxes();
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
       App.initGUI(); 
       App.checkMusicLib(); 

    }
    private void initGUI(){
        this.initFrame();
        this.initLeftPanel();
        this.initCenterPanel();
        this.initBottomPanel();
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
       Panels.pCenter.add(SongList);
       
       frame.repaint();
    }
    private void initBottomPanel(){
       Panels.pBottom.add(Buttons.PLAY);
       Panels.pBottom.add(Buttons.PAUSE);
       Panels.pBottom.add(Buttons.STOP);
       
       frame.repaint();
    }
    public void checkMusicLib() throws InterruptedException, FileNotFoundException, IOException{
        while(true){
               
                String path_save="C:\\Users\\wellsnp\\Documents\\NetBeansProjects\\TestFileStructureRead\\WellsTunesPathInfo.txt";
                File tempDir = new File(path_save);
                if(tempDir.exists()){
                    BufferedReader br = new BufferedReader(new FileReader(tempDir)); 
                    String path=br.readLine();
                    Folders.setPath(path);
                    Thread.sleep(6);
                }
               if(Folders.path!=null){
                        Thread.sleep(3);
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


}
    
