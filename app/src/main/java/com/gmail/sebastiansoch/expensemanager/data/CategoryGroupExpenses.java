package com.gmail.sebastiansoch.expensemanager.data;

public class CategoryGroupExpenses {
    private CategoryGroup categoryGroup;
    private Double expenses;

    public CategoryGroupExpenses(CategoryGroup categoryGroup, Double expenses) {
        this.categoryGroup = categoryGroup;
        this.expenses = expenses;
    }

    public CategoryGroup getCategoryGroup() {
        return categoryGroup;
    }

    public Double getExpenses() {
        return expenses;
    }
}
