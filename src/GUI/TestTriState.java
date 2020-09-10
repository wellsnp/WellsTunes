/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

/**
 *
 * @author wells
 */
public class TestTriState {
      TristateButtonModel Button;
      public TestTriState(){
        Button = new TristateButtonModel() ;
      }

               
       
       public static void main(String[] args) {
      TestTriState ec = new TestTriState();
      JButton Button2 = new JButton();
      JFrame frame = new JFrame("Demo");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel p = new JPanel();
      
      
      ec.Button.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent e){
                    ec.Button.iterateState();
                }
      
              });
      
      
      frame.add(ec.Button);
      frame.setSize(500, 250);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
    
    
    
       //https://www.javaspecialists.eu/archive/Issue145.html
    public enum TristateState {
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
    public final class TristateButtonModel extends JToggleButton{
  private TristateState state = TristateState.DESELECTED;
  private String State1Str = "Repeat All";
  private String State2Str = "Repeat One";
  private String State3Str = "Repeat Off";

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
    //displayState();
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
    //displayState();
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

//  private void displayState() {
//   
//    //super.setSelected(state != TristateState.DESELECTED);
//   //super.setArmed(state == TristateState.INDETERMINATE);
//    //super.setPressed(state == TristateState.INDETERMINATE);
//
//  }

  public TristateState getState() {
    return state;
  }
}
}

