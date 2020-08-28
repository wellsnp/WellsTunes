/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileAndDirectory;

/**
 *
 * @author wellsnp
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
public class DuplicationDetection {
     int dupcnt;
     List<File> DuplicationList;
     //Class Constuctor
     DuplicationDetection(){
        dupcnt=0;
        DuplicationList = new ArrayList<>();
     }
     
     public List<File> FindDuplicatedSongs(File[] Songs) throws IOException{
            int cnt=0;
            //int currentHash;
            //int nextHash;
            List<File>  DuplicatedSongList = new ArrayList<>();
                for( int j=Songs.length-1; j>=0; j-- ){
                      // currentHash=Songs[j].hashCode();  
                    for( int k=j-1; k>=0;k--){
                         //nextHash=Songs[k].hashCode();
                          //System.out.println("Current Hash: "+currentHash);
                          //System.out.println("Next Hash: "+nextHash);
                          //System.out.println("");
                          // System.out.println("Found: "+cnt+" Song Duplicates");
                         if(FileUtils.contentEquals(Songs[j], Songs[k])){
                             cnt=cnt+1;
                             DuplicatedSongList.add(Songs[k]);
                            }
                    }
                }  
                
                System.out.println("Found: "+cnt+" Song Duplicates");
                //for(int j=0;j<DuplicatedSongList.size(); j++){
                //    System.out.println("\t"+DuplicatedSongList.get(j).getName());
                //}
                System.out.println("");
                  this.dupcnt+=cnt;
                  return DuplicatedSongList;
    }
}
