package com.expenseTracker.service;
import com.expenseTracker.expenseTrackerService.Transaction;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ViewTransaction {
 private final AddTransaction addTransaction;

 public void displayTransactions() {
  List<Transaction> transactions = addTransaction.getTransactions();
  System.out.println("Transaction History:");
  if (transactions.isEmpty()) {
   System.out.println("No transactions recorded.");
  } else {
   for (Transaction t : transactions) {
    System.out.println(t);
   }
  }
  System.out.println("Current Balance: " + getBalance());
  System.out.println("Total Expenses " + getExpenses());
  System.out.println("Total Income " + getIncome());
 }

 public double getBalance() {
  return addTransaction.getTransactions().stream()
          .mapToDouble(t -> t.isIncome() ? t.getAmount() : -t.getAmount())
          .sum();
 }

 public double getExpenses() {
  return addTransaction.getTransactions().stream()
          .filter(t -> !t.isIncome())
          .mapToDouble(Transaction::getAmount)
          .sum();
 }

 public double getIncome() {
  return addTransaction.getTransactions().stream()
          .filter(Transaction::isIncome)
          .mapToDouble(Transaction::getAmount)
          .sum();
 }

 public List<Transaction> getTransactions() {
  return new ArrayList<>(addTransaction.getTransactions());
 }

 public Transaction findTransactionByUuid(String uuid) {
  return addTransaction.getTransactions().stream()
          .filter(t -> t.getUuid().equals(uuid))
          .findFirst()
          .orElse(null);
 }
}