/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.view;

import java.util.HashMap;
import java.util.Map;
import javax.swing.event.TableModelEvent;
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
        this.model = App.getInstance();
    }
    
    @Override
    public int getRowCount(){
        var a = App.getCurrentUser().getPasswordManager().getSize();
        System.out.println(a);
        return a;
    }
    
    @Override
    public int getColumnCount(){
        return 2;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HashMap<String, String> passwords =
            App.getCurrentUser()
               .getPasswordManager()
               .getStoredPasswords();

        int i = 0;
        System.out.println(passwords.toString());
        for (var entry : passwords.entrySet()) {
            if (i == rowIndex) {
                switch (columnIndex) {
                    case 0:
                        return entry.getKey();
                    case 1:
                        return entry.getValue();
                    default:
                        return "";
                }
            }
            i++;
        }

        return "";
    }
    
    @Override
    public String getColumnName(int column){
     switch(column){
            case 0:
                return "origin";
            case 1:
                return "password";
            default:
                return "";
        }
    }

}
