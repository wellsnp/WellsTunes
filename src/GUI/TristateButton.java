/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
//https://www.javaspecialists.eu/archive/Issue145.html
import java.awt.event.ItemEvent;
import javax.swing.JToggleButton;

/**
 *
 * @author wells
 */
public class TristateButton extends JToggleButton{
  private TristateState state;
  private String State1Str;
  private String State2Str;
  private String State3Str;

  public TristateButton(String Str1, String Str2, String Str3) {
    
    State1Str=Str1;
    State2Str=Str2;
    State3Str=Str3;
    setState(TristateState.DESELECTED);
    setText(State3Str);
  }

  public TristateButton() {
      setState(TristateState.DESELECTED);
  }

  public void setIndeterminate() {
    setState(TristateState.INDETERMINATE);
  }

  public boolean isIndeterminate() {
    return state == TristateState.INDETERMINATE;
  }

  // Overrides of superclass methods
  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    // Restore state display
    displayState();
  }

  @Override
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
    if(getState()==TristateState.SELECTED){
        this.setText(State1Str);
        
    }
    if(getState()==TristateState.INDETERMINATE){
        this.setText(State2Str);
        
    }
    if(getState()==TristateState.DESELECTED){
        this.setText(State3Str);
        
    }
    //System.out.println(getState());
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

  public TristateState getState() {
    return state;
  }
  private void displayState() {
   
    super.setSelected(state != TristateState.DESELECTED);
   //super.setArmed(state == TristateState.INDETERMINATE);
    //super.setPressed(state == TristateState.INDETERMINATE);

  }
  
  public enum TristateState {
  SELECTED {
    @Override
    public TristateState next() {
      return INDETERMINATE;
    }
  },
  INDETERMINATE {
    @Override
    public TristateState next() {
      return DESELECTED;
    }
  },
  DESELECTED {
    @Override
    public TristateState next() {
      return SELECTED;
    }
  };

  public abstract TristateState next();
}
}

