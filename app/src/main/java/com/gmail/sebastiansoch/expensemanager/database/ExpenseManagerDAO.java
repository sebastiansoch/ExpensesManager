package com.gmail.sebastiansoch.expensemanager.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.sebastiansoch.expensemanager.database.model.CategoryDTO;
import com.gmail.sebastiansoch.expensemanager.database.model.CategoryGroupDTO;
import com.gmail.sebastiansoch.expensemanager.database.model.TilesDTO;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBHelper;
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
        for (CategoryGroupDTO categoryGroupDTO :  categoryGroupsDTO) {
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
}
