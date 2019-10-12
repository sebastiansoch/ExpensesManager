package com.gmail.sebastiansoch.expensemanager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.sebastiansoch.expensemanager.data.Category;

import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PurchaseEntry extends BaseActivity {

    char decimalSeparator;
    private List<String> purchaseCategoryList = new ArrayList<>();
    private TextView purchaseDateTextView;
    private ImageButton purchaseDateBtn;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private String categoryGroupName;
    private Spinner purchaseCategorySpinner;

    private LinearLayout enteredPurchasesLayout;
    private EditText purchasePriceEditText;
    private ImageButton addPurchaseBtn;
    private View.OnClickListener addPurchaseButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addEnteredPurchaseToList();
            cleanPriceTextView();
        }
    };

    private TextWatcher purchasePriceTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String sPrice = purchasePriceEditText.getText().toString();

            if (sPrice != null && !sPrice.isEmpty()) {
                purchasePriceEditText.removeTextChangedListener(this);
                String parsedPrice = parseEditingPrice(sPrice);
                editable.replace(0, editable.length(), parsedPrice);
                purchasePriceEditText.addTextChangedListener(this);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_entry);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        decimalSeparator = symbols.getDecimalSeparator();

        categoryGroupName = getIntent().getStringExtra("CATEGORY_GROUP_NAME");

        purchaseDateTextView = findViewById(R.id.purchaseDateTV);
        purchaseDateBtn = findViewById(R.id.purchaseDateBtn);
        enteredPurchasesLayout = findViewById(R.id.enteredPurchasesLayout);
        init();

        addPurchaseBtn = findViewById(R.id.addPurchaseBtn);
        addPurchaseBtn.setOnClickListener(addPurchaseButtonListener);

        purchasePriceEditText = findViewById(R.id.purchasePriceET);
        purchasePriceEditText.addTextChangedListener(purchasePriceTextWatcher);
    }

    private void init() {
        setPurchaseCategoryList(categoryGroupName);
        fillPurchaseCategorySpinner();
        setCurrentDate();
        initDatePicker();
    }

    private void initDatePicker() {
        purchaseDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(PurchaseEntry.this, android.R.style.Theme_Material_Light_Dialog_MinWidth, onDateSetListener, year, month, day);
//                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsedDate = dateFormat.parse(year + "-" + (month + 1) + "-" + day);
                    purchaseDateTextView.setText(dateFormat.format(parsedDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void setCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        if (date != null) {
            purchaseDateTextView.setText(date);
        }
    }

    private void fillPurchaseCategorySpinner() {
        purchaseCategorySpinner = findViewById(R.id.prurchaseCategorySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, purchaseCategoryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        purchaseCategorySpinner.setAdapter(adapter);
        purchaseCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

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

    private void setPurchaseCategoryList(String categoryGroup) {
        List<Category> purchaseCategoriesForGroup = expenseManagerRepo.getAllCategoriesForGroup(categoryGroup);
        if (purchaseCategoriesForGroup != null && !purchaseCategoriesForGroup.isEmpty()) {
            for (Category category : purchaseCategoriesForGroup) {
                purchaseCategoryList.add(category.getName());
            }
        }
    }

    private void addEnteredPurchaseToList() {
        String purchaseCategory = purchaseCategorySpinner.getSelectedItem().toString();
        String date = purchaseDateTextView.getText().toString();
        String price = purchasePriceEditText.getText().toString();

        if (!purchaseCategory.isEmpty() && !date.isEmpty() && !price.isEmpty()) {
            LinearLayout enteredPurchasesRow = new LinearLayout(PurchaseEntry.this);
            enteredPurchasesRow.setOrientation(LinearLayout.HORIZONTAL);
            enteredPurchasesLayout.addView(enteredPurchasesRow);

            TextView purchaseCategoryTV = new TextView(this);
            LinearLayout.LayoutParams purchaseLayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            purchaseLayoutParams.setMargins(10, 2, 0, 2);
            purchaseCategoryTV.setLayoutParams(purchaseLayoutParams);
            purchaseCategoryTV.setGravity(Gravity.START);
            purchaseCategoryTV.setText(purchaseCategory);
            enteredPurchasesRow.addView(purchaseCategoryTV);

            TextView dateTV = new TextView(this);
            LinearLayout.LayoutParams dateLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            dateLayoutParams.setMargins(10, 2, 10, 2);
            dateTV.setLayoutParams(dateLayoutParams);
            dateTV.setGravity(Gravity.END);
            dateTV.setText(date);
            enteredPurchasesRow.addView(dateTV);

            TextView priceTV = new TextView(this);
            LinearLayout.LayoutParams priceLayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 4);
            priceLayoutParams.setMargins(0, 2, 10, 2);
            priceTV.setLayoutParams(priceLayoutParams);
            priceTV.setGravity(Gravity.END);
            priceTV.setText(formatPrice(price));
            enteredPurchasesRow.addView(priceTV);

        } else {
            Toast.makeText(this, "Check if all data are set properly", Toast.LENGTH_SHORT).show();
            return;
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

    private void cleanPriceTextView() {
        purchasePriceEditText.setText("");
    }

    private String parseEditingPrice(String sPrice) {
        int separatorIndex = sPrice.indexOf(decimalSeparator);

        if (separatorIndex == 0) {
            return (0 + sPrice);
        } else if (separatorIndex > 0) {
            if (sPrice.contains("" + decimalSeparator)) {
                if (sPrice.substring(separatorIndex, sPrice.length() - 1).length() > 2) {
                    sPrice = sPrice.substring(0, separatorIndex + 3);
                    return sPrice;
                }
            }
        }
        return sPrice;
    }
}
