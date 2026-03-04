package billing;

public class FreeSubscription extends Subscription {

    private static final int MAX_WATCH_HOURS = 2;
    private static final boolean ADS_ENABLED = true;
    private static final int MAX_QUALITY = 480;

    public FreeSubscription(String subscriberName) {
        super(subscriberName, 0.0);
    }

    @Override
    public void displayPlanDetails() {
        System.out.println("=== Free Subscription Plan ===");
        System.out.println("Subscriber     : " + getSubscriberName());
        System.out.println("Monthly Fee    : Free");
        System.out.println("Max Watch Hours: " + MAX_WATCH_HOURS + " hrs/month");
        System.out.println("Max Quality    : " + MAX_QUALITY + "p");
        System.out.println("Ads            : " + (ADS_ENABLED ? "Yes" : "No"));
    }

    @Override
    public boolean canAccessContent(String contentType) {
        if (contentType.equalsIgnoreCase("exclusive") || contentType.equalsIgnoreCase("premium")) {
            System.out.println("Access denied. Upgrade to Premium to watch " + contentType + " content.");
            return false;
        }
        return true;
    }

    @Override
    public double getMonthlyCost() {
        return 0.0;
    }

    public int getMaxWatchHours() {
        return MAX_WATCH_HOURS;
    }

    public boolean isAdsEnabled() {
        return ADS_ENABLED;
    }

    public int getMaxQuality() {
        return MAX_QUALITY;
    }

    @Override
    public String toString() {
        return "FreeSubscription{subscriber='" + getSubscriberName() + "', fee=Free}";
    }
}