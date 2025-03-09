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
     pRight = new RightPanel("Search Library: ");
     pCenter = new CenterPanel("Song Lists: ");
     pBottom = new BottomPanel("");
    }
    
       class BasicPanel {
       
       JPanel panel; 
       JLabel plabel;    
       Border loweredetched;
       public BasicPanel(){
         panel = new JPanel();  
         plabel = new JLabel("");
         panel.add(plabel);
         panel.setBackground(Color.DARK_GRAY);
         loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
         panel.setBorder(loweredetched);
        }
       
       public void setPanelLabel(String TextLabel){
            this.plabel.setText(TextLabel);
       }
       public void setPanelLayOut(){}
    }
    final class LeftPanel extends BasicPanel{
        
        BoxLayout boxlayout;

        public LeftPanel(String TxtLabel) {
            
            this.setPanelLayOut();
            this.setPanelLabel(TxtLabel);
            panel.setPreferredSize(new Dimension(300, 800));
            panel.setBackground(Color.pink);
            
        }
        @Override
        public void setPanelLayOut(){
            boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
            panel.setLayout(boxlayout);
        }
    }
    final class CenterPanel extends BasicPanel{
        
        BoxLayout boxlayout;

        public CenterPanel(String TxtLabel) {
            
            this.setPanelLayOut();
            //this.setPanelLabel(TxtLabel);
            panel.setPreferredSize(new Dimension(1500, 800));
            panel.setBackground(Color.lightGray);
           // 
        }
        //@Override
        @Override
        public void setPanelLayOut(){
            boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
            panel.setLayout(boxlayout);
        }   
    }
    final class RightPanel extends BasicPanel{
        
        GridLayout gridlayout;

        public RightPanel(String TxtLabel) {
            
            this.setPanelLayOut();
            this.setPanelLabel(TxtLabel);
            this.plabel.setPreferredSize(new Dimension(10, 10));
            panel.setPreferredSize(new Dimension(100, 800));
            panel.setBackground(Color.lightGray);
        }
        //@Override
        @Override
        public void setPanelLayOut(){
            gridlayout = new GridLayout(10,1);
            panel.setLayout(gridlayout);
            //this.setBorder(BorderFactory.createTitledBorder(
            //      BorderFactory.createEtchedBorder(), "Search Library"));
        }   
    }
    final class BottomPanel extends BasicPanel{
        
        GridBagLayout layout;
        GridBagConstraints c;
        public BottomPanel(String TxtLabel) {
            this.c = new GridBagConstraints();
            this.setPanelLayOut();
            this.setPanelLabel(TxtLabel);
            panel.setPreferredSize(new Dimension(1920, 100));
            panel.setBackground(Color.lightGray);
            
            //this.c.gridwidth=5;
            this.c.gridheight=3;
        }
        //@Override
        @Override
        public void setPanelLayOut(){
            layout = new GridBagLayout();
            panel.setLayout(layout);
        }   
    }   
}



        

