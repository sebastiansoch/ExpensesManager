package com.gmail.sebastiansoch.expensemanager.repo;

import com.gmail.sebastiansoch.expensemanager.data.Category;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroup;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroupTile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public interface ExpenseManagerRepo extends Serializable {
    HashMap<CategoryGroup, ArrayList<Category>> getAllCategoriesForSettings();

    ArrayList<Category> getAllCategoriesForGroup(CategoryGroup categoryGroup);

    ArrayList<CategoryGroupTile> getCategoryGroupTiles();
}
