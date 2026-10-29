/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pl.polsl.main;

import pl.polsl.view.Login;
import pl.polsl.view.Testform;


/**
 *
 * @author SuperStudent-PL
 */
public class Passwordmanager2 {

    public static void main(String[] args) {
        App app = new App();
        app.initUsers();
        
        Login loginFrame = new Login();
        loginFrame.setVisible(true);
    }
}
