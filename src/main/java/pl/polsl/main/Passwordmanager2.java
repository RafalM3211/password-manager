/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pl.polsl.main;

import pl.polsl.view.Login;


/**
 * Main class of the password manager application
 *
 * @author Rafał Malinowski
 * @version 1
 */
public class Passwordmanager2 {

    /**
     * Application entry point
     * Optionally, login and password can be passed as arguments to prefill the login form
     * @author Rafał Malinowski
     * @param args Command line arguments: optional login (first) and optional password (second)
     */
    public static void main(String[] args) {
        App app = App.getInstance();
        app.initUsers();
        
        
        Login loginFrame;
        if(args.length>0){
            String login = args[0];
            String password = args.length>1? args[1]:"";
            loginFrame = new Login(login, password);
        }
        else {
            loginFrame = new Login();
        }
        
        loginFrame.setVisible(true);
    }
}
