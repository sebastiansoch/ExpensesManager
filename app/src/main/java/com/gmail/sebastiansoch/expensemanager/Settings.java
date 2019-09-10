package com.gmail.sebastiansoch.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Settings extends AppCompatActivity {
    private ExpenseManagerRepo repository;
    private ArrayList<CategoryTiles> catagoryTilesInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        repository = (ExpenseManagerRepo) intent.getSerializableExtra("REPO");
        getSettings();

    }

    private void getSettings() {
        catagoryTilesInfo = (ArrayList<CategoryTiles>) repository.getCatagoryTilesInfo();
    }


    public void saveSettings(View view) {
        Intent intent = new Intent();
        intent.putExtra("CATEGORY_TAILS", catagoryTilesInfo);
        setResult(RESULT_OK, intent);
        finish();
    }
}
