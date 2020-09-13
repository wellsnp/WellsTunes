/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author wells
 */
public class LibraryFilter {
    
    JTextField SearchBox;
    ButtonGroup group;
    JRadioButton SongName;
    JRadioButton AlbumName;
    JRadioButton ArtistName;
    
    public LibraryFilter(Actions ActionHandler){
     
        SearchBox = new JTextField();
        SearchBox.setVisible(true);
        SearchBox.setEditable(true);
        //SearchBox.setPreferredSize(new Dimension(100, 50));
        
        SearchBox.addActionListener(ActionHandler);
        
        
        SongName = new JRadioButton("Songs");
        //SongName.setPreferredSize(new Dimension(10, 10));
        AlbumName = new JRadioButton("Albums");
        //AlbumName.setPreferredSize(new Dimension(10, 10));
        ArtistName = new JRadioButton("Artists");
        //ArtistName.setPreferredSize(new Dimension(10, 10));
        
        group = new ButtonGroup();
        group.add(SongName);
        group.add(AlbumName);
        group.add(ArtistName);
        
    
    }
    
}
