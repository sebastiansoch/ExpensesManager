package com.gmail.sebastiansoch.expensemanager.repo;

import android.content.Context;

import com.gmail.sebastiansoch.expensemanager.data.Category;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroup;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroupTile;
import com.gmail.sebastiansoch.expensemanager.database.ExpenseManagerDAO;
import com.gmail.sebastiansoch.expensemanager.database.model.CategoryDTO;
import com.gmail.sebastiansoch.expensemanager.database.model.CategoryGroupDTO;
import com.gmail.sebastiansoch.expensemanager.database.model.TilesDTO;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManagerDBRepo implements ExpenseManagerRepo {

    private static final long serialVersionUID = 1L;

    private Context applicationContext;
    private ExpenseManagerDAO expenseManagerDAO;

    public ExpenseManagerDBRepo(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void init() {
        expenseManagerDAO = new ExpenseManagerDAO(new DBHelper(applicationContext));
    }


    @Override
    public Map<CategoryGroup, List<Category>> getAllCategoriesForSettings() {
        Map<CategoryGroup, List<Category>> categoriesForSettings = new HashMap<>();

        List<CategoryGroupDTO> allCategoryGroups = expenseManagerDAO.getAllCategoryGroups();
        for (CategoryGroupDTO categoryGroupDTO : allCategoryGroups) {

            List<CategoryDTO> allCategoriesForGroup = expenseManagerDAO.getAllCategoriesForGroup(categoryGroupDTO.getName());
            List<Category> categories = new ArrayList<>();
            for (CategoryDTO categoryDTO : allCategoriesForGroup) {
                categories.add(new Category(categoryDTO.getName(), categoryDTO.isHide()));
            }

            CategoryGroup categoryGroup = new CategoryGroup(categoryGroupDTO.getName(), categoryGroupDTO.isHide());
            categoriesForSettings.put(categoryGroup, categories);
        }

        return categoriesForSettings;
    }

    @Override
    public List<Category> getAllCategoriesForGroup(String categoryGroupName) {
        final List<Category> categories = new ArrayList<>();

        List<CategoryDTO> allCategoriesForGroup = expenseManagerDAO.getAllCategoriesForGroup(categoryGroupName);
        for (CategoryDTO categoryDTO : allCategoriesForGroup) {
            if (!categoryDTO.isHide()) {
                categories.add(new Category(categoryDTO.getName(), categoryDTO.isHide()));
            }
        }

        return categories;
    }

    @Override
    public List<CategoryGroupTile> getCategoryGroupTiles() {
        List<CategoryGroupTile> categoriesGroupTile = new ArrayList<>();

        List<CategoryGroupDTO> allCategoryGroups = expenseManagerDAO.getAllCategoryGroups();
        for (CategoryGroupDTO categoryGroupDTO : allCategoryGroups) {
            if (!categoryGroupDTO.isHide()) {
                TilesDTO tileForCategoryGroup = expenseManagerDAO.getTileForCategoryGroup(categoryGroupDTO.getName());
                categoriesGroupTile.add(
                        new CategoryGroupTile(categoryGroupDTO.getName(),
                                categoryGroupDTO.getTag(), tileForCategoryGroup.getPath())
                );
            }
        }

        return categoriesGroupTile;
    }
}
