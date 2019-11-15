package com.gmail.sebastiansoch.expensemanager.database.schema;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.sebastiansoch.expensemanager.database.data.CategoryData;
import com.gmail.sebastiansoch.expensemanager.database.data.CategoryGroupData;
import com.gmail.sebastiansoch.expensemanager.database.data.DefaultCategoryData;
import com.gmail.sebastiansoch.expensemanager.database.data.DefaultCategoryGroupData;
import com.gmail.sebastiansoch.expensemanager.database.data.DefaultTilesData;
import com.gmail.sebastiansoch.expensemanager.database.data.TilesData;

public class DBDefaultData {

    private final SQLiteDatabase database;

    public DBDefaultData(SQLiteDatabase database) {
        this.database = database;
    }

    public void setDefaultTilesData() {
        for (TilesData tilesData : DefaultTilesData.getTilesData()) {
            ContentValues values = new ContentValues();
            values.put(DBSchema.SchemaTiles.COLUMN_ID, tilesData.getId());
            values.put(DBSchema.SchemaTiles.COLUMN_PATH, tilesData.getIconPath());
            database.insert(DBSchema.SchemaTiles.TABLE_NAME, null, values);
        }
    }

    public void setDefaultCategoryGroupData() {
        for (CategoryGroupData categoryGroup : DefaultCategoryGroupData.getCategoryGroupData()) {
            ContentValues values = new ContentValues();
            values.put(DBSchema.SchemaCategoryGroup.COLUMN_ID, categoryGroup.getId());
            values.put(DBSchema.SchemaCategoryGroup.COLUMN_NAME, categoryGroup.getName());
            values.put(DBSchema.SchemaCategoryGroup.COLUMN_TAG, categoryGroup.getTag());
            values.put(DBSchema.SchemaCategoryGroup.COLUMN_TILES_ID, categoryGroup.getTileId());
            database.insert(DBSchema.SchemaCategoryGroup.TABLE_NAME, null, values);
        }
    }

    public void setDefaultCategoryData() {
        for (CategoryData categoryData : DefaultCategoryData.getCategoryData()) {
            ContentValues values = new ContentValues();
            values.put(DBSchema.SchemaCategory.COLUMN_NAME, categoryData.getName());
            values.put(DBSchema.SchemaCategory.COLUMN_CATEGORY_GROUP_ID, categoryData.getGroupId());
            database.insert(DBSchema.SchemaCategory.TABLE_NAME, null, values);
        }
    }

}
