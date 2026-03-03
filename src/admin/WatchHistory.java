package admin;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user's watch history.
 * This class will be fully implemented by team member CREENCIA.
 * For now, it's a stub to allow compilation of other modules.
 */
public class WatchHistory {
    private String userName;
    private List<String> historyList;

    public WatchHistory() {
        this.historyList = new ArrayList<>();
    }

    public WatchHistory(String userName) {
        this.userName = userName;
        this.historyList = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getHistoryList() {
        return historyList;
    }

    public void addRecord(String mediaTitle) {
        if (mediaTitle != null && !mediaTitle.isEmpty()) {
            historyList.add(mediaTitle);
        }
    }

    public void clearHistory() {
        historyList.clear();
    }

    @Override
    public String toString() {
        return "WatchHistory{" +
                "userName='" + userName + '\'' +
                ", historyList=" + historyList +
                '}';
    }
}
