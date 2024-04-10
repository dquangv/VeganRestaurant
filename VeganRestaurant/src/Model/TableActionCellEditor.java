/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import LiLy.cell.PanelAction;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author Admin
 */
public class TableActionCellEditor extends DefaultCellEditor {

    private TableActionEvent event;

    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
//        // Khởi tạo một đối tượng PanelAction
        PanelAction act = new PanelAction();
//
//        // Thiết lập các sự kiện cho PanelAction
//        act.initEvent(event, row);
//
//        // Thiết lập màu nền cho PanelAction
//        act.setBackground(jtable.getSelectionBackground());
//
//        // Trả về PanelAction đã được thiết lập
        return act;
    }

}
