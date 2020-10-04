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

public class AAList extends BaseList{
    //AAList is Artist&Albums List: Refactored Code To Remove Effectively Duplicate ArtistList and AlbumList;
    public AAList(Actions ActionHandler){
            super(ActionHandler);
    }
    
    @Override
 public void UpdateList(File InputFile){
        FolderInfo Folders = new FolderInfo();
      //Get Albums of Artist
        File[] Dir=Folders.ListDir(InputFile);
        //this.ScrollListNames = new ArrayList<>();
        this.setScrollListFiles(new ArrayList<>());
        if(!listmodel.isEmpty()){
            listmodel.removeAllElements();
        }
        for(File CurrentDir:Dir){
            
            this.addToScrollListFiles(CurrentDir);    
            listmodel.addElement(CurrentDir.getName());
        }

 }
}
  
