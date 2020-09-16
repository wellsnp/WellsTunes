/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Audio;

/**
 *
 * @author wellsnp
 */
import GUI.SongList.SongMedia;
import GUI.TristateButton.TristateState;
import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
public class AudioMedia {
       public Media Song; 
       private SongMedia MP3Media;
       //private ObservableList<Media> mediaList;
       //private ObservableList<String> SongNames;
       private String CurrentSongName;

 
       public boolean status;
       public MediaPlayer mediaPlayer;
        public AudioMedia(){
          //JFXPanel Is only here to make this work!
          //https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
          status=false;
          MP3Media = new SongMedia(); 
          //this.mediaList = FXCollections.observableArrayList();  
          final JFXPanel fxPanel = new JFXPanel();
         }

    public void setMedia(SongMedia media) {
        this.MP3Media = media;
    }
    public void clearMedia() {
        this.MP3Media.getMediaList().clear();
        this.MP3Media.getMediaName().clear();
    }
//    public void setSongNames(ObservableList<String> SongNames) {
//        this.SongNames = SongNames;
//    }
       
    public String getCurrentSongName() {
        return CurrentSongName;
    }       
        public void addSong(File Song){
          this.status=true;
          this.Song= new Media(Song.toURI().toString());
          this.mediaPlayer=new MediaPlayer(this.Song);
          
        }
        public void playSong(){
              mediaPlayer.play();
        }

        public void playSongList(){
              if (this.MP3Media.getMediaList().isEmpty()){
                  System.out.println("No Media List");
                  return;
              }
              this.mediaPlayer = new MediaPlayer(MP3Media.getMediaList().remove(0));
              this.mediaPlayer.setVolume(1.0);
              mediaPlayer.setOnError(() -> System.out.println("Error : " + mediaPlayer.getError().toString()));
              this.CurrentSongName=MP3Media.getMediaName().remove(0);
              this.mediaPlayer.setOnReady(new Runnable(){
                  @Override
                  public void run(){
                  mediaPlayer.play();
                  
              }
              });
              //this.mediaPlayer.play();
               System.out.println("PlaySongList Play");
              this.mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                    public void run() {
                    playSongList();
                    }
              });
        }
        
        public void repeatSong(){
            
              this.mediaPlayer = new MediaPlayer(MP3Media.getMediaList().get(0));
              this.CurrentSongName=MP3Media.getMediaName().get(0);
              this.mediaPlayer.play();
               
            
              };
        
        
              
        public void stopSong(){
               this.mediaPlayer.stop();
        }
        public void pauseSong(){
               this.mediaPlayer.pause();
        }
        public void setStatus(boolean tf){
              this.status=tf;
        }
}
    


    
    
    
