package com.gmail.sebastiansoch.expensemanager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ManageExpenses extends BaseActivity {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private DatePickerDialog.OnDateSetListener onBeginDateSetListener;
    private DatePickerDialog.OnDateSetListener onEndDateSetListener;

    private TextView manageBeginDateTextView;
    private ImageButton manageBeginDateBtn;
    private TextView manageEndDateTextView;
    private ImageButton manageEndDateBtn;
    private boolean isDateRangeCorrect = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_expenses);

        manageBeginDateTextView = findViewById(R.id.manageBeginDateTV);
        manageBeginDateBtn = findViewById(R.id.manageBeginDateBtn);
        manageEndDateTextView = findViewById(R.id.manageEndDateTV);
        manageEndDateBtn = findViewById(R.id.manageEndDateBtn);
        init();

    }

    private void init() {
        setCurrentDateInDateTextViews();
        initDatePickers();
    }

    private void setCurrentDateInDateTextViews() {

        String date = dateFormat.format(new Date());
        if (date != null) {
            manageBeginDateTextView.setText(date);
            manageEndDateTextView.setText(date);
        }
    }

    private void initDatePickers() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        manageBeginDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ManageExpenses.this, android.R.style.Theme_Material_Light_Dialog_MinWidth, onBeginDateSetListener, year, month, day);
                datePickerDialog.show();
            }
        });

        onBeginDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                try {
                    Date parsedDate = dateFormat.parse(year + "-" + (month + 1) + "-" + day);

                    if (parsedDate.after(new Date())) {
                        Toast.makeText(ManageExpenses.this, "Dates are not seated correctly", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    manageBeginDateTextView.setText(dateFormat.format(parsedDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };

        manageEndDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ManageExpenses.this, android.R.style.Theme_Material_Light_Dialog_MinWidth, onEndDateSetListener, year, month, day);
                datePickerDialog.show();
            }
        });

        onEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                try {
                    Date parsedDate = dateFormat.parse(year + "-" + (month + 1) + "-" + day);
                    Date beginRangeDate = dateFormat.parse(manageBeginDateTextView.getText().toString());

                    if (parsedDate.before(beginRangeDate)) {
                        Toast.makeText(ManageExpenses.this, "Dates are not seated correctly", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    manageEndDateTextView.setText(dateFormat.format(parsedDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };

    }
}
