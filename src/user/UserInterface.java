package user;
import admin.*;
public interface UserInterface {
    public void setUsername(String username);
    public String getUsername();
    public void setEmail(String email);
    public String getEmail();
    public void setPassword(String password);
    public String getPassword();
    public void setSubscriptionType(String subscriptionType);
    public String getSubscriptionType();
    public void setWatchHistory(WatchHistory watchHistory);
    public WatchHistory getWatchHistory();
    public String getAccessLevel();
    public String toString();
}
