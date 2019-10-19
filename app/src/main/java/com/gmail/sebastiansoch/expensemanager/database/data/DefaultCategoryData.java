package com.gmail.sebastiansoch.expensemanager.database.data;

import java.util.ArrayList;
import java.util.List;

public class DefaultCategoryData {

    private static List<CategoryData> categoryData = new ArrayList<CategoryData>() {
        {
            add(new CategoryData("food", 1)); //food
            add(new CategoryData("beverage", 1)); //food
            add(new CategoryData("alcohol", 1)); //food

            add(new CategoryData("clothes", 2)); //people
            add(new CategoryData("health", 2)); //people
            add(new CategoryData("sport", 2)); //people

            add(new CategoryData("cleaning_supplies", 3)); //house
            add(new CategoryData("furnishing", 3)); //house
            add(new CategoryData("fees", 3)); //house
            add(new CategoryData("renovation", 3)); //house
            add(new CategoryData("rent", 3)); //house
            add(new CategoryData("utilities", 3)); //house

            add(new CategoryData("restaurants", 4)); //entertainment
            add(new CategoryData("vacation", 4)); //entertainment
            add(new CategoryData("cinema_concert", 4)); //entertainment
            add(new CategoryData("music_video_games", 4)); //entertainment
            add(new CategoryData("other", 4)); //entertainment

            add(new CategoryData("public_transport", 5)); //transportation
            add(new CategoryData("car_repairs", 5)); //transportation
            add(new CategoryData("car_fuel", 5)); //transportation

            add(new CategoryData("house", 6)); //money
            add(new CategoryData("car", 6)); //money
            add(new CategoryData("vacation", 6)); //money
            add(new CategoryData("retirement", 6)); //money
            add(new CategoryData("other", 6)); //money
            add(new CategoryData("mortgage", 6)); //money
            add(new CategoryData("other_installment", 6)); //money

            add(new CategoryData("insurance", 7)); //other_expenses
            add(new CategoryData("gift", 7)); //other_expenses
        }
    };

    public static List<CategoryData> getCategoryData() {
        return categoryData;
    }
}
