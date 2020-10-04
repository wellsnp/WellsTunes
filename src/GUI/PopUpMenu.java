/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author wells
 */
public abstract class PopUpMenu {
    JFrame Frame;
    JButton Button;
    JTextField TextField;
    JPanel Panel;
    JScrollPane ScrollPane;
    
       PopUpMenu() {
        Frame=new JFrame();
        Panel= new JPanel();
        ScrollPane = new JScrollPane();
        Button = new JButton();
        TextField = new JTextField();
       }
            
            public void show(){
                this.Frame.setVisible(true);
            }
            public void close(){
                this.Frame.setVisible(false);
            }
       
    
}

class popMenuDup extends PopUpMenu{
        BoxLayout boxlayout;
        JLabel Label;
        popMenuDup(Actions ActionHandler){
            super();
            Frame.setSize(400, 800);
            Frame.setTitle("Duplication Check");
            boxlayout = new BoxLayout(Panel, BoxLayout.Y_AXIS);
            Panel.setLayout(boxlayout);
            
            Label=new JLabel("Number of Duplicates Found");
            Label.setPreferredSize(new Dimension(400, 50));
            
            ScrollPane.setPreferredSize(new Dimension(400, 600));
            
            Button.setText("Delete");
            Button.setPreferredSize(new Dimension(400, 50));
            Button.addActionListener(ActionHandler);
            
             TextField.setPreferredSize(new Dimension(400, 100));
             
             Panel.add(ScrollPane);
                Panel.add(Label);
                Panel.add(TextField);
                //Panel.add(Run);
                Panel.add(Button);
                Frame.add(Panel);
        }
}

class  popMenuAlbumTags extends PopUpMenu{
              BoxLayout boxlayout;
              TaggTable TrackNum;
              TaggTable SongName;
              
              TaggTable AlbumName;
              TaggTable ArtistName;
              //TaggList GenreName;
          
          popMenuAlbumTags(Actions ActionHandler){
              super();
              Frame.setSize(1200, 1200);
              Frame.setTitle("Album Tags");
              boxlayout = new BoxLayout(Panel, BoxLayout.X_AXIS);
              Panel.setLayout(boxlayout);
              
              //ScrollPane.add(TrackNum);
              
              TrackNum=new TaggTable(ActionHandler);
              TrackNum.setPreferredSize(new Dimension(20,1200));
              SongName=new TaggTable(ActionHandler);
              SongName.setPreferredSize(new Dimension(320,1200));
              AlbumName=new TaggTable(ActionHandler);
              AlbumName.setPreferredSize(new Dimension(420,1200));
              ArtistName=new TaggTable(ActionHandler);
              ArtistName.setPreferredSize(new Dimension(420,1200));
              
//TrackNum.list.enableInputMethods(true);
             //SP=new JScrollPane();
             //SP.setViewportView(TrackNum);
             Panel.add(new JScrollPane(TrackNum));
             Panel.add(new JScrollPane(SongName));
             Panel.add(new JScrollPane(AlbumName)); 
             Panel.add(new JScrollPane(ArtistName)); 
             
             Frame.add(Panel);
              
          }
          
 
          }
          







       
 
 

