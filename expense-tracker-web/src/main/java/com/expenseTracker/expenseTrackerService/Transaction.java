package com.expenseTracker.expenseTrackerService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@RequiredArgsConstructor
@Getter
@Setter
public class Transaction {
    private final String uuid= UUID.randomUUID().toString();
    private String type;
    private double amount;
    private String description;

    public Transaction(String description, double amount, String type) {
        this.description = description;
        this.amount = amount;
        this.type = type;
    }


    public boolean isIncome() {
            return "Income".equalsIgnoreCase(type);
        }

        @Override
        public String toString() {
            return "Transaction{uuid='" + uuid + "', description='" + description + "', amount=" + amount + ", type='" + type + "', isIncome=" + isIncome() + "}";
        }
    }
