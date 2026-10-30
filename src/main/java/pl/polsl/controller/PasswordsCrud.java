/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.controller;

import pl.polsl.exception.UnauthorizedException;
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
    
    public void addPassword(String origin, String password) throws UnauthorizedException{
        if (this.currentUser != null){
            this.currentUser.getPasswordManager().addPassword(origin, password);
        }
        else{
            throw new UnauthorizedException();
        }
    }
    
    public void removePassword(String origin) throws UnauthorizedException{
        if (this.currentUser != null) this.currentUser.getPasswordManager().removePassword(origin);
        else{
            throw new UnauthorizedException();
        }
    }
    
    public void editPassword(String origin, String newPassword) throws UnauthorizedException{
        if (this.currentUser != null){
            this.currentUser.getPasswordManager().removePassword(origin);
            this.addPassword(origin, newPassword);
        }
        else{
            throw new UnauthorizedException();
        }
    }
    
    public void editOrigin(String oldOrigin, String newOrigin) throws UnauthorizedException{
        if (this.currentUser != null){
            String password = this.currentUser.getPasswordManager().getStoredPasswords().get(oldOrigin);
            this.currentUser.getPasswordManager().removePassword(oldOrigin);
            this.addPassword(newOrigin, password);
        }
        else{
            throw new UnauthorizedException();
        }
    }
}
