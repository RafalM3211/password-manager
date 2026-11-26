    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.model.Passwords;

/**
 *
 * @author SuperStudent-PL
 */
public class PasswordsJUnitTest {
    
    @ParameterizedTest
    @CsvSource({"janorigin,kowpass", "JaNorigin,nowpass", "stefan ko origin,pass", ",pass", "origin,"})
    public void addPasswordTest(String origin, String password){
        Passwords passwords = new Passwords();
        
        passwords.addPassword(origin, password);
        
        var actualPasswords = passwords.getStoredPasswords();
        assertEquals(actualPasswords.get(origin), password);
    }
    
    @ParameterizedTest
    @CsvSource({"janorigin,kowpass", "JaNorigin,nowpass", "stefan ko origin,pass", ",pass", "origin,"})
    public void removePasswordTest(String origin, String password){
        Passwords passwords = new Passwords();
        passwords.addPassword(origin, password);
        passwords.addPassword(origin+"2", password+"2");
        
        passwords.removePassword(origin);
        
        var actualPasswords = passwords.getStoredPasswords();
        assertEquals(actualPasswords.get(origin), null);
    }
    
    @Test
    public void getSizeTest(){
        Passwords passwords = new Passwords();
        
        assertEquals(passwords.getSize(), 0);
        
        passwords.addPassword("origin", "pasword");
        
        assertEquals(passwords.getSize(), 1);
    }
}
