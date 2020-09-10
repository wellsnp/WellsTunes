/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Audio.AudioMedia;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.*;
import javax.swing.JToggleButton.ToggleButtonModel;

/**
 *
 * @author wellsnp
 */
public class Buttons  {
    
    JButton STOP;
    JButton PLAY;
    JButton PAUSE;
    TristateButton REPEAT;
    JToggleButton SHUFFLE;
    AudioMedia MP3Player;
    Menus Menus;
    public Buttons(Actions ActionHandler){
    this.MP3Player=MP3Player;
    
    STOP = this.initButton("STOP",ActionHandler);
    PLAY = this.initButton("PLAY",ActionHandler);
    PAUSE = this.initButton("PAUSE",ActionHandler);
    SHUFFLE = this.initTButton("Shuffle/Normal",ActionHandler);
    REPEAT = this.initTriButton("REPEAT ALL","REPEAT ONE","REPEAT OFF",ActionHandler);
    }
        
    private JButton initButton(String Str,Actions ActionHandler){
      JButton Button = new JButton(Str);
      Button.addActionListener(ActionHandler);
      Button.setPreferredSize(new Dimension (150,30));
      return Button;
    }
    //Toggle Buttons
    private JToggleButton initTButton(String Str,Actions ActionHandler ){
    
        JToggleButton Button = new JToggleButton(Str);
        Button.addItemListener(ActionHandler);
        Button.setPreferredSize(new Dimension (150,30));
        return Button;
    };


    private TristateButton initTriButton(String Str1,String Str2,String Str3,Actions ActionHandler ){
    
        TristateButton Button = new TristateButton(Str1,Str2,Str3);
        Button.addMouseListener(ActionHandler);
        Button.setPreferredSize(new Dimension (150,30));
        return Button;
    };
}
