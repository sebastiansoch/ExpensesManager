package com.gmail.sebastiansoch.expensemanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerFakeRepo;
import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int SETTINGS_REQ_CODE = 1;
    private ArrayList<CategoryTiles> categoryTilesInfo = new ArrayList<>();

    private ExpenseManagerRepo expenseManagerRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRepo();

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
                generateCategoryTiles();
            }
        }
    }

    private void generateCategoryTiles() {
        TableLayout tableLayout = findViewById(R.id.categoryTableLayout);
        TableRow tableRow = new TableRow(this);
        tableLayout.addView(tableRow);

        for (CategoryTiles categoryTiles : categoryTilesInfo) {
            ImageButton button = new ImageButton(this);
            button.setImageResource(getResources().getIdentifier(categoryTiles.getTilesIconPath(), "drawable", getPackageName()));
            tableRow.addView(button);
        }
    }

    private void getRepo() {
        ExpenseManagerFakeRepo expenseManagerFakeRepo = new ExpenseManagerFakeRepo();
        expenseManagerFakeRepo.init();
        expenseManagerRepo = expenseManagerFakeRepo;
    }

}
