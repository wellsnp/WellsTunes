/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author wellsnp
 */
public class TaggList extends BaseList{
    
    public TaggList() {
       list.setCellRenderer(super.getRenderer());
       this.setViewportView(list);
       this.setPreferredSize(new Dimension (50,500));
    }
    
    //Methods
    //@Override
    public void clearList(){
     listmodel.removeAllElements();
    }

}
