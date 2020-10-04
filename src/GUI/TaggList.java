/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dimension;

/**
 *
 * @author wellsnp
 */
public class TaggList extends BaseList{
    
    public TaggList() {
       list.setCellRenderer(super.getRenderer());
       this.setViewportView(list);
       this.setPreferredSize(new Dimension (10,100));
    }
    
    //Methods
    //@Override
    public void clearList(){
     listmodel.removeAllElements();
    }


//             public void setTLLM(TaggList TL){
//              int num = TL.listmodel.getSize();
//              for( int i = 0; i<num; i++){
//                  this.listmodel.add(i, TL.listmodel.elementAt(i));
//          }
//    
//}
}
