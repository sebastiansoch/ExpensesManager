package com.gmail.sebastiansoch.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sebastiansoch.expensemanager.data.PurchaseGroupTiles;
import com.gmail.sebastiansoch.expensemanager.database.model.CategoriesInfoForSettings;
import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    private ExpenseManagerRepo repository;
    private ArrayList<CategoriesInfoForSettings> categoryInfo = new ArrayList<>();
    private TableLayout chooseCategoryTableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        repository = intent.getParcelableExtra("REPO");
        chooseCategoryTableLayout = findViewById(R.id.chooseCategoryLayout);

        showAllCategories();

    }

    private void showAllCategories() {
        categoryInfo = (ArrayList<CategoriesInfoForSettings>) repository.getAllCategoriesInfoForSettings();
    }


    public void saveSettings(View view) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("PURCHASE_GROUP_TAILS", catagoryTilesInfo);
        setResult(RESULT_OK, intent);
        finish();
    }
}
