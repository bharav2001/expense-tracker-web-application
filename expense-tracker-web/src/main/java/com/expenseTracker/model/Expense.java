package com.expenseTracker.model;
import lombok.Data;

import java.time.LocalDate;
@Data
public class Expense {
    private long id;
    private String description;
    private Double amount;
    private String type; // "Income" or "Expense"
    private LocalDate date;

   /* public Expense(String description, Double amount, String type, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }*/
   public boolean isIncome() {
       return "Income".equalsIgnoreCase(type);
   }

}
