package com.gmail.sebastiansoch.expensemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.gmail.sebastiansoch.expensemanager.data.Category;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroup;

import java.util.List;
import java.util.Map;

public class CategorySettings extends BaseActivity implements CategoryExpandableListListener {

    private ExpandableListView categoryExpandableList;
    private Map<CategoryGroup, List<Category>> allCategoriesForSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_settings);

        allCategoriesForSettings = expenseManagerRepo.getAllCategoriesForSettings();

        categoryExpandableList = findViewById(R.id.categoryExpandableList);
        categoryExpandableList.setAdapter(new CategoryExpandableListAdapter(CategorySettings.this, allCategoriesForSettings));
    }

    public void saveCategoriesConfiguration(View view) {
        finish();
    }

    @Override
    public void expandCategoryGroupEvent(int groupPosition, boolean isExpanded) {
        if (isExpanded) {
            categoryExpandableList.collapseGroup(groupPosition);
        } else {
            categoryExpandableList.expandGroup(groupPosition);
        }
    }

}
