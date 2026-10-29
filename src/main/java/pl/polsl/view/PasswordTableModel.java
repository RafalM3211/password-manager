/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.view;

import javax.swing.table.AbstractTableModel;
import pl.polsl.main.App;

/**
 *
 * @author SuperStudent-PL
 */
public class PasswordTableModel extends AbstractTableModel {
    private App model;
    
    public void setModel(App model){
        this.model = model;
    }

    public PasswordTableModel() {
        this.model = new App();
    }
    
    
    @Override
    public int getRowCount(){
        return model.getCurrentUser().getStoredPasswords().getSize();
    }
    
    @Override
    public int getColumnCount(){
    return 3;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        switch(columnIndex){
            case 0:
                return "test login";
                //return model.getCurrentUser().getStoredPasswords().getStoredPasswords().keySet()+"";
            case 1:
                return "test password";
                //return model.getCurrentUser().getStoredPasswords().getStoredPasswords().values()+"";
            default:
                return "";
        }
    }
    
    @Override
    public String getColumnName(int column){
     switch(column){
            case 0:
                return "origin";
            case 1:
                return "passwgord";
            default:
                return "";
        }
    }

}
