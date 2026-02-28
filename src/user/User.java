package user;

public abstract class User{
    private String username;
    private String email;
    private String password;
    private String subscriptionType;
    private String watchHistory;

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setSubscriptionType(String subscriptionType){
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionType(){
        return this.subscriptionType;
    }

    public void setWatchHistory(String watchHistory){
        this.watchHistory = watchHistory;
    }

    public String getWatchHistory(){
        return this.watchHistory;
    }

    public String getAccessLevel();
    public String toString();
}