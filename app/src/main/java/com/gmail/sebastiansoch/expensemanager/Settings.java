package com.gmail.sebastiansoch.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    private ExpenseManagerRepo repository;
    private ArrayList<CategoryTiles> categoryTilesInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        repository = (ExpenseManagerRepo) intent.getParcelableExtra("REPO");
        getSettings();

    }

    private void getSettings() {
        categoryTilesInfo = (ArrayList<CategoryTiles>) repository.getCategoryTilesInfo();
    }


    public void saveSettings(View view) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("CATEGORY_TAILS", categoryTilesInfo);
        setResult(RESULT_OK, intent);
        finish();
    }
}
