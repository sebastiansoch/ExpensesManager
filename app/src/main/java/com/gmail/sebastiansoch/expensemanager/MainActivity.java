package com.gmail.sebastiansoch.expensemanager;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroupExpenses;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ImageButton expenseCategoriesBtn;
    private ImageButton expensesStatisticsBtn;
    private ImageButton manageExpensesBtn;

    private BarChart currentMonthExpensesChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentMonthExpensesChart = findViewById(R.id.currentMonthExpensesChart);
        prepareCurrentMonthExpensesChart();
        setNavigationButtons();
    }

    private void prepareCurrentMonthExpensesChart() {
        List<CategoryGroupExpenses> currentMonthExpensesForAllCategoriesGroup = expenseManagerRepo.getCurrentMonthExpensesForAllCategoriesGroup();
        List<BarEntry> entryList = new ArrayList<>();
        final List<String> categoryLabels = new ArrayList<>();

        for (int i = 0; i < currentMonthExpensesForAllCategoriesGroup.size(); i++) {
            if (!currentMonthExpensesForAllCategoriesGroup.get(i).getCategoryGroup().isHide()) {
                Double expenses = currentMonthExpensesForAllCategoriesGroup.get(i).getExpenses();
                entryList.add(new BarEntry(i, (float) expenses.doubleValue()));
                categoryLabels.add(currentMonthExpensesForAllCategoriesGroup.get(i).getCategoryGroup().getName());
            }
        }

        BarDataSet barDataSet = new BarDataSet(entryList, "");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setDrawValues(false);
        barDataSet.setForm(Legend.LegendForm.EMPTY);

        currentMonthExpensesChart.setData(new BarData(barDataSet));
        currentMonthExpensesChart.setDrawValueAboveBar(false);
        Description description = new Description();
        description.setText("Current expenses statistics");
        description.setTextAlign(Paint.Align.RIGHT);
        description.setYOffset(-16);
        description.setTextSize(16);
        description.setTextColor(ContextCompat.getColor(this, R.color.colorChartLabel));
        currentMonthExpensesChart.setDescription(description);

        XAxis xAxis = currentMonthExpensesChart.getXAxis();
        xAxis.disableAxisLineDashedLine();
        xAxis.disableGridDashedLine();
        xAxis.setLabelRotationAngle(90);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return getResources().getString(getResources().getIdentifier(categoryLabels.get((int) value), "string", getPackageName()));
            }
        });

        YAxis yLeftAxis = currentMonthExpensesChart.getAxisLeft();
        yLeftAxis.setAxisMinimum(0);


        YAxis yRightAxis = currentMonthExpensesChart.getAxisRight();
        yRightAxis.setAxisMinimum(0);
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
