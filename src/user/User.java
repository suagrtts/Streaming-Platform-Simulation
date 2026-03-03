package user;

import admin.WatchHistory;

public abstract class User{
    private String username;
    private String email;
    private String password;
    private String subscriptionType;
    private WatchHistory watchHistory;

    public User() {
        // Default no-arg constructor
        this.subscriptionType = "free";
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.subscriptionType = "free"; // default
    }

    public void setUsername(String username){
        if(username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Username cannot be empty");
        }
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setEmail(String email){
        if(email == null || !email.contains("@")){
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setPassword(String password){
        if(password == null || password.length() < 6){
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public boolean validatePassword(String candidate) {
        return password.equals(candidate);
    }

    public boolean validateEmail(String candidate) {
        return email.equals(candidate);
    }

    public void setSubscriptionType(String subscriptionType){
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionType(){
        return subscriptionType;
    }

    public void setWatchHistory(WatchHistory watchHistory){
        this.watchHistory = watchHistory;
    }

    public WatchHistory getWatchHistory(){
        return watchHistory;
    }

    public abstract String getAccessLevel();
    @Override
    public abstract String toString();
}