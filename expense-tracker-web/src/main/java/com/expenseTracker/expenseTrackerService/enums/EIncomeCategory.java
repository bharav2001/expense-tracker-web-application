package expenseTrackerService.enums;

import lombok.Getter;

@Getter
public enum EIncomeCategory {
    SALARY("Salary"),
    LOAN("Loan"),
    GIFTS("Gift");

    private final String displayName;

    EIncomeCategory(String displayName) {
        this.displayName = displayName;
    }
}