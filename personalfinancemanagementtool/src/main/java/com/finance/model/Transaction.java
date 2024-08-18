package com.finance.model;

public class Transaction {
    private final double amount;
    private final String category;
    private final String description;

    public Transaction(double amount, String category, String description) {
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "Transaction{" + "amount=" + amount + ", category='" + category + '\''
                + ", description='" + description + '\'' + '}';
    }


}
