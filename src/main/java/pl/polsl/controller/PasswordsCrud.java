/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.controller;

import pl.polsl.exception.UnauthorizedException;
import pl.polsl.main.App;
import pl.polsl.model.User;

/**
 * Class that handles CRUD actions on passwords of the currently logged in user
 * @author Rafał Malinowski
 * @version 1
 */
public class PasswordsCrud {
    /**
     * User that was logged in when this object was created
     */
    private final User currentUser;
    
    /**
     * PasswordsCrud constructor. Takes the currently logged in user from the application state
     * @author Rafał Malinowski
     */
    public PasswordsCrud(){
        this.currentUser = App.getCurrentUser();
    }
    
    /**
     * Adds password to current user's password manager
     * @author Rafał Malinowski
     * @param origin Origin of the password (e.g. website name)
     * @param password Password to store
     * @throws UnauthorizedException Throws if there is no logged in user
     */
    public void addPassword(String origin, String password) throws UnauthorizedException{
        if (this.currentUser != null){
            this.currentUser.getPasswordManager().addPassword(origin, password);
        }
        else{
            throw new UnauthorizedException();
        }
    }
    
    /**
     * Removes password from current user's password manager
     * @author Rafał Malinowski
     * @param origin Origin of the password to remove
     * @throws UnauthorizedException Throws if there is no logged in user
     */
    public void removePassword(String origin) throws UnauthorizedException{
        if (this.currentUser != null) this.currentUser.getPasswordManager().removePassword(origin);
        else{
            throw new UnauthorizedException();
        }
    }
    
    /**
     * Changes password for given origin
     * @author Rafał Malinowski
     * @param origin Origin of the password to change
     * @param newPassword New password to store for the origin
     * @throws UnauthorizedException Throws if there is no logged in user
     */
    public void editPassword(String origin, String newPassword) throws UnauthorizedException{
        if (this.currentUser != null){
            this.currentUser.getPasswordManager().removePassword(origin);
            this.addPassword(origin, newPassword);
        }
        else{
            throw new UnauthorizedException();
        }
    }
    
    /**
     * Changes origin of a stored password, keeping the password itself
     * @author Rafał Malinowski
     * @param oldOrigin Current origin of the entry
     * @param newOrigin New origin of the entry
     * @throws UnauthorizedException Throws if there is no logged in user
     */
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
