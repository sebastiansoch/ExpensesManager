package com.gmail.sebastiansoch.expensemanager.database.data;

import java.util.ArrayList;
import java.util.List;

public class DefaultCategoryGroupData {

    private static List<CategoryGroupData> categoryGroupData = new ArrayList<CategoryGroupData>() {
        {
            add(new CategoryGroupData(1, "house", "house", 1));
            add(new CategoryGroupData(2, "food", "food", 2));
            add(new CategoryGroupData(3, "cleaning_supplies", "cleaning_supplies", 3));
            add(new CategoryGroupData(4, "entertainment", "entertainment", 4));
            add(new CategoryGroupData(5, "clothes", "clothes", 5));
            add(new CategoryGroupData(6, "transportation", "transportation", 6));
            add(new CategoryGroupData(7, "installment", "installment", 7));
            add(new CategoryGroupData(8, "insurance", "insurance", 8));
        }
    };

    public static List<CategoryGroupData> getCategoryGroupData() {
        return categoryGroupData;
    }
}
