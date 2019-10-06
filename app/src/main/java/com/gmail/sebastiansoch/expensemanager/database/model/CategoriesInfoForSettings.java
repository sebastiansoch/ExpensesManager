package com.gmail.sebastiansoch.expensemanager.database.model;

import com.gmail.sebastiansoch.expensemanager.database.model_dao.CategoryDAO;
import com.gmail.sebastiansoch.expensemanager.database.model_dao.CategoryGroupDAO;

import java.util.List;

public class CategoriesInfoForSettings {
    private CategoryGroupDAO categoryGroupDAO;
    private List<CategoryDAO> categories;

    public CategoriesInfoForSettings(CategoryGroupDAO categoryGroupDAO, List<CategoryDAO> categories) {
        this.categoryGroupDAO = categoryGroupDAO;
        this.categories = categories;
    }

    public CategoryGroupDAO getCategoryGroupDAO() {
        return categoryGroupDAO;
    }

    public List<CategoryDAO> getCategories() {
        return categories;
    }
}
