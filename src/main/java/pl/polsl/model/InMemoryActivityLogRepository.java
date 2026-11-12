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
 *
 * @author cyrax
 */
public class InMemoryActivityLogRepository implements ActivityLogRepositoryInterface {

    // CopyOnWriteArrayList = bezpieczna przy Swing, bo UI i logika mogą działać na różnych wątkach
    private final List<ActivityLog> storage = new CopyOnWriteArrayList<>();

    @Override
    public ActivityLog save(ActivityLog log) {
        if (log.getData() == null) {
            log.setData(LocalDateTime.now());
        }
        storage.add(log);
        return log;
    }

    @Override
    public List<ActivityLog> findAll() {
        return storage.stream()
                .sorted(Comparator.comparing(ActivityLog::getData).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityLog> findByAction(Action action) {
        return storage.stream()
                .filter(log -> log.getAction() == action)
                .sorted(Comparator.comparing(ActivityLog::getData).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityLog> findByOrigin(String origin) {
        return storage.stream()
                .filter(log -> log.getOrigin() != null && log.getOrigin().equalsIgnoreCase(origin))
                .sorted(Comparator.comparing(ActivityLog::getData).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityLog> findByDateBetween(LocalDateTime from, LocalDateTime to) {
        return storage.stream()
                .filter(log -> !log.getData().isBefore(from) && !log.getData().isAfter(to))
                .sorted(Comparator.comparing(ActivityLog::getData).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityLog> findByActionAndDateBetween(Action action, LocalDateTime from, LocalDateTime to) {
        return storage.stream()
                .filter(log -> log.getAction() == action)
                .filter(log -> !log.getData().isBefore(from) && !log.getData().isAfter(to))
                .sorted(Comparator.comparing(ActivityLog::getData).reversed())
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ActivityLog> findByUserId(int userId) {
        storage.getFirst().getUserId();
        return storage.stream()
            .filter(log -> log.getUserId() == userId)
            .sorted(Comparator.comparing(ActivityLog::getData).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public List<ActivityLog> findByUserIdAndAction(int userId, Action action) {
        return storage.stream()
            .filter(log -> log.getUserId() == userId)
            .filter(log -> log.getAction() == action)
            .sorted(Comparator.comparing(ActivityLog::getData).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public Optional<ActivityLog> findLatest() {
        return storage.stream()
                .max(Comparator.comparing(ActivityLog::getData));
    }

    @Override
    public long count() {
        return storage.size();
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }
}
