package com.gmail.sebastiansoch.expensemanager.repo;

import android.content.Context;

import com.gmail.sebastiansoch.expensemanager.data.Category;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroup;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroupTile;
import com.gmail.sebastiansoch.expensemanager.database.PurchaseDAO;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpenseManagerDBRepo implements ExpenseManagerRepo {

    private static final long serialVersionUID = 1L;

    private Context applicationContext;
    private PurchaseDAO purchaseDAO;

    public ExpenseManagerDBRepo(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void init() {
        purchaseDAO = new PurchaseDAO(new DBHelper(applicationContext));
    }


    @Override
    public HashMap<CategoryGroup, ArrayList<Category>> getAllCategoriesForSettings() {
        return new HashMap<CategoryGroup, ArrayList<Category>>();
    }

    @Override
    public ArrayList<Category> getAllCategoriesForGroup(CategoryGroup categoryGroup) {
        return new ArrayList<Category>();
    }

    @Override
    public ArrayList<CategoryGroupTile> getCategoryGroupTiles() {
        return new ArrayList<CategoryGroupTile>();
    }
}
