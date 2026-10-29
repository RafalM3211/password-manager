package pl.polsl.controller;

import pl.polsl.exception.UserNotFoundException;
import pl.polsl.main.App;
import pl.polsl.model.User;
import pl.polsl.model.UsersCollection;

/**
 * Class that handles user CRUD actions
 * @author Rafał Malinowski
 * @version 1
 */
public class UserCrud {
    /**
     * Creates user
     * @author Rafał Malinowski
     * @param id User id
     * @param login User login
     * @param password User password
     * @return Reference to created User object
     */
    public User createUser(int id, String login, String password){
        return new User(id, login, password);
    }

    /**
     * Deletes user with given id
     * @author Rafał Malinowski
     * @param id User id
     */
    public void deleteUser(int id){
        UsersCollection usersCollection = App.getUsersCollection();
        try {
            usersCollection.deleteUser(id);
        }
        catch (UserNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
