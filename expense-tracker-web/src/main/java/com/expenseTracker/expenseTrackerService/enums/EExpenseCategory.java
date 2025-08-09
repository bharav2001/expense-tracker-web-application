package expenseTrackerService.enums;

import lombok.Getter;

@Getter
public enum EExpenseCategory {
    FOOD_AND_DINING("Food & Dining"),
    TRANSPORTATION("Transportation"),
    ENTERTAINMENT("Entertainment"),
    UTILITIES("Utilities"),
    SHOPPING("Shopping"),
    HEALTHCARE("Healthcare");

    private final String displayName;

    EExpenseCategory(String displayName) {
        this.displayName = displayName;
    }
}