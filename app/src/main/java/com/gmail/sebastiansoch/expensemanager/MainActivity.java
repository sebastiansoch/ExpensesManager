package com.gmail.sebastiansoch.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.gmail.sebastiansoch.expensemanager.data.CategoryGroupTile;

import java.util.List;

public class MainActivity extends BaseActivity {

    private TableLayout tilesTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                button.setTag(categoryGroupTile.getTilesTag());
                button.setImageResource(getResources().getIdentifier(categoryGroupTile.getTilesIconPath(), "drawable", getPackageName()));
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
