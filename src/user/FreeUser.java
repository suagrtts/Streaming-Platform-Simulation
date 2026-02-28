package user;

public class FreeUser extends User {
    private boolean adsEnabled = true;
    private String maxQuality = "480p";

    @Override
    public String getAccessLevel() {
        return "Free Access: Ads enabled, Max Quality: " + maxQuality;
    }

    public boolean isAdsEnabled() {
        return adsEnabled;
    }

    public String getMaxQuality() {
        return maxQuality;
    }

    public boolean canDownload() {
        return false;
    }

    @Override
    public String toString() {
        return "FreeUser{" +
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", subscriptionType='" + getSubscriptionType() + '\'' +
                ", watchHistory='" + getWatchHistory() + '\'' +
                ", adsEnabled=" + adsEnabled +
                ", maxQuality='" + maxQuality + '\'' +
                '}';
    }
}
