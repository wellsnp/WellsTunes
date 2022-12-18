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
import java.io.FilenameFilter;

public class FileFilters {
    
    
        FilenameFilter textFilter = (File dir, String name) -> {
        String lowercaseName = name.toLowerCase();
        return lowercaseName.endsWith(".txt");
        };
    
        FilenameFilter mp3Filter = (File dir, String name) -> {
        String lowercaseName = name.toLowerCase();
        return lowercaseName.endsWith(".mp3");
        };
               
        FilenameFilter wavFilter = (File dir, String name) -> {
        String lowercaseName = name.toLowerCase();
        return lowercaseName.endsWith(".wav");
        };
        
        FilenameFilter wavmp3Filter = (File dir, String name) -> {
        String lowercaseName = name.toLowerCase();
        
        return (lowercaseName.endsWith(".wav") || lowercaseName.endsWith(".mp3")) ;  
        
        
        
        
        };
        

       
};
    
    

