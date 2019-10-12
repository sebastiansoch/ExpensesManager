package com.gmail.sebastiansoch.expensemanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.gmail.sebastiansoch.expensemanager.data.Category;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroup;

import java.util.List;
import java.util.Map;

public class CategorySettings extends BaseActivity {

    private LinearLayout categoryVisibilityLayout;
    private Map<CategoryGroup, List<Category>> allCategoriesForSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_settings);
        categoryVisibilityLayout = findViewById(R.id.categoryVisibilityLayout);
        allCategoriesForSettings = expenseManagerRepo.getAllCategoriesForSettings();
        showAllCategories();
    }

    private void showAllCategories() {
        for (final CategoryGroup categoryGroup : allCategoriesForSettings.keySet()) {

            LayoutInflater inflaterGroup = getLayoutInflater();
            View categoryGroupView = inflaterGroup.inflate(R.layout.view_settings_category_group, categoryVisibilityLayout, false);

            CheckBox categoryGroupChb = categoryGroupView.findViewById(R.id.settingsCategoryGroupChb);
            if (categoryGroup.isHide()) {
                categoryGroupChb.setChecked(false);
            } else {
                categoryGroupChb.setChecked(true);
            }

            categoryGroupChb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    categoryGroup.setHide(!isChecked);
                }
            });

            TextView categoryGroupTV = categoryGroupView.findViewById(R.id.settingsCategoryGroupTV);
            categoryGroupTV.setText(categoryGroup.getName());

            categoryVisibilityLayout.addView(categoryGroupView);

            for (final Category category : allCategoriesForSettings.get(categoryGroup)) {
                LayoutInflater inflaterCategory = getLayoutInflater();
                View categoryView = inflaterCategory.inflate(R.layout.view_settings_category, categoryVisibilityLayout, false);

                final CheckBox categoryChb = categoryView.findViewById(R.id.settingsCategoryChb);

                if (category.isHide()) {
                    categoryChb.setChecked(false);
                } else {
                    categoryChb.setChecked(true);
                }

                categoryChb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        category.setHide(!isChecked);
                    }
                });

                TextView categoryTV = categoryView.findViewById(R.id.settingsCategoryTV);
                categoryTV.setText(category.getName());

                categoryVisibilityLayout.addView(categoryView);
            }
        }
    }

    public void saveCategoriesConfiguration(View view) {
        finish();
    }
}
