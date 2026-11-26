/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.util.HashMap;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Password manager model. Holds the passwords of a single user,
 * stored as pairs of origin (e.g. website name) and password
 *
 * @author Rafał Malinowski
 * @version 1
 */
@NoArgsConstructor
public class Passwords {
    /**
     * Stored passwords. Key is the origin, value is the password for that origin
     */
    @Getter
    private HashMap<String, String> storedPasswords = new HashMap<String, String>();

    /**
     * Passwords constructor. Creates an empty password storage
     * @author Rafał Malinowski
     */
    
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
        this.storedPasswords.remove(origin);
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
