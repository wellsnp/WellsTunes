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
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
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
        list.setCellRenderer(getRenderer());
        //DefaultListCellRenderer renderer =  (DefaultListCellRenderer)list.getCellRenderer();
        //ListCellRenderer<? super String> renderer = list.getCellRenderer();
        //renderer.
       //JLabel listCellRendererComponent = (JLabel) 
       //list.setBackground(Color.red);
       //list.setBorder(BorderFactory.createLineBorder(Color.BLUE));
       //renderer.getListCellRendererComponent(list, value, 0, true, true);
       //renderer.setBorder(BorderFactory.createLineBorder(Color.BLUE));
      //panel.add(scrollPane);
      panel.add(scrollPane);
      JFrame frame = new JFrame("Demo");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(panel);
      frame.setSize(500, 250);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }
    
    
    private static ListCellRenderer<? super String> getRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
                return listCellRendererComponent;
            }
        };
    }
            }