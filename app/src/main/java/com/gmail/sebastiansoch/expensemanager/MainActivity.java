package com.gmail.sebastiansoch.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton expenseCategoriesBtn = findViewById(R.id.expenseCategoriesBtn);
        expenseCategoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpenseCategories.class);
                startActivity(intent);
            }
        });


        ImageButton expensesStatisticsBtn = findViewById(R.id.expensesStatisticsBtn);
        expensesStatisticsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpensesStatistics.class);
                startActivity(intent);
            }
        });
    }
}
