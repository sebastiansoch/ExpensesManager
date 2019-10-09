package com.gmail.sebastiansoch.expensemanager.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.sebastiansoch.expensemanager.database.model_dao.CategoryDAO;
import com.gmail.sebastiansoch.expensemanager.database.model_dao.CategoryGroupDAO;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBHelper;
import com.gmail.sebastiansoch.expensemanager.utils.StringBuilderWrapper;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.sebastiansoch.expensemanager.database.schema.DBSchema.SchemaCategory;
import static com.gmail.sebastiansoch.expensemanager.database.schema.DBSchema.SchemaCategoryGroup;


public class PurchaseDAO {

    private SQLiteDatabase database;

    public PurchaseDAO(DBHelper DBHelper) {
        database = DBHelper.getWritableDatabase();
    }

    List<CategoryGroupDAO> getAllCategoryGroups() {
        List<CategoryGroupDAO> categoryGroups = new ArrayList<>();

        String sql = "SELECT * FROM " + SchemaCategoryGroup.TABLE_NAME;
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();

        while (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_NAME));
            String tag = cursor.getString(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_TAG));
            boolean isHide = cursor.getInt(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_IS_HIDE)) != 0 ? true : false;
            int tileId = cursor.getInt(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_TILES_ID));
            categoryGroups.add(new CategoryGroupDAO(id, name, tag, isHide, tileId));
        }

        return categoryGroups;
    }

    List<CategoryDAO> getAllCategoriesForGroup(String groupName) {
        List<CategoryDAO> categoriesForGroup = new ArrayList<>();

        StringBuilderWrapper sql = new StringBuilderWrapper("SELECT");
        sql.append("*");
        sql.append("FROM").append(SchemaCategory.TABLE_NAME);
        sql.append("JOIN").append(SchemaCategoryGroup.TABLE_NAME);
        sql.append("ON").append(SchemaCategory.COLUMN_CATEGORY_GROUP_ID)
                .append("=").append(SchemaCategoryGroup.COLUMN_ID);
        sql.append("WHERE").append(SchemaCategoryGroup.COLUMN_NAME)
                .append("=").append(groupName);

        Cursor cursor = database.rawQuery(sql.toString(), null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(SchemaCategory.COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(SchemaCategory.COLUMN_NAME));
            boolean isHide = cursor.getInt(cursor.getColumnIndex(SchemaCategory.COLUMN_IS_HIDE)) != 0 ? true : false;
            int groupId = cursor.getInt(cursor.getColumnIndex(SchemaCategory.COLUMN_CATEGORY_GROUP_ID));
            categoriesForGroup.add(new CategoryDAO(id, name, isHide, groupId));
        }
    }


}
