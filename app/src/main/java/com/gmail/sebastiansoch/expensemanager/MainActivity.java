package com.gmail.sebastiansoch.expensemanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;

import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerFileRepo;
import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final int SETTINGS_REQ_CODE = 1;
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
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SETTINGS_REQ_CODE && resultCode == RESULT_OK) {
            ArrayList<CategoryTiles> catagoryTilesInfo = data.getParcelableExtra("CATEGORY_TAILS");
            int aa =0;
        }
    }

    private void getRepo() {
        ExpenseManagerFileRepo expenseManagerFileRepo = new ExpenseManagerFileRepo();
        expenseManagerFileRepo.init();
        expenseManagerRepo = expenseManagerFileRepo;
    }

}
