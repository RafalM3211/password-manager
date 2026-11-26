package pl.polsl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.model.Passwords;

/**
 * Unit tests of the Passwords class
 *
 * @author Rafał Malinowski
 * @version 1
 */
public class PasswordsJUnitTest {
    
    /**
     * Checks that an added password can be read back from the storage under its origin.
     * Test data: ordinary origins, an origin with different letter case, an origin with spaces,
     * a null origin (empty CSV value) and a null password (empty CSV value)
     *
     * @param origin Origin of the password
     * @param password Password to add
     */
    @ParameterizedTest
    @CsvSource({"janorigin,kowpass", "JaNorigin,nowpass", "stefan ko origin,pass", ",pass", "origin,"})
    public void addPasswordTest(String origin, String password){
        Passwords passwords = new Passwords();
        
        passwords.addPassword(origin, password);
        
        var actualPasswords = passwords.getStoredPasswords();
        assertEquals(actualPasswords.get(origin), password,
                "Password stored under origin '" + origin + "' should be equal to the added password");
    }
    
    /**
     * Checks that removing a password deletes the entry with the given origin.
     * Two entries are added (the second one has a modified origin and password) and the first one is removed.
     * Test data: the same origins and passwords as in addPasswordTest, including null values
     *
     * @param origin Origin of the password to remove
     * @param password Password stored under the origin
     */
    @ParameterizedTest
    @CsvSource({"janorigin,kowpass", "JaNorigin,nowpass", "stefan ko origin,pass", ",pass", "origin,"})
    public void removePasswordTest(String origin, String password){
        Passwords passwords = new Passwords();
        passwords.addPassword(origin, password);
        passwords.addPassword(origin+"2", password+"2");
        
        passwords.removePassword(origin);
        
        var actualPasswords = passwords.getStoredPasswords();
        assertEquals(actualPasswords.get(origin), null,
                "Password with origin '" + origin + "' should be removed from the storage");
    }
    
    /**
     * Checks that the size of the storage is 0 for a new storage
     * and 1 after adding a single password (origin "origin", password "pasword")
     */
    @Test
    public void getSizeTest(){
        Passwords passwords = new Passwords();
        
        assertEquals(passwords.getSize(), 0,
                "New password storage should be empty");
        
        passwords.addPassword("origin", "pasword");
        
        assertEquals(passwords.getSize(), 1,
                "Password storage should contain exactly one password after adding one");
    }
}
