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
//import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.embed.swing.JFXPanel;
public class TestMP3 {
    public static void main(String[] args) {
    final JFXPanel fxPanel = new JFXPanel();
    //Application.launch();
    String path = "C:\\Users\\wellsnp\\Documents\\PlayMusicLib\\The Allman Brothers Band\\Eat A Peach\\";
    String file = "01 Ain't Wastin' Time No More.mp3";
    Media hit = new Media(new File(path+file).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(hit);
    mediaPlayer.play();
}

  
}
