package com.expenseTracker.service;
import com.expenseTracker.expenseTrackerService.Transaction;

//import expenseTrackerService.Transaction;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class UpdateTransactions {
    private final List<Transaction> transactions;
    private final Scanner scanner = new Scanner(System.in);

    private double getBalance() {
        return transactions.stream()
                .mapToDouble(t -> t.isIncome() ? t.getAmount() : -t.getAmount())
                .sum();
    }

    private Transaction findTransactionByUuid(String uuid) {
        return transactions.stream()
                .filter(t -> t.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
    }

    public void updateTransaction() {
        System.out.println("Update Transaction");
        System.out.print("Enter the UUID of the transaction to update: ");
        String uuid = scanner.nextLine().trim();
        Transaction transaction = findTransactionByUuid(uuid);
        if (transaction == null) {
            System.out.println("Transaction not found with UUID: " + uuid);
            return;
        }
        System.out.println("Current Transaction: " + transaction);

        System.out.print("New Type (Income/Expense): ");
        String newType = scanner.nextLine().trim();
        if (!"Income".equalsIgnoreCase(newType) && !"Expense".equalsIgnoreCase(newType)) {
            System.out.println("Invalid type. Use 'Income' or 'Expense'.");
            return;
        }

        System.out.print("New Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("New Description: ");
        String description = scanner.nextLine().trim();

        // Update the transaction fields
        transaction.setType(newType);
        transaction.setAmount(amount);
        transaction.setDescription(description);

        System.out.println("Transaction updated: " + transaction);
        System.out.println("New balance: " + getBalance());
    }
}