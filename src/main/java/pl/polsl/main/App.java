package pl.polsl.main;

import pl.polsl.exception.UserNotFoundException;
import pl.polsl.model.User;
import pl.polsl.model.UsersCollection;

/**
 * Class that holds the app configuration and state. Will be mostly replaced by a database in future
 *
 * @author Rafał Malinowski
 * @version 1
 */
public class App {
    /**
     * Holds all users. A database mockup for now
     */
    static private UsersCollection usersCollection;
    
    static private User currentUser = null;
    
    /**
     * App constructor
     * @author Rafał Malinowski
     */
    private App() {
        App.usersCollection = new UsersCollection();
    }
    
    private static class Holder {
        private static final App INSTANCE = new App();
    }
    
     public static App getInstance() {
        return Holder.INSTANCE;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }

    /**
     * Creates default users
     * @author Rafał Malinowski
     */
    public void initUsers(){
        usersCollection.addUser(new User(1, "test", "test"));
        try
        {
            usersCollection.getUserById(1).getPasswordManager().addPassword("test website", "test password");
        }
        catch (UserNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Users collection getter
     *
     * @author Rafał Malinowski
     * @return Users collection
     */
    public static UsersCollection getUsersCollection() {
        return usersCollection;
    }
}
