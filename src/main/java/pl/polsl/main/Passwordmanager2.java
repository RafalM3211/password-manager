/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pl.polsl.main;

import pl.polsl.view.Login;


/**
 *
 * @author SuperStudent-PL
 */
public class Passwordmanager2 {

    public static void main(String[] args) {
        App app = App.getInstance();
        app.initUsers();
        
        Login loginFrame = new Login();
        loginFrame.setVisible(true);
    }
}
