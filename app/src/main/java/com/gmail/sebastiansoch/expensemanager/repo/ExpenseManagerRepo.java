package com.gmail.sebastiansoch.expensemanager.repo;

import com.gmail.sebastiansoch.expensemanager.data.Category;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroup;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroupTile;

import java.util.List;
import java.util.Map;

public interface ExpenseManagerRepo {
    Map<CategoryGroup, List<Category>> getAllCategoriesForSettings();

    List<Category> getAllCategoriesForGroup(String categoryGroupName);

    List<CategoryGroupTile> getCategoryGroupTiles();
}
