/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.controller;

import pl.polsl.main.App;
import pl.polsl.model.User;

/**
 *
 * @author SuperStudent-PL
 */
public class PasswordsCrud {
    private final User currentUser;
    
    public PasswordsCrud(){
        this.currentUser = App.getCurrentUser();
    }
    
    public void addPassword(String origin, String password){
        if (this.currentUser != null){
            this.currentUser.getPasswordManager().addPassword(origin, password);
        }
    }
    
    public void removePassword(String origin){
        if (this.currentUser != null) this.currentUser.getPasswordManager().removePassword(origin);
    }
    
    public void editPassword(String oldOrigin, String newOrigin, String password){
        if (this.currentUser != null){
            this.currentUser.getPasswordManager().removePassword(oldOrigin);
        this.addPassword(newOrigin, password);
        }
    }
}
