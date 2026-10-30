package pl.polsl.controller;

import java.util.ArrayList;
import java.util.Scanner;
import pl.polsl.main.App;
import pl.polsl.model.User;
import pl.polsl.model.UsersCollection;

/**
 * Handles password login
 * @author Rafał Malinowski
 * @version 1
 */
public class PasswordLogin implements LoginInterface {
    /**
     * User login
     */
    private String login;
    /**
     * User password
     */
    private String password;

    /**
     * PasswordLogin constructor
     * @author Rafał Malinowski
     * @param login User login
     * @param password User password
     */
    public PasswordLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }
    

    /**
     * Collects login from console
     * @author Rafał Malinowski
     */
    public void collectLogin(){
        Scanner scanner = new Scanner(System.in);
        login = scanner.next();
    }

    /**
     * Collects password from console
     * @author Rafał Malinowski
     */
    public void collectPassword(){
        Scanner scanner = new Scanner(System.in);
        password = scanner.next();
    }

    /**
     * Sets login and password. Used for programmatically login user instead of collecting data from console
     * @author Rafał Malinowski
     * @param login User login
     * @param password User password
     */
    public void polluteLoginData(String login, String password){
        this.login = login;
        this.password = password;
    }

    /**
     * Authenticates user
     * @author Rafał Malinowski
     * @return Information about whether authentication was successful or not
     */
    @Override
    public boolean authenticate(){
        UsersCollection usersCollection = App.getUsersCollection();
        ArrayList<User> users = usersCollection.getUsersByLogin(login);
        
        boolean isSuccessfull = false;
        if(!users.isEmpty()){
            isSuccessfull = password.equals(users.getFirst().getMainPassword());
            if(isSuccessfull) {
                users.getFirst().setIsAuthenticated(isSuccessfull);
                App.setCurrentUser(users.getFirst());
            }
        }
        
        return isSuccessfull;
        
    }
}
