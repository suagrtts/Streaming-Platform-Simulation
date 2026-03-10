package user;

import admin.WatchHistory;

public interface UserInterface {
    void setUsername(String username);
    String getUsername();
    void setEmail(String email);
    String getEmail();
    void setPassword(String password);
    String getPassword();
    void setSubscriptionType(String subscriptionType);
    String getSubscriptionType();
    void setWatchHistory(WatchHistory watchHistory);
    WatchHistory getWatchHistory();
    String getAccessLevel();
}