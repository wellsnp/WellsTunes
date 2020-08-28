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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.io.File;
import FileAndDirectory.FolderInfo;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;

public class AlbumList extends BaseList{
    SongList Songs;
    
    public AlbumList(SongList Songs){
    this.Songs = Songs;
    
    }
              
    @Override
    public void initMouseAdapter(JList list){  
       mouseListener = new MouseAdapter() {
      @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedItem = (int) list.getSelectedIndex();
                    //Updates the Song List Display with All the Songs in the Album
                    Songs.UpdateList(ScrollListFiles.get(selectedItem));     
                    //System.out.println(selectedItem);
                    if(ScrollListFiles!=null){
                    System.out.println(ScrollListFiles.get(selectedItem));
                    }
                }
            }
        };
    }

    @Override
 public void UpdateList(File InputFile){
        FolderInfo Folders = new FolderInfo();
      //Get Albums of Artist
        File[] Albums=Folders.ListAlbums(InputFile);
        //this.ScrollListNames = new ArrayList<>();
        this.ScrollListFiles = new ArrayList<>();
        listmodel.removeAllElements();
        for(File CurrentAlbum:Albums){
            //ScrollListNames.add(CurrentArtist.getName());
            ScrollListFiles.add(CurrentAlbum);    
            listmodel.addElement(CurrentAlbum.getName());
        }

 }
}
  
