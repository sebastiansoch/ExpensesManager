package com.gmail.sebastiansoch.expensemanager.database.schema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Purchase.db";
    private static final int DATABASE_VERSION = 18;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        DBCreateCommand dbCreateCommand = new DBCreateCommand(database);

        dbCreateCommand.createPurchaseCategory();
        dbCreateCommand.createPurchaseGroup();
        dbCreateCommand.createGroupTiles();
        dbCreateCommand.createPurchases();

        DBDefaultData dbDefaultData = new DBDefaultData(database);
        dbDefaultData.setDefaultTilesData();
        dbDefaultData.setDefaultCategoryGroupData();
        dbDefaultData.setDefaultCategoryData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        //TODO - jak przeniesc dane ??
        database.execSQL(DBDeleteCommand.deletePurchaseCategory());
        database.execSQL(DBDeleteCommand.deletePurchaseGroup());
        database.execSQL(DBDeleteCommand.deleteTiles());
        database.execSQL(DBDeleteCommand.deletePurchase());
        onCreate(database);
    }

    @Override
    public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        //TODO - jak to obsluzyc ??
        onUpgrade(database, oldVersion, newVersion);
    }
}
