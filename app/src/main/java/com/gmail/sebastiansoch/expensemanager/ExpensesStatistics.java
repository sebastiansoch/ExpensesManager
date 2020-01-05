package com.gmail.sebastiansoch.expensemanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpensesStatistics extends AppCompatActivity {

    private BarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_statistics);
        chart = findViewById(R.id.statisticsChart);
        prepareStatistics();
    }

    private void prepareStatistics() {
//        List<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(0, new float[]{4f, 2f}));
//        entries.add(new BarEntry(1, new float[]{8f, 2f}));
//        entries.add(new BarEntry(2, new float[]{6f, 10f}));
//        entries.add(new BarEntry(3, new float[]{12f, 1f}));
//        entries.add(new BarEntry(4, new float[]{18f, 2f}));
//        entries.add(new BarEntry(5, new float[]{9f, 6f}));

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 4f, "JA"));
        entries.add(new BarEntry(1, 8f, "FE"));
        entries.add(new BarEntry(2, 6f, "MA"));
        entries.add(new BarEntry(3, 12f, "AP"));
        entries.add(new BarEntry(4, 18f, "MY"));
        entries.add(new BarEntry(5, 9f, "JU"));

        BarDataSet dataSet = new BarDataSet(entries, "# of Calls");
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setValueTextSize(20);

        //Add data to chart
        BarData barData = new BarData(dataSet);
        chart.setData(barData);

        //X-axis label
        final List<String> xLabels = new ArrayList<>(Arrays.asList("January", "February", "March", "April", "May", "June"));
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return xLabels.get((int) value);
            }
        });

        Description description = new Description();
        description.setText("# of times Alice called Bob");
        chart.setDescription(description);


    }

}
