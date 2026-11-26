package pl.polsl.test;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.enums.Action;
import pl.polsl.model.ActivityLog;
import pl.polsl.model.ActivityLogFilter;
import pl.polsl.model.ActivityLogRepositoryInterface;
import pl.polsl.model.InMemoryActivityLogRepository;

/**
 * Unit tests of the InMemoryActivityLogRepository class
 *
 * @author Rafał Malinowski
 * @version 1
 */
public class InMemoryActivityLogRepositoryJUnitTest {
    
    /**
     * Repository filled with test data before each test
     */
    private InMemoryActivityLogRepository repo;

    /**
     * Prepares a new repository with four logs before each test.
     * User 1 has three logs: ADD for "gmail.com" (2026-11-20), EDIT for "gmail.com" (2026-11-21)
     * and DELETE for "facebook.com" (2026-11-22).
     * User 2 has one log: ADD for "gmail.com" (2026-11-20)
     */
    @BeforeEach
    void setUp() {
        repo = new InMemoryActivityLogRepository();

        repo.save(new ActivityLog(LocalDateTime.of(2026, 11, 20, 10, 0), Action.ADD, "gmail.com", "added", 1));
        repo.save(new ActivityLog(LocalDateTime.of(2026, 11, 21, 10, 0), Action.EDIT, "gmail.com", "changed", 1));
        repo.save(new ActivityLog(LocalDateTime.of(2026, 11, 22, 10, 0), Action.DELETE, "facebook.com", "deleted", 1));

        repo.save(new ActivityLog(LocalDateTime.of(2026, 11, 20, 10, 0), Action.ADD, "gmail.com", "Added", 2));
    }
    
    /**
     * Checks that a saved log can be read back from an empty repository.
     * Test data: different actions, ordinary origins and descriptions,
     * and null origin and/or description (empty CSV values)
     *
     * @param origin Origin of the log
     * @param description Details of the log
     * @param action Action of the log
     */
    @ParameterizedTest
    @CsvSource({"origin,desc,ADD", "JaN,Nowak,DELETE", "Anna,Hey,EDIT", ",,ADD", ",desc,ADD", "org,,ADD"})
    public void shouldSaveLog(String origin, String description, Action action){
        ActivityLogRepositoryInterface logRepository = new InMemoryActivityLogRepository();
        
        ActivityLog log = new ActivityLog(LocalDateTime.now(), action, origin, description, 0);
        logRepository.save(log);
        
        var actualLogs = logRepository.findAll();
        assertEquals(log, actualLogs.getFirst(), "The only log in the repository should be the saved one");
    }
    
    /**
     * Checks filtering logs of user 1 by a phrase (letter case is ignored).
     * No action and no date range is used in the filter.
     * Test data: "gmail" (2 logs), "facebook" (1 log), "GMAIL" in upper case (2 logs)
     * and a phrase that matches nothing (0 logs)
     *
     * @param phrase Phrase to filter by
     * @param expectedCount Expected number of found logs
     */
    @ParameterizedTest
    @CsvSource({
        "gmail, 2",
        "facebook, 1",
        "GMAIL, 2",
        "nieistnieje, 0"
    })
    public void shouldFilterByPhrase(String phrase, int expectedCount) {
        ActivityLogFilter filter = new ActivityLogFilter(1, null, phrase, null, null);

        assertEquals(expectedCount, repo.findByFilter(filter).size(), "Wrong number of logs of user 1 found for phrase '" + phrase + "'");
    }
    
    
    /**
     * Checks filtering logs of user 1 by action.
     * No phrase and no date range is used in the filter.
     * Test data: each action (ADD, EDIT, DELETE) has exactly one log for user 1
     *
     * @param action Action to filter by
     * @param expectedCount Expected number of found logs
     */
    @ParameterizedTest
    @CsvSource({
        "ADD, 1",
        "EDIT, 1",
        "DELETE, 1"
    })
    public void shouldFilterByAction(Action action, int expectedCount) {
        ActivityLogFilter filter = new ActivityLogFilter(1, action, null, null, null);

        var result = repo.findByFilter(filter);

        assertEquals(expectedCount, result.size(),
                "Wrong number of logs of user 1 found for action " + action);
    }
    
    /**
     * Checks that findAll returns logs of all users.
     * Test data: the four logs prepared in setUp (three of user 1 and one of user 2)
     */
    @Test
    public void shouldFindAll(){
        var result = repo.findAll();
        
        assertEquals(4, result.size(), "findAll should return all four logs from the repository");
    }
    
    /**
     * Checks that findByUserId returns only logs of the given user.
     * Test data: user 1 has three logs, user 2 has one log
     *
     * @param userId Id of the user
     * @param expectedCount Expected number of found logs
     */
    @ParameterizedTest
    @CsvSource({
        "1, 3",
        "2, 1",
    })
    public void shouldFindByUserId(int userId, int expectedCount){
        var result = repo.findByUserId(userId);
        
        assertEquals(expectedCount, result.size(), "Wrong number of logs found for user with id " + userId);
    }
}
