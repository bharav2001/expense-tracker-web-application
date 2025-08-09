package com.expenseTracker.service;
import com.expenseTracker.model.Expense;
import com.expenseTracker.respository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final ExpenseRepository expenseRepository;
    private double balance = 0.0;

    //Add Transaction method
    public Expense addTransaction(Expense expense) {
        if (expense.getAmount()==null || expense.getAmount()<=0)//
            throw new IllegalArgumentException("Amount should be positive");// Lo
        else if(expense.getType()==null || (!"Income".equalsIgnoreCase(expense.getType())) && (!"Expense".equalsIgnoreCase(expense.getType())))
            throw new IllegalArgumentException("Type must be 'Expense' or 'Income' ");
        else if(expense.getDate()==null)
            expense.setDate(LocalDate.now());

        Expense savedExpense = expenseRepository.save(expense);
        if (savedExpense.isIncome()) {
            balance += expense.getAmount();
            System.out.println("Added Income: " + expense.getAmount() + ". New balance: " + balance);
        } else {
            balance -= expense.getAmount();
            System.out.println("Added Expense: " + expense.getAmount() + ". New balance: " + balance);
        }
        return savedExpense;
    }

    //view all transactions
    public List<Expense> getAllTransactions() {
        return expenseRepository.findAll();
    }

    //View transaction by id
    public Expense getTransactionById(long id) {
        return expenseRepository.findById(id);
    }

    //Update transaction by id
    public Expense updateTransaction(long id,Expense updatedExpense) {
       Expense existing = expenseRepository.findById(id);
       if(existing == null) {
           throw new IllegalArgumentException("Transaction with id " + id + " does not exist.");
       }
       if(updatedExpense.getAmount()==null || existing.getAmount()<=0) {
           throw new IllegalArgumentException("Amount should be positive");
       }
       if(updatedExpense.getType()==null || (!"Income".equalsIgnoreCase(updatedExpense.getType())) && (!"Expense".equalsIgnoreCase(updatedExpense.getType()))) {
           throw new IllegalArgumentException("Type must be 'Expense' or 'Income'");
       }
       if(updatedExpense.getDate()==null) {
           updatedExpense.setDate(LocalDate.now());
       }
       if(updatedExpense.getAmount()!=null)
           existing.setAmount(updatedExpense.getAmount());
       if(updatedExpense.getDescription()!=null)
           existing.setDescription(updatedExpense.getDescription());
       if(updatedExpense.getDate()!=null)
           existing.setDate(updatedExpense.getDate());
       if(updatedExpense.getType()!=null)
           existing.setType(updatedExpense.getType());

       Expense rowsUpdated = expenseRepository.update(existing);
       if(rowsUpdated==null)
              throw new IllegalStateException("Transaction with id " + id + " does not exist.");
         double oldAmount = existing.isIncome()? existing.getAmount() : -existing.getAmount();
         double newAmount = updatedExpense.isIncome() ? updatedExpense.getAmount() : -updatedExpense.getAmount();
         balance += (newAmount - oldAmount);
         System.out.println("Transaction updated: " + existing);
        return existing;
    }

    //Delete transaction by id
    public void deleteTransaction(long id) {
        Expense existing = expenseRepository.findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Transaction with id " + id + " does not exist.");
        }
        if(existing.isIncome()){
            balance -= existing.getAmount();
        } else {
            balance += existing.getAmount();
        }
        int rowsDeleted = expenseRepository.deleteById(id);
        if (rowsDeleted == 0) {
            throw new IllegalStateException("Transaction with id " + id + " does not exist.");
        }
        System.out.println("Transaction with id " + id + " deleted successfully.");
    }
    public double getBalance() {
        return balance;
    }


}
