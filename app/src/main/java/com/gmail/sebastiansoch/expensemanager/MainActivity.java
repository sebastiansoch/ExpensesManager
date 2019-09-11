package com.gmail.sebastiansoch.expensemanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerFakeRepo;
import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final int SETTINGS_REQ_CODE = 1;
    private ExpenseManagerRepo expenseManagerRepo;
    private ArrayList<CategoryTiles> categoryTilesInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRepository();
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        intent.putExtra("REPO", expenseManagerRepo);
        startActivityForResult(intent, SETTINGS_REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SETTINGS_REQ_CODE && resultCode == RESULT_OK) {
            categoryTilesInfo = data.getParcelableArrayListExtra("CATEGORY_TAILS");

            if (categoryTilesInfo != null && !categoryTilesInfo.isEmpty()) {
                Toast.makeText(this, "Ilość kategorii: " + categoryTilesInfo.size(), Toast.LENGTH_SHORT).show();
                createCategoryTails();
            }
        }
    }

    private void createCategoryTails() {
        TableLayout categotyTailsTableLayout = findViewById(R.id.catagoryTableLayout);
                TableRow tableRow = new TableRow(this);
        categotyTailsTableLayout.addView(tableRow);
        for (CategoryTiles categoryTiles :categoryTilesInfo) {
            ImageButton imageButton = new ImageButton(this);
            //TODO - rozwiazac problem pobierania zasobow
            //co chcemy przekazac i czy ma pewno poslugujemy sie iconami
            imageButton.setImageResource(getResources().getIdentifier("ic_settings_black_24dp", "drawable", "com.gmail.sebastiansoch.expensemanager"));
            imageButton.setTag(categoryTiles.getTilesTag());
            tableRow.addView(imageButton);
        }
    }

    private void getRepository() {
        ExpenseManagerFakeRepo expenseManagerFakeRepo = new ExpenseManagerFakeRepo();
        expenseManagerFakeRepo.init();
        expenseManagerRepo = expenseManagerFakeRepo;
    }

}
