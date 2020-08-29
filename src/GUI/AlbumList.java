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

import java.io.File;
import FileAndDirectory.FolderInfo;

public class AlbumList extends BaseList{
    
    public AlbumList(Actions ActionHandler){
            super(ActionHandler);
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
  
