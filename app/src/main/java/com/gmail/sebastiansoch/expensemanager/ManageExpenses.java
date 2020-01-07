package com.gmail.sebastiansoch.expensemanager;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.sebastiansoch.expensemanager.data.CategoryGroup;
import com.gmail.sebastiansoch.expensemanager.data.Purchase;

import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageExpenses extends BaseActivity {

    private TextView manageBeginDateTextView;
    private ImageButton manageBeginDateBtn;
    private TextView manageEndDateTextView;
    private ImageButton manageEndDateBtn;
    private Spinner categoryGroupsSpinner;
    private LinearLayout filteredExpensesLayout;
    private ImageButton showFilteredExpenses;
    private View.OnClickListener showFilteredExpensesListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            fillFilteredExpensesList();
        }
    };
    private ImageButton removeExpensesFromDbBtn;
    private View.OnClickListener removeExpensesFromDbListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            removeExpensesFromDB();
        }
    };

    private DatePickerDialog.OnDateSetListener onBeginDateSetListener;
    private DatePickerDialog.OnDateSetListener onEndDateSetListener;

    char decimalSeparator;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private List<String> categoryGroupsList = new ArrayList<>();
    private Map<String, String> nameToCategory = new HashMap<>();
    private List<Integer> purchaseToRemoveFromDB = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_expenses);

        manageBeginDateTextView = findViewById(R.id.manageBeginDateTV);
        manageBeginDateBtn = findViewById(R.id.manageBeginDateBtn);
        manageEndDateTextView = findViewById(R.id.manageEndDateTV);
        manageEndDateBtn = findViewById(R.id.manageEndDateBtn);
        categoryGroupsSpinner = findViewById(R.id.manageCategorySpinner);
        filteredExpensesLayout = findViewById(R.id.managesEnteredLayout);
        showFilteredExpenses = findViewById(R.id.manageShowFilteredExpensesBtn);
        showFilteredExpenses.setOnClickListener(showFilteredExpensesListener);
        removeExpensesFromDbBtn = findViewById(R.id.manageRemoveExpensesBtn);
        removeExpensesFromDbBtn.setOnClickListener(removeExpensesFromDbListener);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        purchaseToRemoveFromDB.clear();
    }

    private void init() {

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        decimalSeparator = symbols.getDecimalSeparator();

        setCategoryGroupsList();
        fillCategoryGroupsSpinner();
        setCurrentDateInDateTextViews();
        initDatePickers();
    }

    private void setCategoryGroupsList() {
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

    private void fillFilteredExpensesList() {
        filteredExpensesLayout.removeAllViewsInLayout();
        String categoryGroup = categoryGroupsSpinner.getSelectedItem().toString();
        final String categoryGroupName = nameToCategory.get(categoryGroup);
        final String beginDate = manageBeginDateTextView.getText().toString();
        final String endDate = manageEndDateTextView.getText().toString();

        List<Purchase> filteredExpenses = expenseManagerRepo.getFilteredExpenses(categoryGroupName, beginDate, endDate);

        for (final Purchase purchase : filteredExpenses) {
            LayoutInflater inflater = getLayoutInflater();
            final View enteredPurchaseView = inflater.inflate(R.layout.view_entered_purchases, filteredExpensesLayout, false);

            String categoryName = getResources().getString(getResources().getIdentifier(purchase.getCategoryName(), "string", getPackageName()));
            TextView purchaseTV = enteredPurchaseView.findViewById(R.id.purchaseEPV);
            purchaseTV.setText(categoryName);
            purchaseTV.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
            TextView purchaseDateTV = enteredPurchaseView.findViewById(R.id.purchaseDateEPV);
            purchaseDateTV.setText(purchase.getPurchaseDate());
            TextView priceTV = enteredPurchaseView.findViewById(R.id.priceEPV);
            priceTV.setText(formatPrice(purchase.getPrice()));

            ImageButton removeBtn = enteredPurchaseView.findViewById(R.id.removePurchaseEPVBtn);
            removeBtn.setVisibility(View.VISIBLE);
            removeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    filteredExpensesLayout.removeView(enteredPurchaseView);
                    purchaseToRemoveFromDB.add(purchase.getPurchaseId());
                }
            });

            filteredExpensesLayout.addView(enteredPurchaseView);
        }
    }

    private String formatPrice(String sPrice) {
        int separatorIndex = sPrice.indexOf(decimalSeparator);
        if (separatorIndex == -1) {
            return sPrice.concat(".00");
        }

        if (sPrice.substring(separatorIndex, sPrice.length() - 1).length() == 1) {
            return sPrice.concat("0");
        }

        return sPrice;
    }

    private void removeExpensesFromDB() {
        if (purchaseToRemoveFromDB != null && !purchaseToRemoveFromDB.isEmpty()) {
            if (expenseManagerRepo.removeChosenExpensesFromDB(purchaseToRemoveFromDB)) {
                Toast.makeText(this, "Chosen expenses removed from DB", Toast.LENGTH_SHORT);
            } else {
                Toast.makeText(this, "Something went wrong. Chosen expenses were not removed from DB", Toast.LENGTH_SHORT);
            }
        }

    }


}
