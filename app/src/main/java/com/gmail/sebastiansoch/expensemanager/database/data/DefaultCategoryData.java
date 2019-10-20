package com.gmail.sebastiansoch.expensemanager.database.data;

import java.util.ArrayList;
import java.util.List;

public class DefaultCategoryData {

    private static List<CategoryData> categoryData = new ArrayList<CategoryData>() {
        {
            add(new CategoryData("cat_food", 1)); //food
            add(new CategoryData("cat_beverage", 1)); //food
            add(new CategoryData("cat_alcohol", 1)); //food

            add(new CategoryData("cat_clothes", 2)); //people
            add(new CategoryData("cat_health", 2)); //people
            add(new CategoryData("cat_sport", 2)); //people

            add(new CategoryData("cat_cleaning_supplies", 3)); //house
            add(new CategoryData("cat_furnishing", 3)); //house
            add(new CategoryData("cat_fees", 3)); //house
            add(new CategoryData("cat_renovation", 3)); //house
            add(new CategoryData("cat_rent", 3)); //house
            add(new CategoryData("cat_utilities", 3)); //house

            add(new CategoryData("cat_restaurants", 4)); //entertainment
            add(new CategoryData("cat_vacation", 4)); //entertainment
            add(new CategoryData("cat_cinema_concert", 4)); //entertainment
            add(new CategoryData("cat_music_video_games", 4)); //entertainment
            add(new CategoryData("cat_other", 4)); //entertainment

            add(new CategoryData("cat_public_transport", 5)); //transportation
            add(new CategoryData("cat_car_repairs", 5)); //transportation
            add(new CategoryData("cat_car_fuel", 5)); //transportation

            add(new CategoryData("cat_savings_house", 6)); //money
            add(new CategoryData("cat_savings_car", 6)); //money
            add(new CategoryData("cat_savings_vacation", 6)); //money
            add(new CategoryData("cat_savings_retirement", 6)); //money
            add(new CategoryData("cat_savings_other", 6)); //money
            add(new CategoryData("cat_mortgage", 6)); //money
            add(new CategoryData("cat_other_installment", 6)); //money

            add(new CategoryData("cat_insurance", 7)); //other_expenses
            add(new CategoryData("cat_gift", 7)); //other_expenses
        }
    };

    public static List<CategoryData> getCategoryData() {
        return categoryData;
    }
}
