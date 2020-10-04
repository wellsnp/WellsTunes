/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author wells
 */
public class TaggTable extends JTable{
    private final DefaultTableModel model;
    private final ListSelectionModel cellSelectionModel;

 
    TaggTable(Actions ActionHandler){
      super(0,1);
      this.model = (DefaultTableModel) getModel();
      this.setCellSelectionEnabled(true);
      this.cellSelectionModel=getSelectionModel();
      this.cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.cellSelectionModel.addListSelectionListener(ActionHandler);
      this.addKeyListener(ActionHandler);
      this.setTableHeader(null);
    }
    
    public void LoadTable(TaggList TL){
        this.model.setRowCount(0);
        int cnt=this.model.getRowCount();
        System.out.println("Row Cnt");
        System.out.println(cnt);
        
        for(int i=0; i< TL.listmodel.getSize();i++) {           
            this.model.insertRow(i,new String[]{TL.listmodel.get(i)});
        }
    }
        public void LoadTable(DefaultListModel<String> DLM){
        this.model.setRowCount(0);
        int cnt=this.model.getRowCount();
        System.out.println("Row Cnt");
        System.out.println(cnt);
        
        for(int i=0; i< DLM.getSize();i++) {           
            this.model.insertRow(i,new String[]{DLM.get(i)});
        }
    }
    
    
       public ListSelectionModel getCellSelectionModel() {
        return cellSelectionModel;
    }
    
    
}
