package com.finance.service;

import java.util.HashMap;
import java.util.List;

import com.finance.model.Budget;
import com.finance.model.Transaction;
import com.finance.util.DataStorage;

import java.util.Map;
import java.util.logging.Logger;

public class FinanceService{
    private static final Logger LOGGER = Logger.getLogger(FinanceService.class.getName());
    private final DataStorage dataStorage;

    public FinanceService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public synchronized void addTransaction(Transaction transaction) {
        try {
            dataStorage.addTransaction(transaction);
            if (dataStorage.getBudgets().containsKey(transaction.getCategory())) {
                Budget budget = dataStorage.getBudgets().get(transaction.getCategory());
                budget.addSpending(transaction.getAmount());
                dataStorage.setBudget(transaction.getCategory(), budget);
            }
            LOGGER.info("Transaction added successfully: " + transaction);
        } catch (Exception e) {
            LOGGER.severe("Error adding transaction: " + e.getMessage());
        }    }

    public synchronized void viewTransactions() {
        try {
            List<Transaction> transactions = dataStorage.getTransactions();
            for (Transaction transaction : transactions) {
                System.out.println(transaction); // Consider using a formatted output or log it
            }
        } catch (Exception e) {
            LOGGER.severe("Error retrieving transactions: " + e.getMessage());
        }
    }

    public synchronized void setBudget(String category, double limit) {
        Budget budget = new Budget(category, limit);
        dataStorage.setBudget(category, budget);
    }

    public synchronized void viewBudgets() {
        Map<String, Budget> budgets = dataStorage.getBudgets();
        for (Budget budget : budgets.values()) {
            System.out.println(budget);
        }
    }

    public synchronized void checkBudgets() {
        Map<String, Budget> budgets = dataStorage.getBudgets();
        for (Budget budget : budgets.values()) {
            if (budget.isOverBudget()) {
                System.out.println("Over Budget: You are over limit by "+ (budget.getSpent() - budget.getLimit()) + " in " + budget.getCategory());

            }
            else{
                System.out.println("Under Budget: You can still spend "+ (budget.getLimit() - budget.getSpent()) + " in " + budget.getCategory());
            }
        }
    }
}
