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
    private DefaultTableModel model;
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

// class myTableModel extends DefaultTableModel{
//
//           myTableModel(int row, int col){
//            super(row,col);
//    }
//         myTableModel(Object[][] tableData, Object[] colNames){
//            super(tableData, colNames);
//    }
//         @Override
//         public boolean isCellEditable(int row,int cols)
//
//                         {
//                            return true;
//                                                }      
// }




