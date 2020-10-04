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
     private int dupcnt;
     private final List<File> DuplicationList;
     private final List<String> DuplicationListNames;
     //Class Constuctor
     DuplicationDetection(){
        dupcnt=0;
        DuplicationList = new ArrayList<>();
        DuplicationListNames = new ArrayList<>();
     }
     
    public List<File> FindDuplicatedSongs(File[] Songs) throws IOException{
            int cnt=0;
            //int currentHash;
            //int nextHash;
            List<File>  DuplicatedSongList = new ArrayList<>();
                for( int j=Songs.length-1; j>=0; j-- ){
                      // currentHash=Songs[j].hashCode();  
                    for( int k=j-1; k>=0;k--){
                         
                         if(FileUtils.contentEquals(Songs[j], Songs[k])){
                             cnt=cnt+1;
                             DuplicatedSongList.add(Songs[k]);
                          
                            }
                    }
                }  
                System.out.println("Found: "+cnt+" Song Duplicates");

                System.out.println("");
                this.dupcnt+=cnt;
                return DuplicatedSongList;
    }
    public int getDupcnt() {
        return dupcnt;
     }
    public List<File> getDuplicationList() {
        return DuplicationList;
     }
    public List<String> getDuplicationListNames() {
        return DuplicationListNames;
     } 
    public void addToDupList(List<File> List){
             this.DuplicationList.addAll(List);
        }
    public void addToDupNameList(String Str){
             this.DuplicationListNames.add(Str);
     }
    public void clearDupList(){
             this.DuplicationList.clear();
        }
    public void clearDupListNames(){
             this.DuplicationListNames.clear();
        }
    public void decremetnDupCnt(){
            this.dupcnt-=1;
     }
}

