package user;

public class Admin extends User {

    public boolean isAdmin() {
        return true;
    }

    public boolean hasContentModeration() {
        return true;
    }

    public boolean canManageUsers() {
        return true;
    }

    public Admin(String username, String email, String password) {
        super(username, email, password);
        setSubscriptionType("admin");
    }

    public Admin() {
            // Default constructor for flexibility
    }

    public String getAccessLevel() {
        return "Admin Access: Full control over the platform, including user management and content moderation.";
    }

     @Override
    public String toString() {
        return "Admin{" +
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", subscriptionType='" + getSubscriptionType() + '\'' +
                ", watchHistory='" + getWatchHistory() + '\'' +
                '}';
    }
}
