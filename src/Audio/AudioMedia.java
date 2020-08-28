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
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class AudioMedia {
       public Media Song; 
       
       public ObservableList<Media> mediaList;
       public boolean status;
       public MediaPlayer mediaPlayer;
        public AudioMedia(){
          //JFXPanel Is only here to make this work!
          //https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
          status=false;
         
          this.mediaList = FXCollections.observableArrayList();  
          final JFXPanel fxPanel = new JFXPanel();
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
              if (this.mediaList.isEmpty()){
                  System.out.println("No Media List");
                  return;
              }
              this.mediaPlayer = new MediaPlayer(mediaList.remove(0));
              this.mediaPlayer.play();

              this.mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                    public void run() {
                    playSongList();
                    }
              });
        }
              
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
    


    
    
    
