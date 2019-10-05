package com.gmail.sebastiansoch.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sebastiansoch.expensemanager.data.PurchaseGroupTiles;
import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerDBRepo;
import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int SETTINGS_REQ_CODE = 1;
    private ArrayList<PurchaseGroupTiles> purchaseGroupTilesInfo = new ArrayList<>();

    private ExpenseManagerRepo expenseManagerRepo;

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
            purchaseGroupTilesInfo = data.getParcelableArrayListExtra("PURCHASE_GROUP_TAILS");
            if (purchaseGroupTilesInfo != null && !purchaseGroupTilesInfo.isEmpty()) {
                generatePurchaseGroupTiles();
            }
        }
    }

    private void generatePurchaseGroupTiles() {
        TableLayout tableLayout = findViewById(R.id.purchaseGroupTableLayout);

        TableRow tableRow = null;
        int iconInRow = 0;
        for (final PurchaseGroupTiles purchaseGroupTiles : purchaseGroupTilesInfo) {
            if (iconInRow % 3 == 0) {
                tableRow = new TableRow(this);
                tableLayout.addView(tableRow);
            }
            ImageButton button = new ImageButton(this);
            button.setTag(purchaseGroupTiles.getTilesTag());
            button.setImageResource(getResources().getIdentifier(purchaseGroupTiles.getTilesIconName(), "drawable", getPackageName()));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), PurchaseEntry.class);
                    intent.putExtra("PURCHASE_GROUP_NAME", purchaseGroupTiles.getPurchaseGroup());
                    intent.putExtra("REPO", expenseManagerRepo);
                    startActivity(intent);
                }
            });
            tableRow.addView(button);
            iconInRow++;
        }
    }

    private void getRepository() {
        if (expenseManagerRepo == null) {
            ExpenseManagerDBRepo expenseManagerDBRepo = new ExpenseManagerDBRepo(getApplicationContext());
            expenseManagerDBRepo.initDAO();
            expenseManagerRepo = expenseManagerDBRepo;
        }
    }

}
