/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;
import pl.polsl.enums.Action;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Activity log repository keeping logs in memory. Database mockup for now.
 * Search methods return logs sorted from newest to oldest
 *
 * @author Rafał Malinowski
 * @version 1
 */
public class InMemoryActivityLogRepository implements ActivityLogRepositoryInterface {

    /**
     * Stored activity logs. Thread safe collection, because UI and logic may work on different threads
     */
    // CopyOnWriteArrayList = bezpieczna przy Swing, bo UI i logika mogą działać na różnych wątkach
    private final List<ActivityLog> storage = new CopyOnWriteArrayList<>();

    /**
     * Saves activity log. If the log has no date, current date and time is set
     * @author Rafał Malinowski
     * @param log Activity log to save
     * @return Saved activity log
     */
    @Override
    public ActivityLog save(ActivityLog log) {
        if (log.getData() == null) {
            log.setData(LocalDateTime.now());
        }
        storage.add(log);
        return log;
    }

    /**
     * Gets all activity logs
     * @author Rafał Malinowski
     * @return List of all activity logs, sorted from newest to oldest
     */
    @Override
    public List<ActivityLog> findAll() {
        return storage.stream()
                .sorted(Comparator.comparing(ActivityLog::getData).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Gets activity logs with given action
     * @author Rafał Malinowski
     * @param action Action to filter by
     * @return List of found activity logs, sorted from newest to oldest
     */
    @Override
    public List<ActivityLog> findByAction(Action action) {
        return storage.stream()
                .filter(log -> log.getAction() == action)
                .sorted(Comparator.comparing(ActivityLog::getData).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Gets activity logs with given origin. Letter case is ignored
     * @author Rafał Malinowski
     * @param origin Origin to filter by
     * @return List of found activity logs, sorted from newest to oldest
     */
    @Override
    public List<ActivityLog> findByOrigin(String origin) {
        return storage.stream()
                .filter(log -> log.getOrigin() != null && log.getOrigin().equalsIgnoreCase(origin))
                .sorted(Comparator.comparing(ActivityLog::getData).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Gets activity logs from given date range. Both ends of the range are included
     * @author Rafał Malinowski
     * @param from Beginning of the date range
     * @param to End of the date range
     * @return List of found activity logs, sorted from newest to oldest
     */
    @Override
    public List<ActivityLog> findByDateBetween(LocalDateTime from, LocalDateTime to) {
        return storage.stream()
                .filter(log -> !log.getData().isBefore(from) && !log.getData().isAfter(to))
                .sorted(Comparator.comparing(ActivityLog::getData).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Gets activity logs with given action from given date range. Both ends of the range are included
     * @author Rafał Malinowski
     * @param action Action to filter by
     * @param from Beginning of the date range
     * @param to End of the date range
     * @return List of found activity logs, sorted from newest to oldest
     */
    @Override
    public List<ActivityLog> findByActionAndDateBetween(Action action, LocalDateTime from, LocalDateTime to) {
        return storage.stream()
                .filter(log -> log.getAction() == action)
                .filter(log -> !log.getData().isBefore(from) && !log.getData().isAfter(to))
                .sorted(Comparator.comparing(ActivityLog::getData).reversed())
                .collect(Collectors.toList());
    }
    
    /**
     * Gets activity logs of given user
     * @author Rafał Malinowski
     * @param userId Id of the user
     * @return List of found activity logs, sorted from newest to oldest
     */
    @Override
    public List<ActivityLog> findByUserId(int userId) {
        storage.getFirst().getUserId();
        return storage.stream()
            .filter(log -> log.getUserId() == userId)
            .sorted(Comparator.comparing(ActivityLog::getData).reversed())
            .collect(Collectors.toList());
    }

    /**
     * Gets activity logs of given user with given action
     * @author Rafał Malinowski
     * @param userId Id of the user
     * @param action Action to filter by
     * @return List of found activity logs, sorted from newest to oldest
     */
    @Override
    public List<ActivityLog> findByUserIdAndAction(int userId, Action action) {
        return storage.stream()
            .filter(log -> log.getUserId() == userId)
            .filter(log -> log.getAction() == action)
            .sorted(Comparator.comparing(ActivityLog::getData).reversed())
            .collect(Collectors.toList());
    }

    /**
     * Gets the most recent activity log
     * @author Rafał Malinowski
     * @return The most recent activity log or empty optional if there are no logs
     */
    @Override
    public Optional<ActivityLog> findLatest() {
        return storage.stream()
                .max(Comparator.comparing(ActivityLog::getData));
    }

    /**
     * Gets number of stored activity logs
     * @author Rafał Malinowski
     * @return Number of stored activity logs
     */
    @Override
    public long count() {
        return storage.size();
    }

    /**
     * Deletes all stored activity logs
     * @author Rafał Malinowski
     */
    @Override
    public void deleteAll() {
        storage.clear();
    }
}
