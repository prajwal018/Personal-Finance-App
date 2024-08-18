package com.finance.model;

public class Budget {
    private final String category;
    private final double limit;
    private double spent;

    public Budget(String category, double limit) {
        this.category = category;
        this.limit = limit;
        this.spent = 0;
    }

    public String getCategory() {
        return category;
    }

    public double getLimit() {
        return limit;
    }

    public double getSpent() {
        return spent;
    }

    public void addSpending(double amount) {
        this.spent += amount;
    }

    public boolean isOverBudget() {
        return spent > limit;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "category='" + category + '\'' +
                ", limit=" + limit +
                ", spent=" + spent +
                '}';
    }
}
