package pl.polsl.exception;

/**
 * Exception class threw when couldn't find a user for any reason
 * @author Rafał Malinowski
 * @version 1
 */
public class UserNotFoundException extends Exception {
    /**
     * Constructor
     * @author Rafał Malinowski
     * @param userId Id of user wanted to find
     */
    public UserNotFoundException(int userId) {
        super("Couldn't find user with ID: " + userId);
    }
}
