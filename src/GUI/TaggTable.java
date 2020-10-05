/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author wells
 */
public class TaggTable extends JTable{
    private final DefaultTableModel model;
    //private DefaultTableModel mymodel;
    private final ListSelectionModel cellSelectionModel;

 
    TaggTable(Actions ActionHandler){
      super(1,1);
      //this.model = (DefaultTableModel) new myTableModel(1,1);
      
      this.model = (DefaultTableModel) getModel();
      
      //this.setCellSelectionEnabled(true);
      //this.setEnabled(false);
      this.cellSelectionModel=getSelectionModel();
      this.cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      this.cellSelectionModel.addListSelectionListener(ActionHandler);
      this.getDefaultEditor(String.class).addCellEditorListener(ActionHandler);
      this.setModel(model);
      this.setSelectionModel(cellSelectionModel);
      this.setTableHeader(null);
    }
    
    public void LoadTable(BaseList List){
        this.model.setRowCount(0);
        int cnt=this.model.getRowCount();
       
        System.out.println("Row Cnt");
        System.out.println(cnt);
        
        for(int i=0; i< List.listmodel.getSize();i++) {           
            this.model.insertRow(i,new String[]{List.listmodel.get(i)});
        }
    }    
    public ListSelectionModel getCellSelectionModel() {
        return cellSelectionModel;
    }        
}
