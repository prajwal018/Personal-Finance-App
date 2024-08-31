package com.finance.util;

import com.finance.model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDB {
    Connection conn = null;

    public TransactionDB() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/prajwal", "postgres", "postgres");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //create
    public void saveTransaction(Transaction transaction) {
        String query = "insert into transaction(category, amount, description, date) values ( ?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, transaction.getCategory());
            statement.setDouble(2, transaction.getAmount());
            statement.setString(3, transaction.getDescription());
            statement.setString(4, transaction.getDate());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //getAll
    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "select * from transaction";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction(rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5));
                transactions.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }
}
