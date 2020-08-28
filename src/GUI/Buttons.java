/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Audio.AudioMedia;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author wellsnp
 */
public class Buttons implements ActionListener {
    
    JButton STOP;
    JButton PLAY;
    JButton PAUSE;
    JToggleButton REPEAT;
    JToggleButton SHUFFLE;
    AudioMedia MP3Player;
    Menus Menus;
    public Buttons(AudioMedia MP3Player, Menus Menus){
    this.MP3Player=MP3Player;
    this.Menus=Menus;
    STOP = this.initButton("STOP");
    PLAY = this.initButton("PLAY");
    PAUSE = this.initButton("PAUSE");
    }
    
    
    
    private JButton initButton(String Str){
      JButton Button = new JButton(Str);
      Button.addActionListener(this);
      return Button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
       Object choice = e.getSource();
       if(choice.equals(STOP)){
            //Menus.Player.Play.handelStop();
       }
       if(choice.equals(PLAY)){
            //Menus.Player.Play.handelPlay();
       }
       if(choice.equals(PAUSE)){
            //Menus.Player.Play.handelPause();
       }
                 
    }
    
    
    
}
