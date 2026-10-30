/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.exception;

/**
 * Exception class threw when an action requires a logged in user, but nobody is authenticated
 * @author Rafał Malinowski
 * @version 1
 */
public class UnauthorizedException extends Exception {
    /**
     * Constructor without arguments
     * @author Rafał Malinowski
     */
    public UnauthorizedException() {
        this("");
    }

    /**
     * Constructor with message
     * @author Rafał Malinowski
     * @param message Error message
     */
    public UnauthorizedException(String message) {
        super("User not authorized. " + message);
    }

}
