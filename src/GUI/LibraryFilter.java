/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import FileAndDirectory.FolderInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author wells
 */
public class LibraryFilter {
    
    public JTextField SearchBox;
    private final ButtonGroup group;
    public JRadioButton SongName;
    public JRadioButton AlbumName;
    public JRadioButton ArtistName;
    
    public LibraryFilter(Actions ActionHandler){
     
        SearchBox = new JTextField();
        SearchBox.setVisible(true);
        SearchBox.setEditable(true);
        //SearchBox.setPreferredSize(new Dimension(100, 50));
        
        SearchBox.addActionListener(ActionHandler);
        
        
        SongName = new JRadioButton("Songs");
        //SongName.setPreferredSize(new Dimension(10, 10));
        AlbumName = new JRadioButton("Albums");
        AlbumName.setSelected(true);
        //AlbumName.setPreferredSize(new Dimension(10, 10));
        ArtistName = new JRadioButton("Artists");
        //ArtistName.setPreferredSize(new Dimension(10, 10));
        
        group = new ButtonGroup();
        group.add(SongName);
        group.add(AlbumName);
        group.add(ArtistName);
        
    
    }
    
    public ArrayList searchAlbums(FolderInfo Folders){
        Pattern pattern = Pattern.compile(this.SearchBox.getText());
        Matcher matcher; 
       
                ArrayList FilteredScrollListFiles = new ArrayList<>();
                for(File CurrentArtist:Folders.Artists){
                        File[] Albums=Folders.ListAlbums(CurrentArtist);
                        for (File CurrentAlbum:Albums){
                            String CurrentName = CurrentAlbum.getName();
                             matcher=pattern.matcher(CurrentName);
                             if(matcher.find()){
                                 File[] Songs=Folders.ListSongs(CurrentAlbum);
                                 for(File CurrentSong:Songs){
                                     FilteredScrollListFiles.add(CurrentSong);
                                    }
                             }
                        }
                            
                }
                
                
                return FilteredScrollListFiles;
    }

}