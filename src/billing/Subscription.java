package billing;

public abstract class Subscription {
    private String planType;
    private double price;
    private String billingCycle;
    private String features;

    public abstract double calculateBill();

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getPlanType() {
        return planType;
    }

    public double getPrice() {
        return price;
    }

    public String getBillingCycle() {
        return billingCycle;
    }

    public String getFeatures() {
        return features;
    }

    public String toString() {
        return "Subscription Plan: " + planType + "\nPrice: $" + price + "\nBilling Cycle: " + billingCycle + "\nFeatures: " + features;
    }



}
