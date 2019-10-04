package com.gmail.sebastiansoch.expensemanager.database.data;

import java.util.ArrayList;
import java.util.List;

public class DefaultCategoryData {

    private static List<CategoryData> categoryData = new ArrayList<CategoryData>() {
        {
            add(new CategoryData("furnishing", 1)); //house
            add(new CategoryData("fees", 1)); //house
            add(new CategoryData("renovation", 1)); //house
            add(new CategoryData("rent", 1)); //house
            add(new CategoryData("utilities", 1)); //house

            add(new CategoryData("food", 2)); //food
            add(new CategoryData("beverage", 2)); //food

            add(new CategoryData("cleaning_supplies", 3)); //cleaning_supplies

            add(new CategoryData("restaurants", 4)); //entertainment
            add(new CategoryData("alcohol", 4)); //entertainment
            add(new CategoryData("vacation", 4)); //entertainment
            add(new CategoryData("cinema_concert", 4)); //entertainment
            add(new CategoryData("music_video_games", 4)); //entertainment

            add(new CategoryData("clothes", 5)); //clothes

            add(new CategoryData("public_transport", 6)); //transportation
            add(new CategoryData("car", 6)); //transportation

            add(new CategoryData("mortgage", 7)); //installment
            add(new CategoryData("other", 7)); //installment

            add(new CategoryData("house", 8)); //insurance
            add(new CategoryData("car", 8)); //insurance
            add(new CategoryData("life", 8)); //insurance
        }
    };

    public static List<CategoryData> getCategoryData() {
        return categoryData;
    }
}
