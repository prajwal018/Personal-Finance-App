package com.finance.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.finance.model.Transaction;

import java.util.logging.Logger;
import com.finance.model.Budget;


public class DataStorage {
    private final List<Transaction> transactions;
    private final Map<String, Budget> budgets;
    private final String budgetsFilePath="budgets.txt";
    private final String transactionsFilePath = "transactions.txt";
    private static final Logger LOGGER = Logger.getLogger(DataStorage.class.getName());

    public DataStorage() {
        this.transactions = Collections.synchronizedList(new ArrayList<>());
        this.budgets= Collections.synchronizedMap(new HashMap<>());
        loadTransactions();
        loadBudgets();
    }

    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(transactionsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) { // Ensure correct format
                    try {
                        double amount = Double.parseDouble(parts[0]);
                        String category = parts[1];
                        String description = parts[2];
                        transactions.add(new Transaction(amount, category, description));
                    } catch (NumberFormatException e) {
                        LOGGER.warning("Skipping invalid transaction entry: " + line);
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.severe("Error loading transactions: " + e.getMessage());
        }
    }

    private void loadBudgets() {
        try (BufferedReader reader = new BufferedReader(new FileReader(budgetsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) { // Ensure correct format
                try {
                    String category = parts[0];
                    double limit = Double.parseDouble(parts[1]);
                    double spent = Double.parseDouble(parts[2]);
                    Budget budget = new Budget(category, limit);
                    budget.addSpending(spent);
                    budgets.put(category, budget);
                } catch (NumberFormatException e) {
                    LOGGER.warning("Skipping invalid transaction entry: " + line);
                }
                }
            }
        } catch (IOException e) {
            LOGGER.severe("Error loading transactions: " + e.getMessage());
        }
    }

    public synchronized void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveTransactions();
    }
    public void setBudget(String category, Budget budget) {
        budgets.put(category, budget);
        saveBudgets();
    }

    private synchronized void saveTransactions() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(transactionsFilePath))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.getAmount() + "," + transaction.getCategory() + ","
                        + transaction.getDescription());
                writer.newLine();
            }
        } catch (Exception e) {
            LOGGER.severe("Error saving transactions: " + e.getMessage());
        }
    }

    private void saveBudgets() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(budgetsFilePath))) {
            for (Budget budget : budgets.values()) {
                writer.write(budget.getCategory() + "," + budget.getLimit() + "," + budget.getSpent());
                writer.newLine();
            }
        } catch (Exception e) {
            LOGGER.severe("Error saving transactions: " + e.getMessage());
        }
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }
    public Map<String, Budget> getBudgets() {
        return Collections.unmodifiableMap(budgets);
    }

}
