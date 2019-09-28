package com.gmail.sebastiansoch.expensemanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PurchaseDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Purchase.db";
    private static final int DATABASE_VERSION = 11;

    public PurchaseDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBCommand.createPurchaseCategory());
        db.execSQL(DBCommand.createPurchaseGroup());
        db.execSQL(DBCommand.createGoupTiles());
        db.execSQL(DBCommand.createPurchases());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO - jak przeniesc dane ??
//        db.execSQL(DBCommand.deleteDBEntries());
        db.execSQL(DBCommand.deletePurchaseCategory());
        db.execSQL(DBCommand.deletePurchaseGroup());
        db.execSQL(DBCommand.deleteTiles());
        db.execSQL(DBCommand.deletePurchase());
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO - jak to obsluzyc ??
        onUpgrade(db, oldVersion, newVersion);
    }
}
