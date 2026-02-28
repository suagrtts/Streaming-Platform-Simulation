package user;

public class PremiumUser extends User {
    private String maxQuality = "4K";
    private int downloadLimit = 100; // in GB
    private int downloadsUsed = 0;

    @Override
    public String getAccessLevel() {
        return "Premium Access: No ads, Max Quality: " + maxQuality + ", Download Limit: " + downloadLimit + "GB";
    }

    public boolean isAdsEnabled() {
        return false;
    }

    public String getMaxQuality() {
        return maxQuality;
    }

    public boolean canDownload() {
        return downloadsUsed < downloadLimit;
    }

    public int getDownloadLimit() {
        return downloadLimit;
    }

    public int getDownloadsUsed() {
        return downloadsUsed;
    }

    public void addDownload(int size) {
        if (canDownload() && (downloadsUsed + size <= downloadLimit)) {
            downloadsUsed += size;
        } else {
            System.out.println("Download limit exceeded or not enough space for this download.");
        }
    }

    public String toString() {
        return "PremiumUser{" +
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", subscriptionType='" + getSubscriptionType() + '\'' +
                ", watchHistory='" + getWatchHistory() + '\'' +
                ", maxQuality='" + maxQuality + '\'' +
                ", downloadLimit=" + downloadLimit +
                ", downloadsUsed=" + downloadsUsed +
                '}';
    }

}
