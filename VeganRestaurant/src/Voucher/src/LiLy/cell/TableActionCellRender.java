/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LiLy.cell;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Admin
 */
public class TableActionCellRender extends DefaultTableCellRenderer{
    
    public Component getTableActionCellRenderComponent(JTable jtable, Object o, boolean isSeleted, boolean bln1, int row, int column){
        Component com = super.getTableCellRendererComponent(jtable, o, isSeleted, bln1, row, column);

        PanelAction act = new PanelAction();
        if(isSeleted==false && row%2==0){
            act.setBackground(Color.WHITE);
        } else{
        act.setBackground(com.getBackground());
        }
        return act;
    }
    
}
