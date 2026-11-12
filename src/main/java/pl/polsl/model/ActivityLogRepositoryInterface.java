/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;
import pl.polsl.enums.Action;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Interface for activity log repositories. Defines saving, searching and deleting of activity logs
 *
 * @author Rafał Malinowski
 * @version 1
 */
public interface ActivityLogRepositoryInterface {

    // Zapis
    /**
     * Saves activity log
     * @author Rafał Malinowski
     * @param log Activity log to save
     * @return Saved activity log
     */
    ActivityLog save(ActivityLog log);

    // Odczyt - wszystko
    /**
     * Gets all activity logs
     * @author Rafał Malinowski
     * @return List of all activity logs
     */
    List<ActivityLog> findAll();

    // Filtrowanie - tu w implementacji użyjesz Stream
    /**
     * Gets activity logs with given action
     * @author Rafał Malinowski
     * @param action Action to filter by
     * @return List of found activity logs
     */
    List<ActivityLog> findByAction(Action action);

    /**
     * Gets activity logs with given origin
     * @author Rafał Malinowski
     * @param origin Origin to filter by
     * @return List of found activity logs
     */
    List<ActivityLog> findByOrigin(String origin);

    /**
     * Gets activity logs from given date range
     * @author Rafał Malinowski
     * @param from Beginning of the date range
     * @param to End of the date range
     * @return List of found activity logs
     */
    List<ActivityLog> findByDateBetween(LocalDateTime from, LocalDateTime to);

    /**
     * Gets activity logs with given action from given date range
     * @author Rafał Malinowski
     * @param action Action to filter by
     * @param from Beginning of the date range
     * @param to End of the date range
     * @return List of found activity logs
     */
    List<ActivityLog> findByActionAndDateBetween(Action action, LocalDateTime from, LocalDateTime to);

    /**
     * Gets activity logs of given user
     * @author Rafał Malinowski
     * @param userId Id of the user
     * @return List of found activity logs
     */
    List<ActivityLog> findByUserId(int userId);

    /**
     * Gets activity logs of given user with given action
     * @author Rafał Malinowski
     * @param userId Id of the user
     * @param action Action to filter by
     * @return List of found activity logs
     */
    List<ActivityLog> findByUserIdAndAction(int userId, Action action);
    // Pomocnicze
    /**
     * Gets the most recent activity log
     * @author Rafał Malinowski
     * @return The most recent activity log or empty optional if there are no logs
     */
    Optional<ActivityLog> findLatest();

    /**
     * Gets number of stored activity logs
     * @author Rafał Malinowski
     * @return Number of stored activity logs
     */
    long count();

    /**
     * Deletes all stored activity logs
     * @author Rafał Malinowski
     */
    void deleteAll();
}
