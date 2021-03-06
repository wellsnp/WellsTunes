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
    //Fields
    private List<String> ScrollListNames;
    private List<File> ScrollListFiles;
    //private Actions ActionHandler; 
    protected JList<String> list;
    protected DefaultListModel<String> listmodel;
    private static Color Even;
    private static Color Odd;
    
    //Overloaded Constructors
    public BaseList(){
          listmodel = new DefaultListModel<>();
          list = new JList<>(listmodel);
          this.defineColors();
          this.setViewportView(list);
          list.setLayoutOrientation(JList.VERTICAL); 
    }
    public BaseList(Actions ActionHandler){          
          List<String> myList = new ArrayList<>(10);
          listmodel = new DefaultListModel<>();
          list = new JList<>(listmodel);
          //list = new JList<String>(myList.toArray(new String[myList.size()]));
          //for (int index = 0; index < 20; index++) {
          //  myList.add("ListItem: " + index);
          //  listmodel.addElement(myList.get(index));
          //}
          this.defineColors();
          this.setViewportView(list);
          this.setPreferredSize(new Dimension (200,500));
          list.setLayoutOrientation(JList.VERTICAL);
          //list.setCellRenderer(getRenderer());
          //this.initMouseAdapter();
          list.addMouseListener(ActionHandler); 
    }
    //Methods
    public static Color getEven() {
        return Even;
    }
    public static Color getOdd() {
        return Odd;
    }
    public List<String> getScrollListNames() {
        return ScrollListNames;
    }
    public String getScrollListNames(int n) {
        return ScrollListNames.get(n);
    }
    public List<File> getScrollListFiles() {
        return ScrollListFiles;
    }
    public File getScrollListFiles(int n) {
        return ScrollListFiles.get(n);
    }    
    public int getScrollListFilesSize() {
        return ScrollListFiles.size();
    }
    public void UpdateList(File InputFile){
     listmodel.removeAllElements();
    }
    private void defineColors(){
        Color Field = Color.BLUE;
        BaseList.Even = new Color(Field.getRed(), Field.getGreen(), Field.getBlue(), 20);
        Field = Color.LIGHT_GRAY;
        BaseList.Odd = new Color(Field.getRed(), Field.getGreen(), Field.getBlue(), 20);
    }
    public void setScrollListFiles(List<File> ScrollListFiles) {
        this.ScrollListFiles = ScrollListFiles;
    }
    public void addToScrollListFiles(File File){
            this.ScrollListFiles.add(File);
    }
    public void setScrollListNames(List<String> ScrollListNames) {
        this.ScrollListNames = ScrollListNames;
    }
    public void addToScrollListNames(String String){
            this.ScrollListNames.add(String);
    }
    public File removeFromScrollListFiles(){
            return ScrollListFiles.remove(0);
    }
    public String removeFromScrollListNames(){
            return ScrollListNames.remove(0);
    }
    public void clearScrollLists(){
                this.ScrollListFiles.clear();
                this.ScrollListNames.clear();
    }
    public void clearList(){
     listmodel.removeAllElements();
    }   
    public boolean isNullScrollListFiles(){
        return this.ScrollListFiles == null;
    }
    public boolean isNullScrollListNames(){
        return this.ScrollListNames == null;
    }
    public static ListCellRenderer<? super String> getRenderer() {
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

