package com.gmail.sebastiansoch.expensemanager.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.sebastiansoch.expensemanager.database.model.CategoryGroupDTO;
import com.gmail.sebastiansoch.expensemanager.database.model.CategoryGroupExpensesDTO;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBHelper;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBSchema.SchemaCategoryGroup;
import com.gmail.sebastiansoch.expensemanager.database.schema.DBSchema.SchemaPurchase;
import com.gmail.sebastiansoch.expensemanager.database.utils.StringBuilderWrapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ExpensesStatisticDAO {

    private final SQLiteDatabase database;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ExpensesStatisticDAO(DBHelper DBHelper) {
        database = DBHelper.getWritableDatabase();
    }

    public List<CategoryGroupExpensesDTO> getCurrentMonthExpensesForAllCategoriesGroup() {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String firstDayOfMonth = sdf.format(calendar.getTime());
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String lastDayOfMonth = sdf.format(calendar.getTime());

        StringBuilderWrapper currentPurchases = new StringBuilderWrapper("SELECT");
        currentPurchases.appendColumn(SchemaCategoryGroup.TABLE_NAME, "*");
        currentPurchases.append(", table_expenses.price");
        currentPurchases.append("FROM").append(SchemaCategoryGroup.TABLE_NAME);
        currentPurchases.append("LEFT OUTER JOIN (");
        currentPurchases.append("SELECT");
        currentPurchases.appendColumn(SchemaPurchase.TABLE_NAME, SchemaPurchase.COLUMN_CATEGORY_GROUP_ID).append(" AS id");
        currentPurchases.append(", SUM(").appendColumn(SchemaPurchase.TABLE_NAME, SchemaPurchase.COLUMN_PRICE).append(") AS price");
        currentPurchases.append("FROM").append(SchemaPurchase.TABLE_NAME);
        currentPurchases.append("WHERE").appendColumn(SchemaPurchase.TABLE_NAME, SchemaPurchase.COLUMN_PURCHASE_DATE);
        currentPurchases.append("BETWEEN").appendValue(firstDayOfMonth).append("AND").appendValue(lastDayOfMonth);
        currentPurchases.append("GROUP BY").appendColumn(SchemaPurchase.TABLE_NAME, SchemaPurchase.COLUMN_CATEGORY_GROUP_ID);
        currentPurchases.append(") AS table_expenses");
        currentPurchases.append("ON").appendColumn(SchemaCategoryGroup.TABLE_NAME, SchemaCategoryGroup.COLUMN_ID).append("=")
                .append("table_expenses.id");

        Cursor cursor = database.rawQuery(currentPurchases.toString(), null);

        List<CategoryGroupExpensesDTO> categoryGroupExpensesDTO = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_NAME));
                String tag = cursor.getString(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_TAG));
                boolean isHide = cursor.getInt(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_IS_HIDE)) != 0 ? true : false;
                int tileId = cursor.getInt(cursor.getColumnIndex(SchemaCategoryGroup.COLUMN_TILES_ID));

                Double expense = new Double(0);
                if (!cursor.isNull(5)) {
                    expense = cursor.getDouble(5);
                }

                categoryGroupExpensesDTO.add(new CategoryGroupExpensesDTO(new CategoryGroupDTO(id, name, tag, isHide, tileId), expense));
            } while (cursor.moveToNext());
        }

        return categoryGroupExpensesDTO;
    }

}
