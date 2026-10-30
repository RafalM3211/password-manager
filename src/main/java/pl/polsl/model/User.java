package pl.polsl.model;

/**
 * User model
 *
 * @author Rafał Malinowski
 * @version 1
 */
public class User {
    /**
     * User id
     */
    private int id;
    /**
     * User login
     */
    private String login;
    /**
     * User main password
     */
    private String mainPassword;
    /**
     * Information whether user has been authenticated
     */
    private boolean isAuthenticated;
    /**
     * Password manager holding passwords stored by the user
     */
    private Passwords passwordManager;

    /**
     * User Id getter
     * @author Rafał Malinowski
     * @return User id
     */
    public int getId() {
        return id;
    }

    /**
     * User main password getter
     * @author Rafał Malinowski
     * @return User main password
     */
    public String getMainPassword() {
        return mainPassword;
    }

    /**
     * User login getter
     * @author Rafał Malinowski
     * @return User login
     */
    public String getLogin() {
        return login;
    }

    /**
     * User password manager getter
     * @author Rafał Malinowski
     * @return Password manager with passwords stored by the user
     */
    public Passwords getPasswordManager() {
        return passwordManager;
    }

    /**
     * Authentication status getter
     * @author Rafał Malinowski
     * @return True if user has been authenticated, false otherwise
     */
    public boolean isIsAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Authentication status setter
     * @author Rafał Malinowski
     * @param isAuthenticated True if user has been authenticated, false otherwise
     */
    public void setIsAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }
    
    
    

    /**
     * User constructor
     * @author Rafał Malinowski
     * @param id user Id
     * @param mainPassword user password
     * @param login user login
     */
    public User(int id, String login, String mainPassword) {
        this.id = id;
        this.login = login;
        this.mainPassword = mainPassword;
        
        this.passwordManager = new Passwords();
    }
    
    
}
