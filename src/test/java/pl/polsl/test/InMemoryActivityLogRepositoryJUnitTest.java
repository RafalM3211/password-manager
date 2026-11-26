/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.test;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.enums.Action;
import pl.polsl.model.ActivityLog;
import pl.polsl.model.ActivityLogFilter;
import pl.polsl.model.ActivityLogRepositoryInterface;
import pl.polsl.model.InMemoryActivityLogRepository;

/**
 *
 * @author SuperStudent-PL
 */
public class InMemoryActivityLogRepositoryJUnitTest {
    
    private InMemoryActivityLogRepository repo;

    @BeforeEach
    void setUp() {
        repo = new InMemoryActivityLogRepository();

        repo.save(new ActivityLog(LocalDateTime.of(2026, 11, 20, 10, 0), Action.ADD, "gmail.com", "added", 1));
        repo.save(new ActivityLog(LocalDateTime.of(2026, 11, 21, 10, 0), Action.EDIT, "gmail.com", "changed", 1));
        repo.save(new ActivityLog(LocalDateTime.of(2026, 11, 22, 10, 0), Action.DELETE, "facebook.com", "deleted", 1));

        repo.save(new ActivityLog(LocalDateTime.of(2026, 11, 20, 10, 0), Action.ADD, "gmail.com", "Added", 2));
    }
    
    @ParameterizedTest
    @CsvSource({"origin,desc,ADD", "JaN,Nowak,DELETE", "Anna,Hey,EDIT", ",,ADD", ",desc,ADD", "org,,ADD"})
    public void shouldSaveLog(String origin, String description, Action action){
        ActivityLogRepositoryInterface logRepository = new InMemoryActivityLogRepository();
        
        ActivityLog log = new ActivityLog(LocalDateTime.now(), action, origin, description, 0);
        logRepository.save(log);
        
        var actualLogs = logRepository.findAll();
        assertEquals(actualLogs.getFirst(), log);
    }
    
    @ParameterizedTest
    @CsvSource({
        "gmail, 2",
        "facebook, 1",
        "GMAIL, 2",
        "nieistnieje, 0"
    })
    public void shouldFilterByPhrase(String phrase, int expectedCount) {
        ActivityLogFilter filter = new ActivityLogFilter(1, null, phrase, null, null);

        assertEquals(expectedCount, repo.findByFilter(filter).size(), "asd");
    }
    
    
    @ParameterizedTest
    @CsvSource({
        "ADD, 1",
        "EDIT, 1",
        "DELETE, 1"
    })
    public void shouldFilterByAction(Action action, int expectedCount) {
        ActivityLogFilter filter = new ActivityLogFilter(1, action, null, null, null);

        var result = repo.findByFilter(filter);

        assertEquals(expectedCount, result.size());
    }
    
    @Test
    public void shouldFindAll(){
        var result = repo.findAll();
        
        assertEquals(result.size(), 4);
    }
    
    @ParameterizedTest
    @CsvSource({
        "1, 3",
        "2, 1",
    })
    public void shouldFindByUserId(int userId, int expectedCount){
        var result = repo.findByUserId(userId);
        
        assertEquals(result.size(), expectedCount);
    }
}
