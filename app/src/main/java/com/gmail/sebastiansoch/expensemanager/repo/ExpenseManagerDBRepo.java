package com.gmail.sebastiansoch.expensemanager.repo;

import android.content.Context;

import com.gmail.sebastiansoch.expensemanager.data.Category;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroup;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroupTile;
import com.gmail.sebastiansoch.expensemanager.data.Purchase;
import com.gmail.sebastiansoch.expensemanager.database.ExpenseManagerDAO;
import com.gmail.sebastiansoch.expensemanager.database.model.CategoryDTO;
import com.gmail.sebastiansoch.expensemanager.database.model.CategoryGroupDTO;
import com.gmail.sebastiansoch.expensemanager.database.model.PurchaseDTO;
import com.gmail.sebastiansoch.expensemanager.database.model.TilesDTO;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public void saveCategoriesSettings(Map<CategoryGroup, List<Category>> categoriesSettings) {
        List<CategoryGroup> categoryGroups = new ArrayList<>(categoriesSettings.keySet());
        List<CategoryGroupDTO> categoryGroupsDTO = expenseManagerDAO.getAllCategoryGroups();

        for (CategoryGroup categoryGroup : categoryGroups) {
            String currentCategoryGroupDTOName = "";

            for (CategoryGroupDTO categoryGroupDTO : categoryGroupsDTO) {
                if (categoryGroup.getName().equals(categoryGroupDTO.getName())) {
                    categoryGroupDTO.setHide(categoryGroup.isHide());
                    currentCategoryGroupDTOName = categoryGroupDTO.getName();
                }
            }

            if (!currentCategoryGroupDTOName.isEmpty()) {
                List<Category> categories = categoriesSettings.get(categoryGroup);
                List<CategoryDTO> categoriesDTOForGroup = expenseManagerDAO.getAllCategoriesForGroup(currentCategoryGroupDTOName);

                for (Category category : categories) {

                    for (CategoryDTO categoryDTO : categoriesDTOForGroup) {
                        if (category.getName().equals(categoryDTO.getName())) {
                            categoryDTO.setHide(category.isHide());
                        }
                    }
                }

                expenseManagerDAO.saveCategorySettings(categoriesDTOForGroup);

            }
        }

        expenseManagerDAO.saveCategoryGroupsSettings(categoryGroupsDTO);
    }

    @Override
    public void saveEnteredPurchases(List<Purchase> purchaseListForDB) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String entryDate = dateFormat.format(new Date());
        if (entryDate == null) {
            throw new NullPointerException("System can't set current date, try again");
        }

        List<PurchaseDTO> enteredPurchasesDTO = new ArrayList<>();

        for(Purchase purchase : purchaseListForDB) {
            int categoryGroupId = expenseManagerDAO.getCategoryGroupIdForName(purchase.getCategoryGroupName());
            int categoryId = expenseManagerDAO.getCategoryIdForName(purchase.getCategoryGroupName(), purchase.getCategoryName());
            enteredPurchasesDTO.add(new PurchaseDTO(-1, categoryGroupId, categoryId,
                    purchase.getPurchaseDate(), entryDate, Double.parseDouble(purchase.getPrice())));
        }

        expenseManagerDAO.saveEnteredPurchases(enteredPurchasesDTO);
    }

    @Override
    public List<Purchase> getLatelyEnteredPurchasesForCategoryGroup(String categoryGroupName) {
        List<Purchase> latelyEnteredPurchases = new ArrayList<>();
        List<PurchaseDTO> latelyEnteredPurchasesDTO = expenseManagerDAO.getLatelyEnteredPurchasesForCategoryGroup(categoryGroupName);

        for (PurchaseDTO purchaseDTO : latelyEnteredPurchasesDTO) {
            String categoryName = expenseManagerDAO.getCategoryNameForId(purchaseDTO.getCategoryId());
            latelyEnteredPurchases.add(new Purchase(categoryGroupName, categoryName, purchaseDTO.getPurchaseDate(), purchaseDTO.getPrice().toString()));
        }

        return latelyEnteredPurchases;
    }

}
