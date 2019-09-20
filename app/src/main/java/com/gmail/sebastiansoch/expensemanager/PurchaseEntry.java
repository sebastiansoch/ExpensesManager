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

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PurchaseEntry extends AppCompatActivity {

    private ExpenseManagerRepo repository;
    private List<String> purchaseProductsList = new ArrayList<>();
    char decimalSeparator;

    private TextView purchaseDateTextView;
    private ImageButton purchaseDateButton;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private PurchaseCategory purchaseCategory;
    private Spinner purchaseSpinner;

    private LinearLayout enteredPurchasesLayout;
    private EditText purchasePriceEditText;
    private ImageButton addPurchaseButton;
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

        purchaseCategory = getIntent().getParcelableExtra("CATEGORY_NAME");
        repository = getIntent().getParcelableExtra("REPO");

        purchaseDateTextView = findViewById(R.id.purchaseDateTV);
        purchaseDateButton = findViewById(R.id.purchaseDateBtn);
        enteredPurchasesLayout = findViewById(R.id.enteredPurchasesLayout);
        init();

        addPurchaseButton = findViewById(R.id.addPurchaseBtn);
        addPurchaseButton.setOnClickListener(addPurchaseButtonListener);
        purchasePriceEditText = findViewById(R.id.purchasePriceET);

        purchasePriceEditText.addTextChangedListener(purchasePriceTextWatcher);
    }

    private void init() {
        setPurchaseProductsList(purchaseCategory);
        fillPurchaseProductSpinner();
        setCurrentDate();
        initDatePicker();
    }

    private void initDatePicker() {
        purchaseDateButton.setOnClickListener(new View.OnClickListener() {
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

    private void fillPurchaseProductSpinner() {
        purchaseSpinner = findViewById(R.id.prurchaseSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, purchaseProductsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        purchaseSpinner.setAdapter(adapter);
        purchaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


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

    private void setPurchaseProductsList(PurchaseCategory categoryName) {
        List<PurchaseProduct> productsForCategory = repository.getProductsForCategory(categoryName);
        if (productsForCategory != null && !productsForCategory.isEmpty()) {
            for (PurchaseProduct product : productsForCategory) {
                purchaseProductsList.add(product.getName());
            }
        }
    }

    private void addEnteredPurchaseToList() {
        String purchase = purchaseSpinner.getSelectedItem().toString();
        String date = purchaseDateTextView.getText().toString();
        String price = purchasePriceEditText.getText().toString();

        if (!purchase.isEmpty() && !date.isEmpty() && !price.isEmpty()) {
            LinearLayout enteredPurchasesLine = new LinearLayout(PurchaseEntry.this);
            enteredPurchasesLine.setOrientation(LinearLayout.HORIZONTAL);
            enteredPurchasesLayout.addView(enteredPurchasesLine);

            TextView purchaseTV = new TextView(this);
            LinearLayout.LayoutParams purchaseLayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            purchaseLayoutParams.setMargins(10, 2, 0, 2);
            purchaseTV.setLayoutParams(purchaseLayoutParams);
            purchaseTV.setGravity(Gravity.START);
            purchaseTV.setText(purchase);
            enteredPurchasesLine.addView(purchaseTV);

            TextView dateTV = new TextView(this);
            LinearLayout.LayoutParams dateLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            dateLayoutParams.setMargins(10, 2, 10, 2);
            dateTV.setLayoutParams(dateLayoutParams);
            dateTV.setGravity(Gravity.END);
            dateTV.setText(date);
            enteredPurchasesLine.addView(dateTV);

            TextView priceTV = new TextView(this);
            LinearLayout.LayoutParams priceLayoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 4);
            priceLayoutParams.setMargins(0, 2, 10, 2);
            priceTV.setLayoutParams(priceLayoutParams);
            priceTV.setGravity(Gravity.END);
            priceTV.setText(formatPrice(price));
            enteredPurchasesLine.addView(priceTV);

        } else {
            Toast.makeText(this, "Check if data are set properly", Toast.LENGTH_SHORT).show();
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
