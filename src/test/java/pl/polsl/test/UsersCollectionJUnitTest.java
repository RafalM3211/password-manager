/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.exception.UserNotFoundException;
import pl.polsl.model.User;
import pl.polsl.model.UsersCollection;

/**
 *
 * @author SuperStudent-PL
 */
public class UsersCollectionJUnitTest {
    
    @Test
    public void addingUserTest() {
        UsersCollection usersCollection = new UsersCollection();
        
        usersCollection.addUser(new User(1, "test", "test"));
        
        User expectedUser = new User(1, "test", "test");
       
        assertEquals(expectedUser.getId(), usersCollection.getUsers().getFirst().getId());
        assertEquals(expectedUser.getLogin(), usersCollection.getUsers().getFirst().getLogin());
        assertEquals(expectedUser.getMainPassword(), usersCollection.getUsers().getFirst().getMainPassword());
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 0, -1})
    public void getUserByIdTest(int id){
        try {
            UsersCollection usersCollection = new UsersCollection();
            User expectedUser = new User(id, "test1", "test1");
            usersCollection.addUser(expectedUser);
            usersCollection.addUser(new User(id+1, "test2", "test2"));
            
            User actualUser = usersCollection.getUserById(id);
            
            assertEquals(expectedUser, actualUser);
        } 
        catch (UserNotFoundException e) {
            fail("User should be found");
        }
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 0, -1})
    public void throwWhenUserNotFoundTest(int id){
        try {
            UsersCollection usersCollection = new UsersCollection();
            User expectedUser = new User(id, "test1", "test1");
            usersCollection.addUser(expectedUser);
            usersCollection.addUser(new User(id+1, "test2", "test2"));
            
            User actualUser = usersCollection.getUserById(id+10);
            
            fail("User shouldn't be found. Exception should be thrown");
        } 
        catch (UserNotFoundException ex) {
        }
    }
    
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"Jan", "marek", "stefan ko", "   ", "\t"})
    public void getUserByLoginTest(String login){
        UsersCollection usersCollection = new UsersCollection();
        User expectedUser = new User(1, login, "password1");
        usersCollection.addUser(expectedUser);
        usersCollection.addUser(new User(2, login + "test2", "test2"));
        
        var actualUsers = usersCollection.getUsersByLogin(login);
        
        assertEquals(actualUsers.size(), 1);
        assertEquals(actualUsers.getFirst(), expectedUser);
    }
    
    @ParameterizedTest
    @EmptySource
    @NullSource
    @ValueSource(strings = {"Jan", "Marek", "    ", "\t"})
    public void doesNotFindUsersByLoginTest(String login){
        UsersCollection usersCollection = new UsersCollection();
        User expectedUser = new User(1, login + "test1", "password1");
        usersCollection.addUser(expectedUser);
        usersCollection.addUser(new User(2, login + "test2", "test2"));
        
        var actualUsers = usersCollection.getUsersByLogin(login);
        
        assertEquals(actualUsers.size(), 0);
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 0, -2})
    public void deleteUserTest(int id){
        try {
            UsersCollection usersCollection = new UsersCollection();
            User deletedUser = new User(id, "test1", "test1");
            usersCollection.addUser(deletedUser);
            usersCollection.addUser(new User(id+1, "test2", "test2"));
            
            usersCollection.deleteUser(id);
            
            assertEquals(usersCollection.getSize(), 1);
            assertEquals(usersCollection.getUsers().getFirst().getId(), id+1);
        }
        catch (UserNotFoundException ex){
            fail("User should be found");
        }
    }
    
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 0, -2})
    public void doesNotDeleteUserWhenNotFoundTest(int id){
        try {
            UsersCollection usersCollection = new UsersCollection();
            User deletedUser = new User(id, "test1", "test1");
            usersCollection.addUser(deletedUser);
            usersCollection.addUser(new User(id+1, "test2", "test2"));
            
            usersCollection.deleteUser(id+10);
            
            fail("Should throw UserNotFoundException");
        }
        catch (UserNotFoundException ex){
        }
    }
}
