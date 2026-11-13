/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;
import pl.polsl.enums.Action;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private final List<ActivityLog> storage = new ArrayList<>();

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
     * Gets activity logs by given filter.
     * @author Rafał Malinowski
     * @param filter ActivityLogFilter object with specified filter data
     * @return List of found activity logs, sorted from newest to oldest
     */
    @Override
    public List<ActivityLog> findByFilter(ActivityLogFilter filter) {
        String phrase = filter.getPhrase() == null ? "" : filter.getPhrase().toLowerCase();

        return storage.stream()
            .filter(log -> log.getUserId() == filter.getUserId())
            .filter(log -> filter.getAction() == null || log.getAction() == filter.getAction())
            .filter(log -> filter.getFrom() == null || !log.getData().isBefore(filter.getFrom()))
            .filter(log -> filter.getTo() == null || !log.getData().isAfter(filter.getTo()))
            .filter(log -> phrase.isEmpty()
                    || (log.getOrigin() != null && log.getOrigin().toLowerCase().contains(phrase))
                    || (log.getDetails() != null && log.getDetails().toLowerCase().contains(phrase))
                    || log.getAction().name().toLowerCase().contains(phrase))
            .sorted((a, b) -> b.getData().compareTo(a.getData()))
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
        return findByFilter(new ActivityLogFilter(userId, null, null, null, null));
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
        return findByFilter(new ActivityLogFilter(userId, action, null, null, null));
    }

    /**
     * Gets all activity logs
     * @author Rafał Malinowski
     * @return List of all activity logs, sorted from newest to oldest
     */
    @Override
    public List<ActivityLog> findAll() {
        return storage.stream()
            .sorted((a, b) -> b.getData().compareTo(a.getData()))
            .collect(Collectors.toList());
    }

    /**
     * Deletes all stored activity logs
     * @author Rafał Malinowski
     */
    @Override
    public void deleteAll() {
        storage.clear();
    }

    @Override
    public Optional<ActivityLog> findLatestByUserId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
