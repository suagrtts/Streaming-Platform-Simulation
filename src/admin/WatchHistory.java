package admin;

import content.Media;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user's watch history.
 * This class will be fully implemented by team member CREENCIA.
 * For now, it's a stub to allow compilation of other modules.
 */
public class WatchHistory {
    private String userName;
    private final List<String> historyList;
    private final List<Media> watchedMedia;

    public WatchHistory() {
        this.historyList = new ArrayList<>();
        this.watchedMedia = new ArrayList<>();
    }

    public WatchHistory(String userName) {
        this.userName = userName;
        this.historyList = new ArrayList<>();
        this.watchedMedia = new ArrayList<>();
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

    public List<Media> getWatchedMedia() {
        return watchedMedia;
    }

    public void addRecord(String mediaTitle) {
        if (mediaTitle != null && !mediaTitle.isEmpty()) {
            historyList.add(mediaTitle);
        }
    }

    public void addWatchedMedia(Media media) {
        if (media != null) {
            watchedMedia.add(media);
        }
    }

    public void clearHistory() {
        historyList.clear();
        watchedMedia.clear();
    }

    public String getWatchMedia() {
        if (historyList.isEmpty()) {
            return "No media watched yet.";
        }
        return String.join(", ", historyList);
    }


    @Override
    public String toString() {
        return "WatchHistory{" +
                "userName='" + userName + '\'' +
                ", historyList=" + historyList +
                '}';
    }
}
