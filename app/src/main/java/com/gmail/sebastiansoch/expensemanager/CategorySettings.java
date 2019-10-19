package com.gmail.sebastiansoch.expensemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.gmail.sebastiansoch.expensemanager.data.Category;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroup;

import java.util.List;
import java.util.Map;

public class CategorySettings extends BaseActivity implements CategoryExpandableListListener {

    private CategoryExpandableListAdapter categoryExpandableListAdapter;

    private ExpandableListView categoryExpandableList;
    private Map<CategoryGroup, List<Category>> allCategoriesForSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_settings);

        allCategoriesForSettings = expenseManagerRepo.getAllCategoriesForSettings();

        categoryExpandableListAdapter = new CategoryExpandableListAdapter(CategorySettings.this, allCategoriesForSettings);

        categoryExpandableList = findViewById(R.id.categoryExpandableList);
        categoryExpandableList.setAdapter(categoryExpandableListAdapter);

//        categoryExpandableList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        allCategoriesForSettings.keySet().toArray(new CategoryGroup[allCategoriesForSettings.size()])[groupPosition]
//                                + " List Expanded.", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        categoryExpandableList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        allCategoriesForSettings.keySet().toArray(new CategoryGroup[allCategoriesForSettings.size()])[groupPosition]
//                                + " List Collapsed.", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        categoryExpandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                Toast.makeText(getApplicationContext(),
//                        allCategoriesForSettings.keySet().toArray(new CategoryGroup[allCategoriesForSettings.size()])[groupPosition]
//                                + " -> " + allCategoriesForSettings.get(groupPosition).get(childPosition).getName(), Toast.LENGTH_SHORT).show();
//
//                return false;
//            }
//        });
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
