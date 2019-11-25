package com.gmail.sebastiansoch.expensemanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.gmail.sebastiansoch.expensemanager.data.CategoryGroupTile;

import java.util.List;

public class ExpenseCategories extends BaseActivity {

    private TableLayout tilesTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_categories);

        tilesTableLayout = findViewById(R.id.purchaseGroupTableLayout);

        generateCategoryGroupTiles();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        cleanCategoryGroupTiles();
        generateCategoryGroupTiles();
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, CategorySettings.class);
        startActivity(intent);
    }

    private void generateCategoryGroupTiles() {
        TableRow tableRow = null;
        int iconInRow = 0;
        List<CategoryGroupTile> categoryGroupTiles = expenseManagerRepo.getCategoryGroupTiles();
        if (categoryGroupTiles != null) {
            for (final CategoryGroupTile categoryGroupTile : categoryGroupTiles) {
                if (iconInRow % 3 == 0) {
                    tableRow = new TableRow(this);
                    tilesTableLayout.addView(tableRow);
                }
                ImageButton button = new ImageButton(this);
                button.setPadding(32, 32, 32, 32);
                button.setTag(categoryGroupTile.getTilesTag());
                button.setImageResource(getResources().getIdentifier(categoryGroupTile.getTilesIconPath(), "drawable", getPackageName()));
                button.setBackgroundColor(Color.TRANSPARENT);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), PurchaseEntry.class);
                        intent.putExtra("CATEGORY_GROUP_NAME", categoryGroupTile.getCategoryGroupName());
                        startActivity(intent);
                    }
                });
                tableRow.addView(button);
                iconInRow++;
            }
        }
    }

    private void cleanCategoryGroupTiles() {
        tilesTableLayout.removeAllViews();
    }
}
