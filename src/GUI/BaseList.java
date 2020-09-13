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
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.io.File;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
public class BaseList extends JScrollPane{
    
    List<String> ScrollListNames;
    public List<File> ScrollListFiles;
    Actions ActionHandler; 
    JList<String> list;
    DefaultListModel<String> listmodel;
    private Color Even;


    private Color Odd;
    //Overloaded Constructors
    public BaseList(){
          
          listmodel = new DefaultListModel<>();
          list = new JList<String>(listmodel);
          this.defineColors();
          this.setViewportView(list);
          this.setPreferredSize(new Dimension (50,500));
          list.setLayoutOrientation(JList.VERTICAL);
    };
    public BaseList(Actions ActionHandler){          
          List<String> myList = new ArrayList<>(10);
          listmodel = new DefaultListModel<>();
          list = new JList<String>(listmodel);
          //list = new JList<String>(myList.toArray(new String[myList.size()]));
          //for (int index = 0; index < 20; index++) {
          //  myList.add("ListItem: " + index);
          //  listmodel.addElement(myList.get(index));
          //}
          this.defineColors();
          this.setViewportView(list);
          this.setPreferredSize(new Dimension (200,500));
          list.setLayoutOrientation(JList.VERTICAL);
          //this.initMouseAdapter();
          list.addMouseListener(ActionHandler);
       
    }
    public Color getEven() {
        return Even;
    }

    public Color getOdd() {
        return Odd;
    }
    
    public void UpdateList(File InputFile){
     listmodel.removeAllElements();
    }
    private void defineColors(){
    
                    Color Field = Color.BLUE;
                    this.Even = new Color(Field.getRed(),Field.getGreen(),Field.getBlue(),20);
                    Field = Color.LIGHT_GRAY;
                    this.Odd = new Color(Field.getRed(),Field.getGreen(),Field.getBlue(),20);
    
    }
    
    public ListCellRenderer<? super String> getRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.white));
               listCellRendererComponent.setHorizontalAlignment(JLabel.CENTER);
               listCellRendererComponent.setFont(new Font("Times", Font.BOLD, 16));
                if (index % 2 == 0) {    
                    listCellRendererComponent.setBackground(Even);
                    
                }else {
                   
                    listCellRendererComponent.setBackground(Odd);
                }
                return listCellRendererComponent;
            }
        };
    }
}

