package pl.polsl.view;

import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import pl.polsl.controller.PasswordsCrud;
import pl.polsl.exception.UnauthorizedException;
import pl.polsl.main.App;

/**
 * Table model presenting passwords of the currently logged in user.
 * The table has two columns: origin and password
 *
 * @author Rafał Malinowski
 * @version 1
 */
public class PasswordTableModel extends AbstractTableModel {
    /**
     * Application state object
     */
    private App model;
    /**
     * Label used to show error messages
     */
    private JLabel errorText;

    /**
     * Application model setter
     * @author Rafał Malinowski
     * @param model Application state object
     */
    public void setModel(App model){
        this.model = model;
    }

    /**
     * PasswordTableModel constructor
     * @author Rafał Malinowski
     * @param errorText Label used to show error messages
     */
    public PasswordTableModel(JLabel errorText) {
        this.model = App.getInstance();
        this.errorText = errorText;
    }
    
    /**
     * Gets number of rows, which equals the number of passwords stored by the current user
     * @author Rafał Malinowski
     * @return Number of rows in the table
     */
    @Override
    public int getRowCount(){
        var a = App.getCurrentUser().getPasswordManager().getSize();
        return a;
    }
    
    /**
     * Gets number of columns
     * @author Rafał Malinowski
     * @return Number of columns in the table (origin and password)
     */
    @Override
    public int getColumnCount(){
        return 2;
    }
    
    /**
     * Gets value of the cell
     * @author Rafał Malinowski
     * @param rowIndex Index of the row
     * @param columnIndex Index of the column: 0 for origin, 1 for password
     * @return Origin or password in the given cell, or empty string if there is no such cell
     */
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
    
    /**
     * Gets name of the column
     * @author Rafał Malinowski
     * @param column Index of the column: 0 for origin, 1 for password
     * @return Column name, or empty string if there is no such column
     */
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

    /**
     * Sets value of the cell. Changes origin or password of the stored entry
     * in the current user's password manager. If nobody is logged in, an error message is shown
     * @author Rafał Malinowski
     * @param aValue New value, expected to be a String
     * @param rowIndex Index of the edited row
     * @param columnIndex Index of the edited column: 0 for origin, 1 for password
     */
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
