package com.gmail.sebastiansoch.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sebastiansoch.expensemanager.data.Category;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroup;
import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

import java.util.ArrayList;
import java.util.HashMap;

public class CategorySettings extends AppCompatActivity {
    private ExpenseManagerRepo repository;

    private TableLayout categoryVisibilityLayout;
    private HashMap<CategoryGroup, ArrayList<Category>> allCategoriesForSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_settings);
        Intent intent = getIntent();
        repository = (ExpenseManagerRepo) intent.getSerializableExtra("REPO");
        categoryVisibilityLayout = findViewById(R.id.categoryVisibilityLayout);

        showAllCategories();
    }

    private void showAllCategories() {
        allCategoriesForSettings = repository.getAllCategoriesForSettings();
    }


    public void saveCategoriesConfiguration(View view) {
//        Intent intent = new Intent();
//        intent.putParcelableArrayListExtra("PURCHASE_GROUP_TAILS", catagoryTilesInfo);
//        setResult(RESULT_OK, intent);
//        finish();
    }
}
