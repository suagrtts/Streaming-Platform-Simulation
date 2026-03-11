package platform;

import billing.*;
import content.*;
import java.util.*;
import user.*;

public class Platform {
    private final ContentLibrary contentLibrary = new ContentLibrary();
    private final List<User> users = new ArrayList<>();
    private final List<Subscription> subscriptions = new ArrayList<>();
    private String currentUser;
    private String currentSubscription;

    public ContentLibrary getContentLibrary(){
        return contentLibrary;
    }

    public void setCurrentUser(String username){
        this.currentUser = username;
    }

    public void setCurrentSubscription(String subscription){
        this.currentSubscription = subscription;
    }

    public String getCurrentUser(){
        return currentUser;
    }

    public String getCurrentSubscription(){
        return currentSubscription;
    }

    public void addUser(User user){
        if (user != null) {
            users.add(user);
        }
    }

    public void addSubscription(Subscription subscription){
        if (subscription != null) {
            subscriptions.add(subscription);
        }
    }

    public List<User> getUsers(){
        return users;
    }

    public List<Subscription> getSubscriptions(){
        return subscriptions;
    }

    public User findUserByUsername(String username){
        if (username == null) return null;
        for (User user : users) {
            if (user.getUsername() != null && user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }
}
