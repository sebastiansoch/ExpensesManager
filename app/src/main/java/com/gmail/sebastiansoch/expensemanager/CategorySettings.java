package com.gmail.sebastiansoch.expensemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.gmail.sebastiansoch.expensemanager.data.Category;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroup;

import java.util.List;
import java.util.Map;

public class CategorySettings extends BaseActivity {

    private TableLayout categoryVisibilityLayout;
    private Map<CategoryGroup, List<Category>> allCategoriesForSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_settings);
        categoryVisibilityLayout = findViewById(R.id.categoryVisibilityLayout);
        showAllCategories();
    }

    private void showAllCategories() {
        allCategoriesForSettings = expenseManagerRepo.getAllCategoriesForSettings();

    }

    public void saveCategoriesConfiguration(View view) {
        finish();
    }
}
