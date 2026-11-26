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
 * Unit tests of the UsersCollection class
 *
 * @author Rafał Malinowski
 * @version 1
 */
public class UsersCollectionJUnitTest {
    
    /**
     * Checks that an added user is stored in the collection with unchanged id, login and password.
     * Test data: a single user with id 1, login "test" and password "test"
     */
    @Test
    public void addingUserTest() {
        UsersCollection usersCollection = new UsersCollection();
        
        usersCollection.addUser(new User(1, "test", "test"));
        
        User expectedUser = new User(1, "test", "test");
       
        assertEquals(expectedUser.getId(), usersCollection.getUsers().getFirst().getId(),
                "Id of the added user should not change");
        assertEquals(expectedUser.getLogin(), usersCollection.getUsers().getFirst().getLogin(),
                "Login of the added user should not change");
        assertEquals(expectedUser.getMainPassword(), usersCollection.getUsers().getFirst().getMainPassword(),
                "Main password of the added user should not change");
    }
    
    /**
     * Checks that the user with the given id is found in a collection of two users
     * (the second user has id increased by one).
     * Test data: ids 1, 4, 0 and -1 (including zero and a negative value)
     *
     * @param id Id of the searched user
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 0, -1})
    public void getUserByIdTest(int id){
        try {
            UsersCollection usersCollection = new UsersCollection();
            User expectedUser = new User(id, "test1", "test1");
            usersCollection.addUser(expectedUser);
            usersCollection.addUser(new User(id+1, "test2", "test2"));
            
            User actualUser = usersCollection.getUserById(id);
            
            assertEquals(expectedUser, actualUser,
                    "User found by id " + id + " should be the one added with this id");
        } 
        catch (UserNotFoundException e) {
            fail("User should be found");
        }
    }
    
    /**
     * Checks that searching for a user with an id that is not in the collection throws UserNotFoundException.
     * The collection contains users with ids "id" and "id + 1", the searched id is "id + 10".
     * Test data: ids 1, 4, 0 and -1
     *
     * @param id Id of the user added to the collection (the searched id is id + 10)
     */
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
            // Expected: the user does not exist, so the exception is the correct result
        }
    }
    
    /**
     * Checks that exactly one user is found by login when another user has a different login
     * (the searched login with "test2" appended).
     * Test data: an empty login, ordinary logins, a login with a space, a blank login and a tab
     *
     * @param login Login of the searched user
     */
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"Jan", "marek", "stefan ko", "   ", "\t"})
    public void getUserByLoginTest(String login){
        UsersCollection usersCollection = new UsersCollection();
        User expectedUser = new User(1, login, "password1");
        usersCollection.addUser(expectedUser);
        usersCollection.addUser(new User(2, login + "test2", "test2"));
        
        var actualUsers = usersCollection.getUsersByLogin(login);
        
        assertEquals(actualUsers.size(), 1,
                "Exactly one user should be found for login '" + login + "'");
        assertEquals(actualUsers.getFirst(), expectedUser,
                "Found user should be the one added with login '" + login + "'");
    }
    
    /**
     * Checks that no users are found when no user has exactly the searched login
     * (both users in the collection have the searched login with "test1" or "test2" appended).
     * Test data: a null login, an empty login, ordinary logins, a blank login and a tab
     *
     * @param login Searched login, which no user has
     */
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
        
        assertEquals(actualUsers.size(), 0,
                "No users should be found for login '" + login + "'");
    }
    
    /**
     * Checks that deleting a user removes only the user with the given id.
     * The collection contains users with ids "id" and "id + 1", the user with id "id" is deleted.
     * Test data: ids 1, 4, 0 and -2
     *
     * @param id Id of the user to delete
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 4, 0, -2})
    public void deleteUserTest(int id){
        try {
            UsersCollection usersCollection = new UsersCollection();
            User deletedUser = new User(id, "test1", "test1");
            usersCollection.addUser(deletedUser);
            usersCollection.addUser(new User(id+1, "test2", "test2"));
            
            usersCollection.deleteUser(id);
            
            assertEquals(usersCollection.getSize(), 1,
                    "Collection should contain one user after deleting user with id " + id);
            assertEquals(usersCollection.getUsers().getFirst().getId(), id+1,
                    "The remaining user should be the one with id " + (id+1));
        }
        catch (UserNotFoundException ex){
            fail("User should be found");
        }
    }
    
    /**
     * Checks that deleting a user with an id that is not in the collection throws UserNotFoundException.
     * The collection contains users with ids "id" and "id + 1", the deleted id is "id + 10".
     * Test data: ids 1, 4, 0 and -2
     *
     * @param id Id of the user added to the collection (the deleted id is id + 10)
     */
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
            // Expected: the user does not exist, so the exception is the correct result
        }
    }
}
