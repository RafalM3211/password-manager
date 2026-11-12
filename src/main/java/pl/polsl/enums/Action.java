/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.enums;

/**
 * Types of actions performed on stored passwords, saved in activity logs
 * @author Rafał Malinowski
 * @version 1
 */
public enum Action {
    /**
     * Password was added
     */
    ADD,
    /**
     * Password or its origin was edited
     */
    EDIT,
    /**
     * Password was deleted
     */
    DELETE
}
