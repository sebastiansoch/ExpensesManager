package com.gmail.sebastiansoch.expensemanager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
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
    private BarChart chart;
    private List<String> categoryGroupsList = new ArrayList<>();
    private Map<String, String> nameToCategory = new HashMap<>();
    private DatePickerDialog.OnDateSetListener onBeginDateSetListener;
    private DatePickerDialog.OnDateSetListener onEndDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_statistics);
        chart = findViewById(R.id.statisticsChart);

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
