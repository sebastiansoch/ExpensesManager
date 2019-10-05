package com.gmail.sebastiansoch.expensemanager.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.sebastiansoch.expensemanager.database.model.CategoryGroupTilesInfo;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBHelper;
import com.gmail.sebastiansoch.expensemanager.utils.StringBuilderWrapper;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.sebastiansoch.expensemanager.database.schema.DBSchema.*;


public class PurchaseDAO {

    private SQLiteDatabase database;

    public PurchaseDAO(DBHelper DBHelper) {
        database = DBHelper.getWritableDatabase();
    }


}
