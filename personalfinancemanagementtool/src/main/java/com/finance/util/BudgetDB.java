package com.finance.util;

import com.finance.model.Budget;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BudgetDB {
    Connection conn;

    public BudgetDB() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/prajwal", "postgres", "postgres");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //create
    public void saveBudget(Budget budget) {
        String query = "insert into budget(category, blimit, bspent) values ( ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, budget.getCategory());
            statement.setDouble(2, budget.getLimit());
            statement.setDouble(3, budget.getSpent());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //getAll
    public List<Budget> getBudgets() {
        List<Budget> budgets = new ArrayList<>();
        String query = "select * from budget";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Budget t = new Budget(rs.getString(2), rs.getDouble(3), rs.getDouble(4));
                budgets.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return budgets;
    }

    public void updateBudget(Budget budget, double amount) {
        String query = "update budget set bspent=? WHERE category=?;";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDouble(1, (budget.getSpent() + amount));
            statement.setString(2, budget.getCategory());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
