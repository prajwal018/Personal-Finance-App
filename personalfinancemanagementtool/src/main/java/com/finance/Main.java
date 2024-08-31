package com.finance;

import com.finance.model.Transaction;
import com.finance.service.FinanceService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FinanceService financeService = new FinanceService();

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("1. Add Transaction");
                System.out.println("2. View Transactions");
                System.out.println("3. Set Budget");
                System.out.println("4. View Budgets");
                System.out.println("5. Check Budgets");
                System.out.println("6. Search Transaction");
                System.out.println("7. Exit");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter amount: ");
                        double amount = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("Enter Category: ");
                        String category = sc.nextLine();
                        System.out.println("Enter description: ");
                        String description = sc.nextLine();
                        System.out.println("Enter date: ");
                        String date = sc.nextLine();
                        financeService
                                .addTransaction(new Transaction(category, amount, description, date));
                        break;
                    case 2:
                        financeService.viewTransactions();
                        break;
                    case 3:
                        System.out.println("Enter category:");
                        String budgetCategory = sc.nextLine();
                        System.out.println("Enter limit:");
                        double limit = sc.nextDouble();
                        sc.nextLine();
                        financeService.setBudget(budgetCategory, limit);
                        break;
                    case 4:
                        financeService.viewBudgets();
                        break;
                    case 5:
                        financeService.checkBudgets();
                        break;
                    case 6:
                        System.out.println("Enter Search Query:");
                        String query = sc.nextLine();
                        financeService.searchTransaction(query);
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Invalid Choice.");
                }

            }
        }
    }
}
