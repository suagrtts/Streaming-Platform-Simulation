package billing;

import java.util.*;

public abstract class Subscription {
    private String subscriberName;
    private double monthlyFee;

    public Subscription(String subscriberName, double monthlyFee) {
        this.subscriberName = subscriberName;
        this.monthlyFee = monthlyFee;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

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
    }

    @Override
    public String toString() {
        return "Subscription{subscriber='" + subscriberName + "', monthlyFee=$" + monthlyFee + "}";
    }
}
