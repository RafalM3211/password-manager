/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.controller;

import pl.polsl.enums.Action;
import java.time.LocalDateTime;
import pl.polsl.exception.UnauthorizedException;
import pl.polsl.main.App;
import pl.polsl.model.ActivityLog;
import pl.polsl.model.ActivityLogRepositoryInterface;
import pl.polsl.model.User;

/**
 * Class that handles CRUD actions on passwords of the currently logged in user
 * @author Rafał Malinowski
 * @version 1
 */
public class PasswordsCrud {
    
    private final ActivityLogRepositoryInterface logRepository;
    
    /**
     * PasswordsCrud constructor. Takes the currently logged in user from the application state
     * @author Rafał Malinowski
     * @param logRepository Log repository
     */
    public PasswordsCrud(ActivityLogRepositoryInterface logRepository){
        this.logRepository = logRepository;
    }
    
    /**
     * Adds password to current user's password manager
     * @author Rafał Malinowski
     * @param origin Origin of the password (e.g. website name)
     * @param password Password to store
     * @throws UnauthorizedException Throws if there is no logged in user
     */
    public void addPassword(String origin, String password) throws UnauthorizedException{
        if (App.getCurrentUser() != null){
            App.getCurrentUser().getPasswordManager().addPassword(origin, password);
            this.logRepository.save(new ActivityLog(
               LocalDateTime.now(),
                Action.ADD,
                origin,
                "Added new password",
                App.getCurrentUser().getId()
            ));
            System.out.println(this.logRepository.findAll());
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
        if (App.getCurrentUser() != null) {
            App.getCurrentUser().getPasswordManager().removePassword(origin);
            this.logRepository.save(new ActivityLog(
               LocalDateTime.now(),
                Action.DELETE,
                origin,
                "Removed password",
                App.getCurrentUser().getId()
            ));
        }
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
        if (App.getCurrentUser() != null){
            App.getCurrentUser().getPasswordManager().removePassword(origin);
            App.getCurrentUser().getPasswordManager().addPassword(origin, newPassword);
            this.logRepository.save(new ActivityLog(
               LocalDateTime.now(),
                Action.EDIT,
                origin,
                "Modified password",
                App.getCurrentUser().getId()
            ));
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
        if (App.getCurrentUser() != null){
            String password = App.getCurrentUser().getPasswordManager().getStoredPasswords().get(oldOrigin);
            App.getCurrentUser().getPasswordManager().removePassword(oldOrigin);
            App.getCurrentUser().getPasswordManager().addPassword(newOrigin, password);
            this.logRepository.save(new ActivityLog(
               LocalDateTime.now(),
                Action.EDIT,
                oldOrigin,
                "Modified origin. New origin: " + newOrigin,
                App.getCurrentUser().getId()
            ));
        }
        else{
            throw new UnauthorizedException();
        }
    }
}
