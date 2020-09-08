package FileAndDirectory;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wellsnp
 */
public class FolderInfo {
    public String path;
    public File[] Artists;
    FileFilters FileFilter;
    DuplicationDetection DupDetector;
    public int Albums;
    public int Songs;
    public int RandomSongs;
    public int mp3s;
    public int wavs;
   
    
      public FolderInfo(){
      //Default Constructor
      //Do Nothing
      
      }  
    
    
      public final void setPath(String inputStr){
         this.path=inputStr;
         this.initalize();
      }
      public void initalize(){
        this.Artists= new File(this.path).listFiles(File::isDirectory);
        this.FileFilter = new FileFilters();
        this.DupDetector = new DuplicationDetection();
        this.cntAlbums();
        this.cntSongs();
        this.cntRandSongs();
        this.printNumArtists();
        this.printNumAlbums();
        this.printNumSongs();
      }
              
      private void printPath(){
         System.out.println(this.path);
      }
      public final void printNumArtists(){
         System.out.print("Path Contains: ");
         System.out.print(this.Artists.length);
         System.out.println(" Artists ");
      } 
      public final void printNumAlbums(){
         System.out.print("Path Contains: ");
         System.out.print(this.Albums);
         System.out.println(" Albums ");
      }
      public final void printNumSongs(){
         System.out.print("Path Contains: ");
         System.out.print(this.Songs);
         System.out.println(" Songs In Albums");
         System.out.print("Path Contains: ");
         System.out.print(this.RandomSongs);
         System.out.println(" Random Songs In Artist Folders");
         System.out.print("Path Contains: ");
         System.out.print(this.mp3s);
         System.out.println(" .mp3 files");
         System.out.print("Path Contains: ");
         System.out.print(this.wavs);
         System.out.println(" .wav files");
      } 
      public final void printFileArray(File[] Files){
            for( File FileCnt:Files){
                System.out.println("\t"+FileCnt.getName());
            
            }
       }
      
    //Simple Test To Print All The Artist  
    public void printArtists(){
           for( File ArtistCnt:Artists){
               System.out.println("\033[1m"+ArtistCnt.getName());
               printAlbums(ArtistCnt);
               System.out.println("");
           }
       }
    
    private void printAlbums(File Artist){
         
               File[] TheseAlbums = new File(Artist.getAbsolutePath()).listFiles(File::isDirectory);
                for(File AlbumCnt:TheseAlbums){
                        System.out.println("\t"+AlbumCnt.getName());
                    }
           //}    
        }    
    //Designed to Count all the Albums within The Artist Folders 
    private void cntAlbums(){
                    for( File ArtistCnt:Artists){
                        File[] Albums = new File(ArtistCnt.getAbsolutePath()).listFiles(File::isDirectory);
                        this.Albums+=Albums.length;    
                    }
    }    
    //Designed to Count all the Songs within an album folder    
    private void cntSongs(){
              for( File ArtistCnt:Artists){
                        File[] Albums = new File(ArtistCnt.getAbsolutePath()).listFiles(File::isDirectory);
                        for(File AlbumnCnt:Albums){
                              File[] Songs = new File(AlbumnCnt.getAbsolutePath()).listFiles(File::isFile);
                              File[] MP3   = new File(AlbumnCnt.getAbsolutePath()).listFiles(this.FileFilter.mp3Filter);
                              File[] WAV   = new File(AlbumnCnt.getAbsolutePath()).listFiles(this.FileFilter.wavFilter);
                              this.Songs+=Songs.length;  
                              this.mp3s+=MP3.length;  
                              this.wavs+=WAV.length; 
                        }
                        
                    }
        
        }    
    //Designed to Count all the Songs within an Artist folder But Not in a Album Folder    
    private void cntRandSongs(){
              for( File ArtistCnt:Artists){
                        File[] Songs = new File(ArtistCnt.getAbsolutePath()).listFiles(File::isFile);
                        File[] MP3   = new File(ArtistCnt.getAbsolutePath()).listFiles(this.FileFilter.mp3Filter);
                        File[] WAV   = new File(ArtistCnt.getAbsolutePath()).listFiles(this.FileFilter.wavFilter);
                              
                              this.mp3s+=MP3.length;  
                              this.wavs+=WAV.length; 
                              this.RandomSongs+=Songs.length;  
                        }
                        
                    }  
              
    public void ListAllArtistAlbums(){
           for( File ArtistCnt:Artists){
               //System.out.println(ArtistCnt);
               printAlbums(ArtistCnt);
           }
    }

    public List<File> FindArtists(String FirstLetter){
            int cnt=0;
            List<File>  ArtistList = new ArrayList<>();
                for( File ArtistCnt:Artists){
                    if(FirstLetter.equals(String.valueOf(ArtistCnt.getName().charAt(0)))){
                        System.out.println(ArtistCnt.getName());
                        ArtistList.add(ArtistCnt);
                        cnt=cnt+1;
                        }
                 }
                    return ArtistList;
    }
    
    //Return a File Array The Albums of An Artist
    public File[] ListAlbums(File Artist){
            File[] Albums = new File(Artist.getAbsolutePath()).listFiles(File::isDirectory);
            return Albums;
            
        }    
  
    //Return a File Array of Songs in An Album
    public File[] ListSongs(File Album){
            File[] Songs = new File(Album.getAbsolutePath()).listFiles(File::isFile);
            return Songs;
         
    }    
      
    public void RunGlobalDuplicationCheck() throws IOException{
        for(File CurrentArtist:Artists){
            File[] AlbumList = ListAlbums(CurrentArtist);
              for( File CurrentAlbum:AlbumList){
                  System.out.println("Current Album: "+CurrentAlbum.getName());
                  File[] TheseSongs=ListSongs(CurrentAlbum);
                  DupDetector.DuplicationList.addAll(DupDetector.FindDuplicatedSongs(TheseSongs));
              }
                    
        }
        DeleteDuplication();
       System.out.println(this.DupDetector.dupcnt);
}
    
    public void DeleteDuplication() throws IOException{
        for(File CurrentSong:DupDetector.DuplicationList){
                if(CurrentSong.delete()){
                 //System.out.println("Deleted the file: " + CurrentSong.getName());
                 DupDetector.dupcnt-=1;
                }else{
                 System.out.println("Failed to delete the file: "+ CurrentSong.getAbsolutePath());
                }
                
                    
        }
       //System.out.println(this.DupDetector.dupcnt);
}
}

