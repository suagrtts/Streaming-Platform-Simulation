package billing;

import java.util.*;

public abstract class Subscription {
<<<<<<< HEAD

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
=======
    private String subscriberName;
    private double monthlyFee;

    public Subscription(String subscriberName, double monthlyFee) {
        this.subscriberName = subscriberName;
        this.monthlyFee = monthlyFee;
    }

>>>>>>> 6fc0daec17bba5660cc5cceb95d62e0834914aaf
    public String getSubscriberName() {
        return subscriberName;
    }

<<<<<<< HEAD
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
=======
    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public abstract void displayPlanDetails();

    public abstract boolean canAccessContent(String contentType);

    public double getMonthlyCost() {
        return monthlyFee;
>>>>>>> 6fc0daec17bba5660cc5cceb95d62e0834914aaf
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "Subscription{subscriber='" + subscriberName + "', active=" + isActive + "}";
    }
}
=======
        return "Subscription{subscriber='" + subscriberName + "', monthlyFee=$" + monthlyFee + "}";
    }
}
>>>>>>> 6fc0daec17bba5660cc5cceb95d62e0834914aaf
