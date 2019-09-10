package com.gmail.sebastiansoch.expensemanager.repo;

import com.gmail.sebastiansoch.expensemanager.CategoryTiles;

import java.io.Serializable;
import java.util.List;

public interface ExpenseManagerRepo extends Serializable {
    List<CategoryTiles> getCatagoryTilesInfo();
}
