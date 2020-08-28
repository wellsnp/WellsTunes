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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.io.File;
import FileAndDirectory.FolderInfo;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
public class BaseList extends JScrollPane{
    
    List<String> ScrollListNames;
    public List<File> ScrollListFiles;
    Actions ActionHandler; 
    JList<String> list;
    DefaultListModel<String> listmodel;
    
    public BaseList(Actions ActionHandler){          
          List<String> myList = new ArrayList<>(10);
          listmodel = new DefaultListModel<>();
          list = new JList<String>(listmodel);
          //list = new JList<String>(myList.toArray(new String[myList.size()]));
          for (int index = 0; index < 20; index++) {
            myList.add("ListItem: " + index);
            listmodel.addElement(myList.get(index));
          }
        
          this.setViewportView(list);
          this.setPreferredSize(new Dimension (100,500));
          list.setLayoutOrientation(JList.VERTICAL);
          //this.initMouseAdapter();
          list.addMouseListener(ActionHandler);
       
    }
    public void UpdateList(File InputFile){
     listmodel.removeAllElements();
    }
}

