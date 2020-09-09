/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Audio.AudioMedia;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.*;

/**
 *
 * @author wellsnp
 */
public class Buttons  {
    
    JButton STOP;
    JButton PLAY;
    JButton PAUSE;
    JToggleButton REPEAT;
    JToggleButton SHUFFLE;
    AudioMedia MP3Player;
    Menus Menus;
    public Buttons(Actions ActionHandler){
    this.MP3Player=MP3Player;
    
    STOP = this.initButton("STOP",ActionHandler);
    PLAY = this.initButton("PLAY",ActionHandler);
    PAUSE = this.initButton("PAUSE",ActionHandler);
    SHUFFLE = this.initTButton("Shuffle/Normal",ActionHandler);
    REPEAT = this.initTButton("Repeat On/Off",ActionHandler);
    }
        
    private JButton initButton(String Str,Actions ActionHandler){
      JButton Button = new JButton(Str);
      Button.addActionListener(ActionHandler);
      return Button;
    }
    
    private JToggleButton initTButton(String Str,Actions ActionHandler ){
    
        JToggleButton Button = new JToggleButton(Str);
        Button.addItemListener(ActionHandler);
        return Button;
    };
 
}
