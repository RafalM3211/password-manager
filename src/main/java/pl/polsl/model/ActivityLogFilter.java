package pl.polsl.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.polsl.enums.Action;

/**
 * Search criteria for activity logs.
 * Null / empty field means that given filter doesn't apply.
 *
 * @author Rafał Malinowski
 * @version 1
 */
@Getter
@AllArgsConstructor
public class ActivityLogFilter {
    /**
     * User Id
     */
    private final int userId;
    /**
     * Action
     */
    private final Action action;
    /**
     * phrase
     */
    private final String phrase;
    /**
     * From datetime
     */
    private final LocalDateTime from;
    /**
     * To datetime
     */
    private final LocalDateTime to;
}