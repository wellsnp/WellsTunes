/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author wellsnp
 */
import FileAndDirectory.FolderInfo;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class Panels {
    FolderInfo Folders;
    LeftPanel  pLeft;
    RightPanel pRight;
    CenterPanel pCenter;
    BottomPanel pBottom;
 
    public Panels(){
     pLeft = new LeftPanel("Library Info: ");
     pRight = new RightPanel("Filler: ");
     pCenter = new CenterPanel("Song Lists: ");
     pBottom = new BottomPanel("");
    }
    
    
    class BasicPanel extends JPanel {
   
       JLabel plabel;    
       Border loweredetched;
       public BasicPanel(){
         plabel = new JLabel("");
         this.add(plabel);
         this.setBackground(Color.DARK_GRAY);
         loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
         this.setBorder(loweredetched);
        }
        
       public void setPanelLabel(String TextLabel){
            this.plabel.setText(TextLabel);
        
        }
        
        public void setPanelLayOut(){
            
        }
    }
    final class LeftPanel extends BasicPanel{
        
        BoxLayout boxlayout;

        public LeftPanel(String TxtLabel) {
            
            this.setPanelLayOut();
            this.setPanelLabel(TxtLabel);
            this.setPreferredSize(new Dimension(300, 800));
            this.setBackground(Color.pink);
            
        }
        @Override
        public void setPanelLayOut(){
            boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            this.setLayout(boxlayout);
        }
    }
    final class CenterPanel extends BasicPanel{
        
        BoxLayout boxlayout;

        public CenterPanel(String TxtLabel) {
            
            this.setPanelLayOut();
            //this.setPanelLabel(TxtLabel);
            this.setPreferredSize(new Dimension(1000, 800));
            this.setBackground(Color.lightGray);
           // 
        }
        //@Override
        public void setPanelLayOut(){
            boxlayout = new BoxLayout(this, BoxLayout.X_AXIS);
            this.setLayout(boxlayout);
        }   
    }
    final class RightPanel extends BasicPanel{
        
        BoxLayout boxlayout;

        public RightPanel(String TxtLabel) {
            
            this.setPanelLayOut();
            this.setPanelLabel(TxtLabel);
            this.setPreferredSize(new Dimension(200, 800));
           
        }
        //@Override
        public void setPanelLayOut(){
            //boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            //this.setLayout(boxlayout);
        }   
    }
    final class BottomPanel extends BasicPanel{
        
        BoxLayout layout;

        public BottomPanel(String TxtLabel) {
            
            this.setPanelLayOut();
            this.setPanelLabel(TxtLabel);
            this.setPreferredSize(new Dimension(1920, 100));
           this.setBackground(Color.lightGray);
        }
        //@Override
        public void setPanelLayOut(){
            //layout = new BoxLayout(this,BoxLayout.X_AXIS);
            //this.setLayout(layout);
        }   
    }   
}



        

