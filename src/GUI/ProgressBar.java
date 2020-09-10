/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;
import javax.swing.JProgressBar;

/**
 *
 * @author wells
 */
public class ProgressBar extends JProgressBar {
    
    
    public ProgressBar(){
    
        this.setValue(0);
        this.setStringPainted(true);
        this.setPreferredSize(new Dimension (500,20));
        //this.setVisible(true);
        
    }
    double SongLength;
    double CurrentTime;

    public void setSongLength(double SongLength) {
        this.SongLength = SongLength;
    }

    public void setCurrentTime(double CurrentTime) {
        this.CurrentTime = CurrentTime;
    }
    
    
    public void UpdatePB(){
    
    int Progress = (int)(100*(CurrentTime/SongLength));
    
    this.setValue(Progress);
    
    }
    
    
}
