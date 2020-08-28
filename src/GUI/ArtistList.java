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


import java.io.File;
import FileAndDirectory.FolderInfo;
import java.util.ArrayList;

public class ArtistList extends BaseList{

public ArtistList(Actions ActionHandler){
          super(ActionHandler);
    }

public void UpdateList(FolderInfo Folders){
        //this.ScrollListNames = new ArrayList<>();
        this.ScrollListFiles = new ArrayList<>();
        listmodel.removeAllElements();
        for(File CurrentArtist:Folders.Artists){
            ScrollListFiles.add(CurrentArtist);    
            listmodel.addElement(CurrentArtist.getName());
        }
 }

}



