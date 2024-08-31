package com.finance.service;

import com.finance.model.Budget;
import com.finance.model.Transaction;
import com.finance.util.BudgetDB;
import com.finance.util.TransactionDB;

import java.util.List;
import java.util.logging.Logger;

public class FinanceService{
    private static final Logger LOGGER = Logger.getLogger(FinanceService.class.getName());

    TransactionDB db = new TransactionDB();
    BudgetDB budgetDB = new BudgetDB();

    public synchronized void addTransaction(Transaction transaction) {
        try {
            db.saveTransaction(transaction);
            budgetDB.getBudgets().stream()
                    .filter(budget -> budget.getCategory().equals(transaction.getCategory()))
                    .forEach(budget -> budgetDB.updateBudget(budget, transaction.getAmount()));
            LOGGER.info("Transaction added successfully: " + transaction);
        } catch (Exception e) {
            LOGGER.severe("Error adding transaction: " + e.getMessage());
        }
    }

    public synchronized void viewTransactions() {
        try {
            db.getTransactions().forEach(System.out::println);
        } catch (Exception e) {
            LOGGER.severe("Error retrieving transactions: " + e.getMessage());
        }
    }

    public synchronized void searchTransaction(String query) {
        String lowerCaseQuery = query.toLowerCase();
        try {
            List<Transaction> transactions = db.getTransactions();
            List<Transaction> searchResults = transactions.stream()
                    .filter(transaction ->
                            transaction.getCategory().toLowerCase().contains(lowerCaseQuery) ||
                                    String.valueOf(transaction.getAmount()).contains(lowerCaseQuery) || // Amount conversion handled differently
                                    transaction.getDescription().toLowerCase().contains(lowerCaseQuery) ||
                                    transaction.getDate().toLowerCase().contains(lowerCaseQuery)).toList();
            // Logging results
            if (searchResults.isEmpty()) {
                LOGGER.info("No transactions found matching the query: " + query);
            } else {
                searchResults.forEach(transaction -> System.out.println(transaction.toString())); // Log the results instead of printing
            }
        } catch (Exception e) {
            LOGGER.severe("Error retrieving transactions: " + e.getMessage());
        }
    }

    public synchronized void setBudget(String category, double limit) {
        Budget budget = new Budget(category, limit);
        budgetDB.saveBudget(budget);
    }

    public synchronized void viewBudgets() {
        try {
            budgetDB.getBudgets().forEach(System.out::println);
        } catch (Exception e) {
            LOGGER.severe("Error retrieving budgets: " + e.getMessage());
        }
    }

    public synchronized void checkBudgets() {
        try {
            budgetDB.getBudgets().forEach(budget -> {
                if (budget.isOverBudget())
                    System.out.println("Over Budget: You are over limit by " + (budget.getSpent() - budget.getLimit()) + " in " + budget.getCategory());
                else
                    System.out.println("Under Budget: You can still spend " + (budget.getLimit() - budget.getSpent()) + " in " + budget.getCategory());
            });
        } catch (Exception e) {
            LOGGER.severe("Error checking budgets: " + e.getMessage());
        }
    }


}
