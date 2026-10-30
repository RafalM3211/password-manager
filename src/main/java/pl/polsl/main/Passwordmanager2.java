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
