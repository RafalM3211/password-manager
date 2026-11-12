package pl.polsl.view;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import pl.polsl.main.App;
import pl.polsl.enums.Action;
import pl.polsl.model.ActivityLog;

/**
 * Table model presenting activity logs of the currently logged in user.
 * The table has four columns: date, action, origin and details.
 * Logs are read-only.
 *
 * @author Rafał Malinowski
 * @version 1
 */
public class ActivityLogTableModel extends AbstractTableModel {
    /**
     * Application state object
     */
    private App model;
    /**
     * Label used to show error messages
     */
    private JLabel errorText;

    /**
     * Phrase for filtering logs
     */
    private String filterPhrase;

    /**
     * Action for filtering logs, null means all actions
     */
    private Action filterAction;

    /**
     * Formatter for date column
     */
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Application model setter
     * @author Rafał Malinowski
     * @param model Application state object
     */
    public void setModel(App model) {
        this.model = model;
    }

    public void setFilterPhrase(String filterPhrase) {
        this.filterPhrase = filterPhrase == null ? "" : filterPhrase;
    }

    public void setFilterAction(Action filterAction) {
        this.filterAction = filterAction;
    }

    /**
     * ActivityLogTableModel constructor
     * @author Rafał Malinowski
     * @param errorText Label used to show error messages
     */
    public ActivityLogTableModel(JLabel errorText) {
        this.model = App.getInstance();
        this.errorText = errorText;
        this.filterPhrase = "";
        this.filterAction = null;
    }

    /**
     * Returns logs of the current user, filtered by phrase and action,
     * sorted from newest to oldest.
     * @return Filtered list of activity logs
     */
    private List<ActivityLog> getFilteredLogs() {
        if (App.getCurrentUser() == null) {
            return List.of();
        }

        int currentUserId = App.getCurrentUser().getId();
        String phrase = filterPhrase.toLowerCase();

        return App.getActivityLogRepository().findAll().stream()
                .filter(log -> currentUserId == log.getUserId())
                .filter(log -> filterAction == null || log.getAction() == filterAction)
                .filter(log -> phrase.isEmpty()
                        || (log.getOrigin() != null && log.getOrigin().toLowerCase().contains(phrase))
                        || (log.getDetails() != null && log.getDetails().toLowerCase().contains(phrase))
                        || log.getAction().name().toLowerCase().contains(phrase))
                .sorted((a, b) -> b.getData().compareTo(a.getData()))
                .collect(Collectors.toList());
    }

    /**
     * Gets log at given row, useful for selection
     * @param rowIndex Index of the row
     * @return ActivityLog in the given row
     */
    public ActivityLog getLogAt(int rowIndex) {
        return getFilteredLogs().get(rowIndex);
    }

    /**
     * Refreshes the table view
     */
    public void refresh() {
        fireTableDataChanged();
    }

    /**
     * Gets number of rows, which equals the number of filtered logs
     * @author Rafał Malinowski
     * @return Number of rows in the table
     */
    @Override
    public int getRowCount() {
        return this.getFilteredLogs().size();
    }

    /**
     * Gets number of columns
     * @author Rafał Malinowski
     * @return Number of columns in the table (date, action, origin, details)
     */
    @Override
    public int getColumnCount() {
        return 4;
    }

    /**
     * Gets value of the cell
     * @author Rafał Malinowski
     * @param rowIndex Index of the row
     * @param columnIndex Index of the column: 0 for date, 1 for action, 2 for origin, 3 for details
     * @return Value in the given cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ActivityLog log = getFilteredLogs().get(rowIndex);

        switch (columnIndex) {
            case 0:
                return log.getData() == null ? "" : log.getData().format(DATE_FORMATTER);
            case 1:
                return log.getAction();
            case 2:
                return log.getOrigin();
            case 3:
                return log.getDetails();
            default:
                return "";
        }
    }

    /**
     * Gets name of the column
     * @author Rafał Malinowski
     * @param column Index of the column
     * @return Column name
     */
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "date";
            case 1:
                return "action";
            case 2:
                return "origin";
            case 3:
                return "details";
            default:
                return "";
        }
    }

    /**
     * Logs are read-only, they cannot be edited from the table
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}