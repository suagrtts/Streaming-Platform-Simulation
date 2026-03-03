package billing;

public class PremiumSubscription extends Subscription {

    private static final boolean ADS_ENABLED = false;
    private static final int MAX_QUALITY = 4320;
    private static final boolean OFFLINE_DOWNLOAD = true;

    private String planType;

    public PremiumSubscription(String subscriberName, String planType) {
        super(subscriberName, planType.equalsIgnoreCase("annual") ? 8.99 : 12.99);
        this.planType = planType;
    }

    @Override
    public void displayPlanDetails() {
        System.out.println("=== Premium Subscription Plan ===");
        System.out.println("Subscriber      : " + getSubscriberName());
        System.out.println("Plan Type       : " + planType);
        System.out.printf ("Monthly Fee     : $%.2f%n", getMonthlyCost());
        System.out.println("Max Quality     : " + MAX_QUALITY + "p (8K)");
        System.out.println("Ads             : " + (ADS_ENABLED ? "Yes" : "No"));
        System.out.println("Offline Download: " + (OFFLINE_DOWNLOAD ? "Yes" : "No"));
        System.out.println("Content Access  : All (including Exclusive & Premium)");
    }

    @Override
    public boolean canAccessContent(String contentType) {
        return true;
    }

    @Override
    public double getMonthlyCost() {
        return planType.equalsIgnoreCase("annual") ? 8.99 : 12.99;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public boolean canDownloadOffline() {
        return OFFLINE_DOWNLOAD;
    }

    public int getMaxQuality() {
        return MAX_QUALITY;
    }

    @Override
    public String toString() {
        return "PremiumSubscription{subscriber='" + getSubscriberName() 
             + "', plan='" + planType 
             + "', fee=$" + getMonthlyCost() + "}";
    }
}