package com.gmail.sebastiansoch.expensemanager.database.model;

import java.util.List;

public class CategoriesInfoForSettings {
    private CategoryGroup categoryGroup;
    private List<Category> categories;

    public CategoriesInfoForSettings(CategoryGroup categoryGroup, List<Category> categories) {
        this.categoryGroup = categoryGroup;
        this.categories = categories;
    }

    public CategoryGroup getCategoryGroup() {
        return categoryGroup;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
