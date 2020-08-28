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
//Make a Scrollable List of Artists.
public class ArtistList extends BaseList{
    //FolderInfo Folders;
    //File[] Artists;
   
    AlbumList Albums;
    public ArtistList(AlbumList Albums){
          
          this.Albums=Albums;
            
}
    
    @Override
    public void initMouseAdapter(JList list){  
       mouseListener = new MouseAdapter() {
      @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedItem = (int) list.getSelectedIndex();
                    System.out.println(selectedItem);
                    if(ScrollListFiles!=null){
                    System.out.println(ScrollListFiles.get(selectedItem));
                    Albums.UpdateList(ScrollListFiles.get(selectedItem));
                    }
                }
            }
        };
    }

 public void UpdateList(FolderInfo Folders){
        //this.ScrollListNames = new ArrayList<>();
        this.ScrollListFiles = new ArrayList<>();
        listmodel.removeAllElements();
        for(File CurrentArtist:Folders.Artists){
            //ScrollListNames.add(CurrentArtist.getName());
            ScrollListFiles.add(CurrentArtist);    
            listmodel.addElement(CurrentArtist.getName());
            
        }

 }
}



