package com.expenseTracker.service;


import com.expenseTracker.expenseTrackerService.Transaction;
public class DeleteTransactions {
    private final ViewTransaction viewTransaction;

    public DeleteTransactions(ViewTransaction viewTransaction) {
        this.viewTransaction = viewTransaction;
    }

    public void deleteTransaction(String uuid) {
        Transaction transactionToDelete = viewTransaction.findTransactionByUuid(uuid);
        if (transactionToDelete != null) {
            viewTransaction.getTransactions().remove(transactionToDelete);
            System.out.println("Deleted transaction: " + transactionToDelete);
            System.out.println("New balance: " + viewTransaction.getBalance());
        } else {
            System.out.println("No transaction found with UUID: " + uuid);
        }
    }
}