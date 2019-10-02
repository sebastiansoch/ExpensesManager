package com.gmail.sebastiansoch.expensemanager.database.schema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gmail.sebastiansoch.expensemanager.database.schema.DBCreateCommand;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBDeleteCommand;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Purchase.db";
    private static final int DATABASE_VERSION = 11;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBCreateCommand.createPurchaseCategory());
        db.execSQL(DBCreateCommand.createPurchaseGroup());
        db.execSQL(DBCreateCommand.createGoupTiles());
        db.execSQL(DBCreateCommand.createPurchases());

        db.execSQL(DBCreateCommand.setDefaultPurchesGroupData());
        db.execSQL(DBCreateCommand.setDefaultPurchaseCategoryData());
        db.execSQL(DBCreateCommand.setDefaultTilesData());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO - jak przeniesc dane ??
        db.execSQL(DBDeleteCommand.deletePurchaseCategory());
        db.execSQL(DBDeleteCommand.deletePurchaseGroup());
        db.execSQL(DBDeleteCommand.deleteTiles());
        db.execSQL(DBDeleteCommand.deletePurchase());
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO - jak to obsluzyc ??
        onUpgrade(db, oldVersion, newVersion);
    }
}
