package com.gmail.sebastiansoch.expensemanager.database.model;

public class CategoryGroupExpensesDTO {
private CategoryGroupDTO categoryGroupDTO;
private Double expense;

    public CategoryGroupExpensesDTO(CategoryGroupDTO categoryGroupDTO, Double expense) {
        this.categoryGroupDTO = categoryGroupDTO;
        this.expense = expense;
    }

    public CategoryGroupDTO getCategoryGroupDTO() {
        return categoryGroupDTO;
    }

    public Double getExpense() {
        return expense;
    }
}
