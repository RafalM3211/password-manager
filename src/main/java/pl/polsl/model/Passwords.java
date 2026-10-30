/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.util.HashMap;

/**
 * Password manager model. Holds the passwords of a single user,
 * stored as pairs of origin (e.g. website name) and password
 *
 * @author Rafał Malinowski
 * @version 1
 */
public class Passwords {
    /**
     * Stored passwords. Key is the origin, value is the password for that origin
     */
    private HashMap<String, String> storedPasswords;

    /**
     * Passwords constructor. Creates an empty password storage
     * @author Rafał Malinowski
     */
    public Passwords() {
        this.storedPasswords = new HashMap<String, String>();
    }
    
    /**
     * Adds password to storage. If the origin already exists, its password is overwritten
     * @author Rafał Malinowski
     * @param origin Origin of the password (e.g. website name)
     * @param password Password to store
     */
    public void addPassword(String origin, String password){
        this.storedPasswords.put(origin, password);
    }
    
    /**
     * Removes password with given origin from storage. Does nothing if the origin does not exist
     * @author Rafał Malinowski
     * @param origin Origin of the password to remove
     */
    public void removePassword(String origin){
        System.out.println("REMOVE: " + origin);
        this.storedPasswords.remove(origin);
    }
    
    /**
     * Edits stored entry by removing the old one and adding a new one
     * @author Rafał Malinowski
     * @param oldOrigin Current origin of the entry
     * @param newOrigin New origin of the entry
     * @param password Password to store under the new origin
     */
    public void editPassword(String oldOrigin, String newOrigin, String password){
        this.storedPasswords.remove(oldOrigin);
        this.addPassword(newOrigin, password);
    }

    /**
     * Stored passwords getter
     * @author Rafał Malinowski
     * @return Map of stored passwords, where key is the origin and value is the password
     */
    public HashMap<String, String> getStoredPasswords() {
        return storedPasswords;
    }
    
    /**
     * Gets number of stored passwords
     * @author Rafał Malinowski
     * @return Number of stored passwords
     */
    public int getSize(){
        return this.storedPasswords.size();
    }
}
