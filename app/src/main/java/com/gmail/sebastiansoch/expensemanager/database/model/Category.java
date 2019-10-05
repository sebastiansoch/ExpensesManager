package com.gmail.sebastiansoch.expensemanager.database.model;

public class Category {
    private String categoryName;
    private String categoryGroup;

    public Category(String categoryName, String categoryGroup) {
        this.categoryName = categoryName;
        this.categoryGroup = categoryGroup;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryGroup() {
        return categoryGroup;
    }

    public void setCategoryGroup(String categoryGroup) {
        this.categoryGroup = categoryGroup;
    }
}
