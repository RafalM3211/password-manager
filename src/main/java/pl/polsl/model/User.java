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
    private String login;
    private String mainPassword;
    private boolean isAuthenticated;
    private Passwords storedPasswords;

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

    public Passwords getStoredPasswords() {
        return storedPasswords;
    }
    
    

    public boolean isIsAuthenticated() {
        return isAuthenticated;
    }

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
        
        this.storedPasswords = new Passwords();
    }
    
    
}
