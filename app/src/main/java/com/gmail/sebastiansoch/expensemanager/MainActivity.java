package com.gmail.sebastiansoch.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sebastiansoch.expensemanager.data.CategoryGroupTile;
import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerDBRepo;
import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpenseManagerRepo expenseManagerRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRepository();
        generateCategoryGroupTiles();
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, CategorySettings.class);
        intent.putExtra("REPO", expenseManagerRepo);
        startActivity(intent);
    }

    private void generateCategoryGroupTiles() {
        TableLayout tableLayout = findViewById(R.id.purchaseGroupTableLayout);

        TableRow tableRow = null;
        int iconInRow = 0;
        List<CategoryGroupTile> categoryGroupTiles = expenseManagerRepo.getCategoryGroupTiles();
        if (categoryGroupTiles != null) {
            for (final CategoryGroupTile categoryGroupTile : categoryGroupTiles) {
                if (iconInRow % 3 == 0) {
                    tableRow = new TableRow(this);
                    tableLayout.addView(tableRow);
                }
                ImageButton button = new ImageButton(this);
                button.setTag(categoryGroupTile.getTilesTag());
                button.setImageResource(getResources().getIdentifier(categoryGroupTile.getTilesIconPath(), "drawable", getPackageName()));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), PurchaseEntry.class);
                        intent.putExtra("CATEGORY_GROUP_NAME", categoryGroupTile.getCategoryGroup().getName());
                        intent.putExtra("REPO", expenseManagerRepo);
                        startActivity(intent);
                    }
                });
                tableRow.addView(button);
                iconInRow++;
            }
        }
    }

    private void getRepository() {
        if (expenseManagerRepo == null) {
            ExpenseManagerDBRepo expenseManagerDBRepo = new ExpenseManagerDBRepo(getApplicationContext());
            expenseManagerDBRepo.init();
            expenseManagerRepo = expenseManagerDBRepo;
        }
    }

}
