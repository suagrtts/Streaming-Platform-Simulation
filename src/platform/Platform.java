package platform;

import content.*;

public class Platform {
    private final ContentLibrary contentLibrary = new ContentLibrary();
    private String currentUser;
    private String currentSubscription;

    public ContentLibrary getContentLibrary(){
        return contentLibrary;
    }

    public void setCurrentUser(String username){
        this.currentUser = username;
    }

    public void setCurrentSubsription(String subscription){
        this.currentSubscription = subscription;
    }

    public String getCurrentUser(){
        return currentUser;
    }

    public String getCurrentSubscription(){
        return currentSubscription;
    }
}
