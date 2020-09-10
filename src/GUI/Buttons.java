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
      Button.setPreferredSize(new Dimension (150,30));
      return Button;
    }
    
    private JToggleButton initTButton(String Str,Actions ActionHandler ){
    
        JToggleButton Button = new JToggleButton(Str);
        Button.addItemListener(ActionHandler);
        Button.setPreferredSize(new Dimension (150,30));
        return Button;
    };
    //https://www.javaspecialists.eu/archive/Issue145.html
    private enum TristateState {
  SELECTED {
    public TristateState next() {
      return INDETERMINATE;
    }
  },
  INDETERMINATE {
    public TristateState next() {
      return DESELECTED;
    }
  },
  DESELECTED {
    public TristateState next() {
      return SELECTED;
    }
  };

  public abstract TristateState next();
}
    private class TristateButtonModel extends ToggleButtonModel {
  private TristateState state = TristateState.DESELECTED;

  public TristateButtonModel(TristateState state) {
    setState(state);
  }

  public TristateButtonModel() {
    this(TristateState.DESELECTED);
  }

  public void setIndeterminate() {
    setState(TristateState.INDETERMINATE);
  }

  public boolean isIndeterminate() {
    return state == TristateState.INDETERMINATE;
  }

  // Overrides of superclass methods
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    // Restore state display
    displayState();
  }

  public void setSelected(boolean selected) {
    setState(selected ?
        TristateState.SELECTED : TristateState.DESELECTED);
  }

  // Empty overrides of superclass methods
  public void setArmed(boolean b) {
  }

  public void setPressed(boolean b) {
  }

  void iterateState() {
    setState(state.next());
  }

  private void setState(TristateState state) {
    //Set internal state
    this.state = state;
    displayState();
    if (state == TristateState.INDETERMINATE && isEnabled()) {
      // force the events to fire

      // Send ChangeEvent
      fireStateChanged();

      // Send ItemEvent
      int indeterminate = 3;
      fireItemStateChanged(new ItemEvent(
          this, ItemEvent.ITEM_STATE_CHANGED, this,
          indeterminate));
    }
  }

  private void displayState() {
    super.setSelected(state != TristateState.DESELECTED);
    super.setArmed(state == TristateState.INDETERMINATE);
    super.setPressed(state == TristateState.INDETERMINATE);

  }

  public TristateState getState() {
    return state;
  }
}
}
