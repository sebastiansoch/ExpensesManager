package com.gmail.sebastiansoch.expensemanager;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.gmail.sebastiansoch.expensemanager.data.CategoryGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpensesStatistics extends BaseActivity {

    private Spinner categoryGroupsSpinner;
    private TextView beginDateTextView;
    private TextView endDateTextView;
    private ImageButton beginDateBtn;
    private ImageButton endDateBtn;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private LineChart expensesStatisticChart;
    private List<String> categoryGroupsList = new ArrayList<>();
    private Map<String, String> nameToCategory = new HashMap<>();
    private DatePickerDialog.OnDateSetListener onBeginDateSetListener;
    private DatePickerDialog.OnDateSetListener onEndDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_statistics);
        expensesStatisticChart = findViewById(R.id.statisticsChart);

        beginDateTextView = findViewById(R.id.statBeginDateTV);
        endDateTextView = findViewById(R.id.statEndDateTV);
        beginDateBtn = findViewById(R.id.statBeginDateBtn);
        endDateBtn = findViewById(R.id.statEndDateBtn);
        categoryGroupsSpinner = findViewById(R.id.statCategorySpinner);
        init();

        prepareStatistics();
    }

    private void init() {
        setCategoryGroupsList();
        fillCategoryGroupsSpinner();
        setCurrentDateInDateTextViews();
        initDatePickers();
    }

    private void prepareStatistics() {

        List<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(0, 4f));
        entries1.add(new Entry(1, 8f));
        entries1.add(new Entry(2, 6f));
        entries1.add(new Entry(3, 12f));
        entries1.add(new Entry(4, 18f));
        entries1.add(new Entry(5, 9f));

        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(0, 2f));
        entries2.add(new Entry(1, 5f));
        entries2.add(new Entry(2, 12f));
        entries2.add(new Entry(3, 18f));
        entries2.add(new Entry(4, 1f));
        entries2.add(new Entry(5, 7f));


        LineDataSet dataSet1 = new LineDataSet(entries1, "# opis do wykres numer 1");
        dataSet1.setColor(Color.RED);
        LineDataSet dataSet2 = new LineDataSet(entries2, "# opis do wykres numer 2");
        dataSet2.setColor(Color.MAGENTA);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet1);
        dataSets.add(dataSet2);

        //Add data to expensesStatisticChart
        LineData lineData = new LineData(dataSets);
        expensesStatisticChart.setData(lineData);


        //X-axis label
        final List<String> xLabels = new ArrayList<>(Arrays.asList("January", "February", "March", "April", "May", "June"));
        XAxis xAxis = expensesStatisticChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return xLabels.get((int) value);
            }
        });

        Description description = new Description();
        description.setText("Expenses statistic chart");
        description.setTextAlign(Paint.Align.RIGHT);
        description.setYOffset(16);
        description.setTextSize(16);
        description.setTextColor(ContextCompat.getColor(this, R.color.colorChartLabel));
        expensesStatisticChart.setDescription(description);

    }


    private void setCategoryGroupsList() {
        categoryGroupsList.add("All Category Groups");
        nameToCategory.put("All Category Groups", "all_category_groups");

        List<CategoryGroup> categoryGroups = expenseManagerRepo.getAllCategoryGroups();
        if (categoryGroups != null && !categoryGroups.isEmpty()) {
            for (CategoryGroup categoryGroup : categoryGroups) {
                String categoryGroupName = getResources().getString(getResources().getIdentifier(categoryGroup.getName(), "string", getPackageName()));
                categoryGroupsList.add(categoryGroupName);
                nameToCategory.put(categoryGroupName, categoryGroup.getName());
            }
        }
    }

    private void fillCategoryGroupsSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryGroupsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categoryGroupsSpinner.setAdapter(adapter);
        categoryGroupsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                Toast.makeText(getApplicationContext(), "Produkt: " + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Produkt musi zostać wybrany", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCurrentDateInDateTextViews() {

        String date = dateFormat.format(new Date());
        if (date != null) {
            beginDateTextView.setText(date);
            endDateTextView.setText(date);
        }
    }

    private void initDatePickers() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        beginDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ExpensesStatistics.this, android.R.style.Theme_Material_Light_Dialog_MinWidth, onBeginDateSetListener, year, month, day);
                datePickerDialog.show();
            }
        });

        onBeginDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                try {
                    Date parsedDate = dateFormat.parse(year + "-" + (month + 1) + "-" + day);

                    if (parsedDate.after(new Date())) {
                        Toast.makeText(ExpensesStatistics.this, "Dates are not seated correctly", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    beginDateTextView.setText(dateFormat.format(parsedDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };

        endDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ExpensesStatistics.this, android.R.style.Theme_Material_Light_Dialog_MinWidth, onEndDateSetListener, year, month, day);
                datePickerDialog.show();
            }
        });

        onEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                try {
                    Date parsedDate = dateFormat.parse(year + "-" + (month + 1) + "-" + day);
                    Date beginRangeDate = dateFormat.parse(beginDateTextView.getText().toString());

                    if (parsedDate.before(beginRangeDate)) {
                        Toast.makeText(ExpensesStatistics.this, "Dates are not seated correctly", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    endDateTextView.setText(dateFormat.format(parsedDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
    }


}
