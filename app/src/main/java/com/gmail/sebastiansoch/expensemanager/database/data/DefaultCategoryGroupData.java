package com.gmail.sebastiansoch.expensemanager.database.data;

import java.util.ArrayList;
import java.util.List;

public class DefaultCategoryGroupData {

    private static List<CategoryGroupData> categoryGroupData = new ArrayList<CategoryGroupData>() {
        {
            add(new CategoryGroupData(1, "catgr_food", "food", 1));
            add(new CategoryGroupData(2, "catgr_people", "people", 2));
            add(new CategoryGroupData(3, "catgr_house", "house", 3));
            add(new CategoryGroupData(4, "catgr_entertainment", "entertainment", 4));
            add(new CategoryGroupData(5, "catgr_transportation", "transportation", 5));
            add(new CategoryGroupData(6, "catgr_money", "money", 6));
            add(new CategoryGroupData(7, "catgr_other_expenses", "other_expenses", 7));
        }
    };

    public static List<CategoryGroupData> getCategoryGroupData() {
        return categoryGroupData;
    }
}
