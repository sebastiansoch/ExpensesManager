package com.gmail.sebastiansoch.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {
    private ExpenseManagerRepo repository;
    private ArrayList<PurchaseGroupTiles> catagoryTilesInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        repository = (ExpenseManagerRepo) intent.getParcelableExtra("REPO");
        getSettings();
    }

    private void getSettings() {
        catagoryTilesInfo = (ArrayList<PurchaseGroupTiles>) repository.getPurchaseGroupTilesInfo();
    }


    public void saveSettings(View view) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("PURCHASE_GROUP_TAILS", catagoryTilesInfo);
        setResult(RESULT_OK, intent);
        finish();
    }
}
