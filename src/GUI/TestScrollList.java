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

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.*;
public class TestScrollList {
    
    
    public static void main(String[] args) {
      JPanel panel = new JPanel(new BorderLayout());
      List<String> myList = new ArrayList<>(10);
      for (int index = 0; index < 20; index++) {
         myList.add("List Item " + index);
      }
      final JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
      MouseListener mouseListener = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            int selectedItem = (int) list.getSelectedIndex();
            System.out.println(selectedItem);

         }
        }
      };
      list.addMouseListener(mouseListener);
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setViewportView(list);
      
      list.setLayoutOrientation(JList.VERTICAL);
      
      panel.add(scrollPane);
      JFrame frame = new JFrame("Demo");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(panel);
      frame.setSize(500, 250);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
    
    
    
            }