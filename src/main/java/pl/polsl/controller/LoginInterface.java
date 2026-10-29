package pl.polsl.controller;

/**
 * Interface for login controllers
 * @author Rafał Malinowski
 * @version 1
 */
public interface LoginInterface {

    /**
     * Authenticates user
     * @author Rafał Malinowski
     * @return Information if auth was successful
     */
    public boolean authenticate();
}
