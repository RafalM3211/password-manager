/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.view;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import pl.polsl.controller.PasswordsCrud;
import pl.polsl.exception.UnauthorizedException;
import pl.polsl.main.App;

/**
 *
 * @author SuperStudent-PL
 */
public class PasswordTableModel extends AbstractTableModel {
    private App model;
    private JLabel errorText;

    public void setModel(App model){
        this.model = model;
    }

    public PasswordTableModel(JLabel errorText) {
        this.model = App.getInstance();
        this.errorText = errorText;
    }
    
    @Override
    public int getRowCount(){
        var a = App.getCurrentUser().getPasswordManager().getSize();
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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        HashMap<String, String> passwords =
            App.getCurrentUser()
               .getPasswordManager()
               .getStoredPasswords();

        PasswordsCrud passwordCrud = new PasswordsCrud();
        
        int i = 0;
        for (var entry : passwords.entrySet()) {
            if (i == rowIndex) {
                try{
                    switch (columnIndex) {
                    case 0:
                        String oldOrigin = entry.getKey();
                        String newOrigin = (String) aValue;
                        passwordCrud.editOrigin(oldOrigin, newOrigin);
                        break;
                    case 1:
                        String origin = entry.getKey();
                        String newPassword = (String) aValue;
                        passwordCrud.editPassword(origin, newPassword);
                        break;
                    }
                }
                catch (UnauthorizedException e){
                    errorText.setText(e.getMessage());
                }
                break;
            }
            i++;
        }
    }
}
