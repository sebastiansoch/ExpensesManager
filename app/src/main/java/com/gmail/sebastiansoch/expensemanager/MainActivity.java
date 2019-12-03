package com.gmail.sebastiansoch.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroupExpenses;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
private ImageButton expenseCategoriesBtn;
private ImageButton expensesStatisticsBtn;
private ImageButton manageExpensesBtn;
private AnyChartView anyChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anyChartView = findViewById(R.id.any_chart_view);
        prepareChart();
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

    private void prepareChart() {
        List<CategoryGroupExpenses> categoryGroupExpenses = expenseManagerRepo.getCurrentMonthExpensesForAllCategoriesGroup();
        List<DataEntry> data = new ArrayList<>();

        for (CategoryGroupExpenses expenses : categoryGroupExpenses) {
            data.add(new ValueDataEntry(expenses.getCategoryGroup().getName(), expenses.getExpenses()));
        }

        Pie pie = AnyChart.pie();
        pie.radius(100);
        pie.innerRadius(50);
        pie.data(data);

        anyChartView.setChart(pie);

    }
}
