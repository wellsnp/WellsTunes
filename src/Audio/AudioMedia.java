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
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
public class AudioMedia {
       
       private SongMedia MP3Media;
       private String CurrentSongName;
       private MediaPlayer mediaPlayer;
        
       public AudioMedia(){
          //JFXPanel Is only here to make this work!
          //https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
          //status=false;
          MP3Media = new SongMedia();     
          final JFXPanel fxPanel = new JFXPanel();
       }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }     
    public void setMedia(SongMedia media) {
        this.MP3Media = media;
    }
    public SongMedia getMedia() {
        return this.MP3Media ;
    }
    public void clearMedia() {
        this.MP3Media.getMediaList().clear();
        this.MP3Media.getMediaName().clear();
    }

       
    public String getCurrentSongName() {
        return CurrentSongName;
    }       

    public void playSongList(){
              if (this.MP3Media.getMediaList().isEmpty()){
                  System.out.println("No Media List");
                  //this.mediaPlayer.dispose();
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
//        public void setStatus(boolean tf){
//              this.status=tf;
//        }
}
    


    
    
    
