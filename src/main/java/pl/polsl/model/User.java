package pl.polsl.model;

//import lombok.Getter;
//import lombok.Setter;

/**
 * User model
 *
 * @author Rafał Malinowski
 * @version 1
 */
public class User {
    private final InteriorUser interiorUser;
    
    public User(int id, String login, String mainPassword) {
        this.interiorUser = new InteriorUser(id, login, mainPassword, new Passwords());
    }
    
    public int getId(){
        return this.interiorUser.getId();
    }
    
    public String getLogin(){
        return this.interiorUser.getLogin();
    }
    
    public String getMainPassword(){
        return this.interiorUser.getMainPassword();
    }
    
    public Passwords getPasswordManager(){
        return this.interiorUser.getPasswordManager();
    }
    
    record InteriorUser(int getId, String getLogin, String getMainPassword, Passwords getPasswordManager){}
}

        /*
public class User {

    @Getter
    private int id;

    @Getter
    private String login;

    @Getter
    private String mainPassword;
    @Getter @Setter
    private boolean isAuthenticated;

    @Getter
    private Passwords passwordManager;
 
    public User(int id, String login, String mainPassword) {
        this.id = id;
        this.login = login;
        this.mainPassword = mainPassword;
        
        this.passwordManager = new Passwords();
    }
}
*/