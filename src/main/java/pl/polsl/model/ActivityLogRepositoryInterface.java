/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;
import java.util.List;

/**
 * Interface for activity log repositories. Defines saving, searching and deleting of activity logs
 *
 * @author Rafał Malinowski
 * @version 1
 */
public interface ActivityLogRepositoryInterface {

    /**
     * Saves activity log
     * @author Rafał Malinowski
     * @param log Activity log to save
     * @return Saved activity log
     */
    ActivityLog save(ActivityLog log);
    
    List<ActivityLog> findByFilter(ActivityLogFilter filter);

    /**
     * Gets all activity logs
     * @author Rafał Malinowski
     * @return List of all activity logs
     */
    List<ActivityLog> findAll();


    /**
     * Gets activity logs of given user
     * @author Rafał Malinowski
     * @param userId Id of the user
     * @return List of found activity logs
     */
    List<ActivityLog> findByUserId(int userId);

    /**
     * Deletes all stored activity logs
     * @author Rafał Malinowski
     */
    void deleteAll();
}
