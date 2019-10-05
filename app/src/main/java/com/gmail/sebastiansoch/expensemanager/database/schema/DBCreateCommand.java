package com.gmail.sebastiansoch.expensemanager.database.schema;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.sebastiansoch.expensemanager.database.data.CategoryData;
import com.gmail.sebastiansoch.expensemanager.database.data.CategoryGroupData;
import com.gmail.sebastiansoch.expensemanager.database.data.DefaultCategoryData;
import com.gmail.sebastiansoch.expensemanager.database.data.DefaultCategoryGroupData;
import com.gmail.sebastiansoch.expensemanager.database.data.DefaultTilesData;
import com.gmail.sebastiansoch.expensemanager.database.data.TilesData;
import com.gmail.sebastiansoch.expensemanager.utils.StringBuilderWrapper;

import static com.gmail.sebastiansoch.expensemanager.database.schema.DBSchema.*;

public class DBCreateCommand {
    private static final String ID_OPTIONS = "INTEGER PRIMARY KEY";
    private static final String TEXT_OPTION = "TEXT NOT NULL";
    private static final String PRICE_OPTION = "REAL DEFAULT 0";
    private static final String OTHER_ID_OPTION = "INTEGER DEFAULT 0";
    private static final String BOOLEAN_OPTION = "INTEGER DEFAULT 0";

    private SQLiteDatabase database;

    DBCreateCommand(SQLiteDatabase database) {
        this.database = database;
    }

    public void createPurchaseCategory() {
        StringBuilderWrapper builder = new StringBuilderWrapper("CREATE TABLE");
        builder.append(SchemaCategory.TABLE_NAME);
        builder.append("(");
        builder.append(SchemaCategory.COLUMN_ID).append(ID_OPTIONS).append(",");
        builder.append(SchemaCategory.COLUMN_NAME).append(TEXT_OPTION).append(",");
        builder.append(SchemaCategory.COLUMN_IS_HIDE).append(BOOLEAN_OPTION).append(",");
        builder.append(SchemaCategory.COLUMN_CATEGORY_GROUP_ID).append(OTHER_ID_OPTION);
        builder.append(")");

        database.execSQL(builder.toString());
    }

    public void createPurchaseGroup() {
        StringBuilderWrapper builder = new StringBuilderWrapper("CREATE TABLE");
        builder.append(SchemaCategoryGroup.TABLE_NAME);
        builder.append("(");
        builder.append(SchemaCategoryGroup.COLUMN_ID).append(ID_OPTIONS).append(",");
        builder.append(SchemaCategoryGroup.COLUMN_NAME).append(TEXT_OPTION).append(",");
        builder.append(SchemaCategoryGroup.COLUMN_TAG).append(TEXT_OPTION).append(",");
        builder.append(SchemaCategoryGroup.COLUMN_IS_HIDE).append(BOOLEAN_OPTION).append(",");
        builder.append(SchemaCategoryGroup.COLUMN_TILES_ID).append(OTHER_ID_OPTION);
        builder.append(")");

        database.execSQL(builder.toString());
    }

    public void createGroupTiles() {
        StringBuilderWrapper builder = new StringBuilderWrapper("CREATE TABLE");
        builder.append(SchemaTiles.TABLE_NAME);
        builder.append("(");
        builder.append(SchemaTiles.COLUMN_ID).append(ID_OPTIONS).append(",");
        builder.append(SchemaTiles.COLUMN_PATH).append(TEXT_OPTION);
        builder.append(")");

        database.execSQL(builder.toString());
    }

    public void createPurchases() {
        StringBuilderWrapper builder = new StringBuilderWrapper("CREATE TABLE");
        builder.append(SchemaPurchase.TABLE_NAME);
        builder.append("(");
        builder.append(SchemaPurchase.COLUMN_ID).append(ID_OPTIONS).append(",");
        builder.append(SchemaPurchase.COLUMN_CATEGORY_ID).append(OTHER_ID_OPTION).append(",");
        builder.append(SchemaPurchase.COLUMN_DATE).append(TEXT_OPTION).append(",");
        builder.append(SchemaPurchase.COLUMN_PRICE).append(PRICE_OPTION);
        builder.append(")");

        database.execSQL(builder.toString());
    }

    public void setDefaultTilesData() {
        for (TilesData tilesData : DefaultTilesData.getTilesData()) {
            ContentValues values = new ContentValues();
            values.put(SchemaTiles.COLUMN_ID, tilesData.getId());
            values.put(SchemaTiles.COLUMN_PATH, tilesData.getIconPath());
            database.insert(SchemaTiles.TABLE_NAME, null, values);
        }
    }

    public void setDefaultPurchaseGroupData() {
        for (CategoryGroupData categoryGroup : DefaultCategoryGroupData.getCategoryGroupData()) {
            ContentValues values = new ContentValues();
            values.put(SchemaCategoryGroup.COLUMN_ID, categoryGroup.getId());
            values.put(SchemaCategoryGroup.COLUMN_NAME, categoryGroup.getName());
            values.put(SchemaCategoryGroup.COLUMN_TAG, categoryGroup.getTag());
            values.put(SchemaCategoryGroup.COLUMN_TILES_ID, categoryGroup.getTileId());
            database.insert(SchemaCategoryGroup.TABLE_NAME, null, values);
        }
    }

    public void setDefaultPurchaseCategoryData() {
        for (CategoryData categoryData : DefaultCategoryData.getCategoryData()) {
            ContentValues values = new ContentValues();
            values.put(SchemaCategory.COLUMN_NAME, categoryData.getName());
            values.put(SchemaCategory.COLUMN_CATEGORY_GROUP_ID, categoryData.getGroupId());
            database.insert(SchemaCategory.TABLE_NAME, null, values);
        }
    }
}

