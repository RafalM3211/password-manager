package pl.polsl.model;
import pl.polsl.exception.UserNotFoundException;

import java.util.ArrayList;

/**
 * Holds all users. Database mockup for now
 * @author Rafał Malinowski
 * @version 1
 */
public class UsersCollection {
    /**
     * App users array
     */
    private ArrayList<User> users;

    /**
     * Adds user to application
     * @author Rafał Malinowski
     * @param newUser New user object
     */
    public void addUser(User newUser){
        users.add(newUser);
    }

    /**
     * Gets user by id from app users collection
     * @author Rafał Malinowski
     * @param id User id
     * @return User
     * @throws UserNotFoundException Throws if couldn't find user with given id in collection
     */
    public User getUserById(int id) throws UserNotFoundException {
        for(User user: users){
            if(user.getId()==id) return user;
        }
        
        throw new UserNotFoundException(id);
    }

    /**
     * Gets users by login
     * @author Rafał Malinowski
     * @return found users array
     * @param login User login
     */
    public ArrayList<User> getUsersByLogin(String login)  {
        ArrayList<User> filteredUsers = new ArrayList<User>();
        
        for(User user: users){
            if(user.getLogin().equals(login)) filteredUsers.add(user);
        }
        
        return filteredUsers;
    }

    /**
     * Deletes users from app collection
     * @author Rafał Malinowski
     * @throws UserNotFoundException Throws if couldn't find user with given id in collection
     * @param id User id
     */
    public void deleteUser(int id) throws UserNotFoundException {
        for(User user: users){
            if(id==user.getId()){
                users.remove(user);
                return;
            }
        }
        throw new UserNotFoundException(id);
    }
    
    public int getSize(){
        return users.size();
    }

    /**
     * UsersCollection constructor
     * @author Rafał Malinowski
     */
    public UsersCollection() {
        this.users = new ArrayList<User>();
    }
}
