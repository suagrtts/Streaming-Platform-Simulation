package billing;

public abstract class Subscription {

    private String subscriberName;
    private double monthlyCost;
    private boolean isActive;

    public Subscription(String subscriberName, double monthlyCost) {
        this.subscriberName = subscriberName;
        this.monthlyCost = monthlyCost;
        this.isActive = true;
    }

    // Abstract methods - subclasses must implement
    public abstract void displayPlanDetails();
    public abstract boolean canAccessContent(String contentType);
    public abstract double getMonthlyCost();

    // Getters
    public String getSubscriberName() {
        return subscriberName;
    }

    public boolean isActive() {
        return isActive;
    }

    public double getMonthlySubscriptionCost() {
        return monthlyCost;
    }

    // Setters
    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    // Common methods
    public void cancelSubscription() {
        this.isActive = false;
        System.out.println(subscriberName + "'s subscription has been cancelled.");
    }

    public void reactivateSubscription() {
        this.isActive = true;
        System.out.println(subscriberName + "'s subscription has been reactivated.");
    }

    @Override
    public String toString() {
        return "Subscription{subscriber='" + subscriberName + "', active=" + isActive + "}";
    }
}
