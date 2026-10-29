/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.util.HashMap;

/**
 *
 * @author SuperStudent-PL
 */
public class Passwords {
    private HashMap<String, String> storedPasswords;

    public Passwords() {
        this.storedPasswords = new HashMap<String, String>();
    }
    
    public void addPassword(String origin, String password){
        this.storedPasswords.put(origin, password);
    }
    
    public void removePassword(String origin){
        System.out.println("REMOVE: " + origin);
        this.storedPasswords.remove(origin);
    }
    
    public void editPassword(String oldOrigin, String newOrigin, String password){
        this.storedPasswords.remove(oldOrigin);
        this.addPassword(newOrigin, password);
    }

    public HashMap<String, String> getStoredPasswords() {
        return storedPasswords;
    }
    
    public int getSize(){
        return this.storedPasswords.size();
    }
}
