package com.gmail.sebastiansoch.expensemanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerDBRepo;
import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

public class BaseActivity extends AppCompatActivity {
    ExpenseManagerRepo expenseManagerRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (expenseManagerRepo == null) {
            ExpenseManagerDBRepo expenseManagerDBRepo = new ExpenseManagerDBRepo(getApplicationContext());
            expenseManagerDBRepo.init();
            expenseManagerRepo = expenseManagerDBRepo;
        }
    }
}
