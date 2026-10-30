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
    
    /**
     * Currently logged in user. Null if nobody is logged in
     */
    static private User currentUser = null;
    
    /**
     * App constructor
     * @author Rafał Malinowski
     */
    private App() {
        App.usersCollection = new UsersCollection();
    }
    
    /**
     * Holder of the singleton instance. The instance is created lazily, when the holder is first loaded
     * @author Rafał Malinowski
     */
    private static class Holder {
        /**
         * The only App instance
         */
        private static final App INSTANCE = new App();
    }
    
    /**
     * Gets the singleton App instance
     * @author Rafał Malinowski
     * @return The only App instance
     */
     public static App getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Currently logged in user getter
     * @author Rafał Malinowski
     * @return Currently logged in user or null if nobody is logged in
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Currently logged in user setter
     * @author Rafał Malinowski
     * @param currentUser User to be set as currently logged in
     */
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
