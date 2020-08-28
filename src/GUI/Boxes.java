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
import FileAndDirectory.FolderInfo;
import javax.swing.*;
import java.awt.*;

public class Boxes {
    //FolderInfo Folders;
    AlbumTextBox AlbumInfo = new AlbumTextBox();
    ArtistTextBox ArtistInfo = new ArtistTextBox();
    BaseTextBox   Box3 = new BaseTextBox("");
    BaseTextBox   Box4 = new BaseTextBox("");
    Boxes(){
        //this.Folders=Folders;
    
    }
    
    
    class BaseTextBox {
    //File Menus
        //JMenuBar menubar;
        
        JTextField Field = new JTextField(); 
        
            BaseTextBox(String txt){
            JLabel Label = new JLabel(txt);
            Field.setPreferredSize(new Dimension(100, 500)); 
            Field.setEditable(false);
        }
    }
    
    
    class AlbumTextBox {
    //File Menus
        //JMenuBar menubar;
         JLabel Label = new JLabel("Number of Albums");
        JTextField Field = new JTextField(); 
        
        AlbumTextBox(){
            
            Field.setPreferredSize(new Dimension(100, 20));
            Field.setEditable(false);
        }
    }

   
    class ArtistTextBox {
    //File Menus
        //JMenuBar menubar;
        JLabel Label = new JLabel("Number of Artists");
        JTextField Field = new JTextField(); 
        
        ArtistTextBox(){
            
            Field.setPreferredSize(new Dimension(100, 20)); 
            Field.setEditable(false);
        }
    }
}

 
       
