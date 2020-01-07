package com.gmail.sebastiansoch.expensemanager.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.sebastiansoch.expensemanager.database.model.CategoryDTO;
import com.gmail.sebastiansoch.expensemanager.database.model.CategoryGroupDTO;
import com.gmail.sebastiansoch.expensemanager.database.model.CategoryGroupExpensesDTO;
import com.gmail.sebastiansoch.expensemanager.database.model.PurchaseDTO;
import com.gmail.sebastiansoch.expensemanager.database.model.TilesDTO;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBHelper;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBSchema.SchemaPurchase;
import com.gmail.sebastiansoch.expensemanager.database.utils.StringBuilderWrapper;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.sebastiansoch.expensemanager.database.schema.DBSchema.SchemaCategory;
import static com.gmail.sebastiansoch.expensemanager.database.schema.DBSchema.SchemaCategoryGroup;
import static com.gmail.sebastiansoch.expensemanager.database.schema.DBSchema.SchemaTiles;


public class ExpenseManagerDAO {

    private SQLiteDatabase database;

    public ExpenseManagerDAO(DBHelper DBHelper) {
        database = DBHelper.getWritableDatabase();
    }

    public List<CategoryGroupDTO> getAllCategoryGroups() {
        List<CategoryGroupDTO> categoryGroups = new ArrayList<>();

        String sql = "SELECT * FROM " + SchemaCategoryGroup.TABLE_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_NAME));
                String tag = cursor.getString(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_TAG));
                boolean isHide = cursor.getInt(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_IS_HIDE)) != 0 ? true : false;
                int tileId = cursor.getInt(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_TILES_ID));
                categoryGroups.add(new CategoryGroupDTO(id, name, tag, isHide, tileId));
            } while (cursor.moveToNext());
        }
        return categoryGroups;
    }

    public TilesDTO getTileForCategoryGroup(String categoryGroupName) {
        StringBuilderWrapper sql = new StringBuilderWrapper("SELECT");
        sql.appendColumn(SchemaTiles.TABLE_NAME, "*");
        sql.append("FROM").append(SchemaTiles.TABLE_NAME);
        sql.append("JOIN").append(SchemaCategoryGroup.TABLE_NAME);
        sql.append("ON").appendColumn(SchemaTiles.TABLE_NAME, SchemaTiles.COLUMN_ID).append("=")
                .appendColumn(SchemaCategoryGroup.TABLE_NAME, SchemaCategoryGroup.COLUMN_TILES_ID);
        sql.append("WHERE").appendColumn(SchemaCategoryGroup.TABLE_NAME, SchemaCategoryGroup.COLUMN_NAME)
                .append("=").appendValue(categoryGroupName);

        Cursor cursor = database.rawQuery(sql.toString(), null);
        cursor.moveToFirst();

        return new TilesDTO(cursor.getInt(cursor.getColumnIndex(SchemaTiles.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(SchemaTiles.COLUMN_PATH)));
    }


    public List<CategoryDTO> getAllCategoriesForGroup(String groupName) {
        List<CategoryDTO> categoriesForGroup = new ArrayList<>();

        StringBuilderWrapper sql = new StringBuilderWrapper("SELECT");
        sql.appendColumn(SchemaCategory.TABLE_NAME, "*");
        sql.append("FROM").append(SchemaCategory.TABLE_NAME);
        sql.append("JOIN").append(SchemaCategoryGroup.TABLE_NAME);
        sql.append("ON").appendColumn(SchemaCategory.TABLE_NAME, SchemaCategory.COLUMN_CATEGORY_GROUP_ID)
                .append("=").appendColumn(SchemaCategoryGroup.TABLE_NAME, SchemaCategoryGroup.COLUMN_ID);
        sql.append("WHERE").appendColumn(SchemaCategoryGroup.TABLE_NAME, SchemaCategoryGroup.COLUMN_NAME)
                .append("=").appendValue(groupName);

        Cursor cursor = database.rawQuery(sql.toString(), null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(SchemaCategory.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(SchemaCategory.COLUMN_NAME));
                boolean isHide = cursor.getInt(cursor.getColumnIndex(SchemaCategory.COLUMN_IS_HIDE)) != 0 ? true : false;
                int groupId = cursor.getInt(cursor.getColumnIndex(SchemaCategory.COLUMN_CATEGORY_GROUP_ID));
                categoriesForGroup.add(new CategoryDTO(id, name, isHide, groupId));
            } while (cursor.moveToNext());
        }

        return categoriesForGroup;
    }

    public void saveCategoryGroupsSettings(List<CategoryGroupDTO> categoryGroupsDTO) {
        for (CategoryGroupDTO categoryGroupDTO : categoryGroupsDTO) {
            ContentValues values = new ContentValues();
            values.put(SchemaCategoryGroup.COLUMN_IS_HIDE, categoryGroupDTO.isHide());

            StringBuilderWrapper where = new StringBuilderWrapper(SchemaCategoryGroup.COLUMN_ID);
            where.append("=").append(String.valueOf(categoryGroupDTO.getId()));

            database.update(SchemaCategoryGroup.TABLE_NAME, values, where.toString(), null);
        }
    }

    public void saveCategorySettings(List<CategoryDTO> categoriesDTOForGroup) {
        for (CategoryDTO categoryDTO : categoriesDTOForGroup) {
            ContentValues values = new ContentValues();
            values.put(SchemaCategory.COLUMN_IS_HIDE, categoryDTO.isHide());

            StringBuilderWrapper where = new StringBuilderWrapper(SchemaCategory.COLUMN_ID);
            where.append("=").append(String.valueOf(categoryDTO.getId()));

            database.update(SchemaCategory.TABLE_NAME, values, where.toString(), null);
        }
    }

    public void saveEnteredPurchases(List<PurchaseDTO> enteredPurchases) {
        for (PurchaseDTO purchaseDTO : enteredPurchases) {
            ContentValues values = new ContentValues();
            values.put(SchemaPurchase.COLUMN_CATEGORY_GROUP_ID, purchaseDTO.getCategoryGroupId());
            values.put(SchemaPurchase.COLUMN_CATEGORY_ID, purchaseDTO.getCategoryId());
            values.put(SchemaPurchase.COLUMN_PURCHASE_DATE, purchaseDTO.getPurchaseDate());
            values.put(SchemaPurchase.COLUMN_ENTRY_DATE, purchaseDTO.getEntryDate());
            values.put(SchemaPurchase.COLUMN_PRICE, purchaseDTO.getPrice());

            database.insert(SchemaPurchase.TABLE_NAME, null, values);
        }
    }

    public int getCategoryIdForName(String categoryGroupName, String categoryName) {
        StringBuilderWrapper sql = new StringBuilderWrapper("SELECT");
        sql.appendColumn(SchemaCategory.TABLE_NAME, SchemaCategory.COLUMN_ID);
        sql.append("FROM").append(SchemaCategory.TABLE_NAME);
        sql.append("JOIN").append(SchemaCategoryGroup.TABLE_NAME);
        sql.append("ON").appendColumn(SchemaCategory.TABLE_NAME, SchemaCategory.COLUMN_CATEGORY_GROUP_ID).append("=")
                .appendColumn(SchemaCategoryGroup.TABLE_NAME, SchemaCategoryGroup.COLUMN_ID);
        sql.append("WHERE").appendColumn(SchemaCategory.TABLE_NAME, SchemaCategory.COLUMN_NAME)
                .append("=").appendValue(categoryName);
        sql.append("AND").appendColumn(SchemaCategoryGroup.TABLE_NAME, SchemaCategoryGroup.COLUMN_NAME)
                .append("=").appendValue(categoryGroupName);

        Cursor cursor = database.rawQuery(sql.toString(), null);
        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex(SchemaCategory.COLUMN_ID));
    }

    public int getCategoryGroupIdForName(String categoryGroupName) {
        StringBuilderWrapper sql = new StringBuilderWrapper("SELECT");
        sql.appendColumn(SchemaCategoryGroup.TABLE_NAME, SchemaCategoryGroup.COLUMN_ID);
        sql.append("FROM").append(SchemaCategoryGroup.TABLE_NAME);
        sql.append("WHERE").appendColumn(SchemaCategoryGroup.TABLE_NAME, SchemaCategoryGroup.COLUMN_NAME)
                .append("=").appendValue(categoryGroupName);

        Cursor cursor = database.rawQuery(sql.toString(), null);
        cursor.moveToFirst();

        return cursor.getInt(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_ID));
    }

    public String getCategoryNameForId(int categoryId) {
        StringBuilderWrapper sql = new StringBuilderWrapper("SELECT");
        sql.appendColumn(SchemaCategory.TABLE_NAME, SchemaCategory.COLUMN_NAME);
        sql.append("FROM").append(SchemaCategory.TABLE_NAME);
        sql.append("WHERE").appendColumn(SchemaCategory.TABLE_NAME, SchemaCategory.COLUMN_ID)
                .append("=").appendValue(String.valueOf(categoryId));

        Cursor cursor = database.rawQuery(sql.toString(), null);
        cursor.moveToFirst();

        return cursor.getString(cursor.getColumnIndex(SchemaCategory.COLUMN_NAME));
    }

    public List<PurchaseDTO> getLatelyEnteredPurchasesForCategoryGroup(String categoryGroupName) {
        StringBuilderWrapper sqlMaxDate = new StringBuilderWrapper("SELECT");
        sqlMaxDate.append("MAX(").appendColumn(SchemaPurchase.TABLE_NAME, SchemaPurchase.COLUMN_ENTRY_DATE).append(")");
        sqlMaxDate.append("FROM").append(SchemaPurchase.TABLE_NAME);

        Cursor cursor = database.rawQuery(sqlMaxDate.toString(), null);
        cursor.moveToFirst();

        String maxDate = cursor.getString(0);
        cursor.close();

        int categoryGroupId = getCategoryGroupIdForName(categoryGroupName);

        StringBuilderWrapper sql = new StringBuilderWrapper("SELECT");
        sql.appendColumn(SchemaPurchase.TABLE_NAME, "*");
        sql.append("FROM").append(SchemaPurchase.TABLE_NAME);
        sql.append("WHERE").appendColumn(SchemaPurchase.TABLE_NAME, SchemaPurchase.COLUMN_ENTRY_DATE).append("=").appendValue(maxDate);
        sql.append("AND").appendColumn(SchemaPurchase.TABLE_NAME, SchemaPurchase.COLUMN_CATEGORY_GROUP_ID).append("=").appendValue(String.valueOf(categoryGroupId));

        cursor = database.rawQuery(sql.toString(), null);

        List<PurchaseDTO> latelyEnteredPurchasesDTO = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(SchemaPurchase.COLUMN_ID));
                int categoryId = cursor.getInt(cursor.getColumnIndex(SchemaPurchase.COLUMN_CATEGORY_ID));
                String purchaseDate = cursor.getString(cursor.getColumnIndex(SchemaPurchase.COLUMN_PURCHASE_DATE));
                String entryDate = cursor.getString(cursor.getColumnIndex(SchemaPurchase.COLUMN_ENTRY_DATE));
                Double price = cursor.getDouble(cursor.getColumnIndex(SchemaPurchase.COLUMN_PRICE));
                latelyEnteredPurchasesDTO.add(new PurchaseDTO(id, categoryGroupId, categoryId, purchaseDate, entryDate, price));
            } while (cursor.moveToNext());
        }

        return latelyEnteredPurchasesDTO;
    }

    public List<PurchaseDTO> getAllCategoriesForGroupInRange(String categoryGroupName, String beginDate, String endDate) {
        int categoryGroupId = getCategoryGroupIdForName(categoryGroupName);

        StringBuilderWrapper sql = new StringBuilderWrapper("SELECT");
        sql.appendColumn(SchemaPurchase.TABLE_NAME, "*");
        sql.append("FROM").append(SchemaPurchase.TABLE_NAME);
        sql.append("WHERE").appendColumn(SchemaPurchase.TABLE_NAME, SchemaPurchase.COLUMN_PURCHASE_DATE);
        sql.append("BETWEEN").appendValue(beginDate).append("AND").appendValue(endDate);
        sql.append("AND").appendColumn(SchemaPurchase.TABLE_NAME, SchemaPurchase.COLUMN_CATEGORY_GROUP_ID).append("=").appendValue(String.valueOf(categoryGroupId));

        Cursor cursor = database.rawQuery(sql.toString(), null);

        List<PurchaseDTO> filteredExpenses = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(SchemaPurchase.COLUMN_ID));
                int categoryId = cursor.getInt(cursor.getColumnIndex(SchemaPurchase.COLUMN_CATEGORY_ID));
                String purchaseDate = cursor.getString(cursor.getColumnIndex(SchemaPurchase.COLUMN_PURCHASE_DATE));
                String entryDate = cursor.getString(cursor.getColumnIndex(SchemaPurchase.COLUMN_ENTRY_DATE));
                Double price = cursor.getDouble(cursor.getColumnIndex(SchemaPurchase.COLUMN_PRICE));
                filteredExpenses.add(new PurchaseDTO(id, categoryGroupId, categoryId, purchaseDate, entryDate, price));
            } while (cursor.moveToNext());
        }

        return filteredExpenses;
    }

    public boolean removeChosenExpensesFromDB(List<Integer> purchasesDTO) {

        StringBuilderWrapper sql = new StringBuilderWrapper("DELETE");
        sql.append("FROM").append(SchemaPurchase.TABLE_NAME);
        sql.append("WHERE").appendColumn(SchemaPurchase.TABLE_NAME, SchemaPurchase.COLUMN_ID);
        sql.append("IN").append(purchasesDTO.toString().replace('[', '(').replace(']', ')'));

        String[] purchaseIds = new String[purchasesDTO.size()];
        for (int i = 0; i < purchasesDTO.size(); i++) {
            purchaseIds[i] = purchasesDTO.get(i).toString();
        }

        database.execSQL(sql.toString());
            return true;
    }
}
