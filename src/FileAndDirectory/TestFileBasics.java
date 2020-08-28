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
//import java.io.File;
import java.io.File;
import java.io.IOException;
import java.util.List;
public class TestFileBasics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
//        // File[] directories = new File("/your/path/").listFiles(File::isDirectory);
        String path="C:\\Users\\wellsnp\\Documents\\PlayMusicLib";

        FolderInfo Folders = new FolderInfo() ;
        Folders.setPath(path);
        //Folders.RunGlobalDuplicationCheck();
        
        //System.out.println("Dupliaction List Number of Elements");
        //System.out.println(Folders.DupDetector.DuplicationList.size());
        
        //Folders.DeleteDuplication();
        //System.out.println("Duplicatd Songs Remaning: "+Folders.DupDetector.dupcnt);
        
        
        }
}
            
        
            
        
  