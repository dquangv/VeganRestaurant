/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


public interface TableActionEvent {
    
    public void onVoucher(int row);
    
    public void onMail(int row);
    
    public void onDelete(int row);    
}
