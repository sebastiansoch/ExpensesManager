package com.gmail.sebastiansoch.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends BaseActivity {
    private ImageButton expenseCategoriesBtn;
    private ImageButton expensesStatisticsBtn;
    private ImageButton manageExpensesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNavigationButtons();
    }

    private void setNavigationButtons() {
        expenseCategoriesBtn = findViewById(R.id.expenseCategoriesBtn);
        expenseCategoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpenseCategories.class);
                startActivity(intent);
            }
        });


        expensesStatisticsBtn = findViewById(R.id.expensesStatisticsBtn);
        expensesStatisticsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpensesStatistics.class);
                startActivity(intent);
            }
        });

        manageExpensesBtn = findViewById(R.id.manageExpensesBtn);
        manageExpensesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ManageExpenses.class);
                startActivity(intent);
            }
        });
    }


}
