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
    private static final String OTHER_ID_OPTION = "REAL DEFAULT 0";

    private SQLiteDatabase database;

    DBCreateCommand(SQLiteDatabase database) {
        this.database = database;
    }

    public void createPurchaseCategory() {
        StringBuilderWrapper builder = new StringBuilderWrapper("CREATE TABLE");
        builder.append(PurchaseCategory.TABLE_NAME);
        builder.append("(");
        builder.append(PurchaseCategory.COLUMN_ID);
        builder.append(ID_OPTIONS + ",");
        builder.append(PurchaseCategory.COLUMN_NAME);
        builder.append(TEXT_OPTION + ",");
        builder.append(PurchaseCategory.COLUMN_PURCHASE_GROUP_ID);
        builder.append(OTHER_ID_OPTION);
        builder.append(")");

        database.execSQL(builder.toString());
    }

    public void createPurchaseGroup() {
        StringBuilderWrapper builder = new StringBuilderWrapper("CREATE TABLE");
        builder.append(PurchaseGroup.TABLE_NAME);
        builder.append("(");
        builder.append(PurchaseGroup.COLUMN_ID);
        builder.append(ID_OPTIONS + ",");
        builder.append(PurchaseGroup.COLUMN_NAME);
        builder.append(TEXT_OPTION + ",");
        builder.append(PurchaseGroup.COLUMN_TAG);
        builder.append(TEXT_OPTION + ",");
        builder.append(PurchaseGroup.COLUMN_TILES_ID);
        builder.append(OTHER_ID_OPTION);
        builder.append(")");

        database.execSQL(builder.toString());
    }

    public void createGroupTiles() {
        StringBuilderWrapper builder = new StringBuilderWrapper("CREATE TABLE");
        builder.append(Tiles.TABLE_NAME);
        builder.append("(");
        builder.append(Tiles.COLUMN_ID);
        builder.append(ID_OPTIONS + ",");
        builder.append(Tiles.COLUMN_PATH);
        builder.append(TEXT_OPTION);
        builder.append(")");

        database.execSQL(builder.toString());
    }

    public void createPurchases() {
        StringBuilderWrapper builder = new StringBuilderWrapper("CREATE TABLE");
        builder.append(Purchase.TABLE_NAME);
        builder.append("(");
        builder.append(Purchase.COLUMN_ID);
        builder.append(ID_OPTIONS + ",");
        builder.append(Purchase.COLUMN_CATEGORY_ID);
        builder.append(OTHER_ID_OPTION + ",");
        builder.append(Purchase.COLUMN_DATE);
        builder.append(TEXT_OPTION + ",");
        builder.append(Purchase.COLUMN_PRICE);
        builder.append(PRICE_OPTION);
        builder.append(")");

        database.execSQL(builder.toString());
    }

    public void setDefaultTilesData() {
        for (TilesData tilesData : DefaultTilesData.getTilesData()) {
            ContentValues values = new ContentValues();
            values.put(Tiles.COLUMN_ID, tilesData.getId());
            values.put(Tiles.COLUMN_PATH, tilesData.getIconPath());
            database.insert(Tiles.TABLE_NAME, null, values);
        }
    }

    public void setDefaultPurchaseGroupData() {
        for (CategoryGroupData categoryGroup : DefaultCategoryGroupData.getCategoryGroupData()) {
            ContentValues values = new ContentValues();
            values.put(PurchaseGroup.COLUMN_ID, categoryGroup.getId());
            values.put(PurchaseGroup.COLUMN_NAME, categoryGroup.getName());
            values.put(PurchaseGroup.COLUMN_TAG, categoryGroup.getTag());
            values.put(PurchaseGroup.COLUMN_TILES_ID, categoryGroup.getTileId());
            database.insert(PurchaseGroup.TABLE_NAME, null, values);
        }
    }

    public void setDefaultPurchaseCategoryData() {
        for (CategoryData categoryData : DefaultCategoryData.getCategoryData()) {
            ContentValues values = new ContentValues();
            values.put(PurchaseCategory.COLUMN_NAME, categoryData.getName());
            values.put(PurchaseCategory.COLUMN_PURCHASE_GROUP_ID, categoryData.getGroupId());
            database.insert(PurchaseCategory.TABLE_NAME, null, values);
        }
    }
}

