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
 *
 * @author cyrax
 */
public interface ActivityLogRepositoryInterface {

    // Zapis
    ActivityLog save(ActivityLog log);

    // Odczyt - wszystko
    List<ActivityLog> findAll();

    // Filtrowanie - tu w implementacji użyjesz Stream
    List<ActivityLog> findByAction(Action action);
    List<ActivityLog> findByOrigin(String origin);
    List<ActivityLog> findByDateBetween(LocalDateTime from, LocalDateTime to);
    List<ActivityLog> findByActionAndDateBetween(Action action, LocalDateTime from, LocalDateTime to);
    List<ActivityLog> findByUserId(int userId);
    List<ActivityLog> findByUserIdAndAction(int userId, Action action);
    // Pomocnicze
    Optional<ActivityLog> findLatest();
    long count();
    void deleteAll();
}