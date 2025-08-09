package com.expenseTracker.controller;

import com.expenseTracker.model.Expense;
import com.expenseTracker.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transactions")
public class ExpenseController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Expense> addTransaction(@RequestBody Expense expense) {
        try {
            return ResponseEntity.ok(transactionService.addTransaction(expense));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllTransactions() {
        try {
            return ResponseEntity.ok(transactionService.getAllTransactions());
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getTransactionById(@PathVariable long id) {
        try {
            Expense expense = transactionService.getTransactionById(id);
            if (expense != null) {
                return ResponseEntity.ok(expense);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateTransaction(@PathVariable long id, @RequestBody Expense updatedExpense) {
        try {
            return ResponseEntity.ok(transactionService.updateTransaction(id, updatedExpense));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Expense> deleteTransaction(@PathVariable long id) {
        try {
            transactionService.deleteTransaction(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance() {
        return ResponseEntity.ok(transactionService.getBalance());
    }
}
