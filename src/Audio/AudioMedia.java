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
import GUI.SongList;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.embed.swing.JFXPanel;
import java.net.URL;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.MediaPlayer.Status;
public class AudioMedia {
       public Media Song; 
       
       public ObservableList<Media> mediaList;
       public boolean status;
       public MediaPlayer mediaPlayer;
        public AudioMedia(){
          //JFXPanel Is only here to make this work!
          //https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
          status=false;
          //this.SongList=Songs;
          this.mediaList = FXCollections.observableArrayList();  
          final JFXPanel fxPanel = new JFXPanel();
          //URL mediaUrl = getClass().getResource(".mp3");
          //String mediaStringUrl = mediaUrl.toExternalForm();
          //Song=new Media(mediaStringUrl);
          //mediaPlayer=new MediaPlayer();
          
         }
        //https://stackoverflow.com/questions/46655056/how-to-play-multiple-consecutive-sound-files-with-javafx
        //Want to Add All Songs of An Album To A List and Then Use A playList method to play through the list.
        //When an album is selected all the songs are automatically added to the list (and hitting play at this point will start playing the list of songs). 
        //If Song List is clicked Before play, we will rebuild the List To Start From the selected index of the List. Then hitting play will play the list of songs).
        
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
    


    
    
    
