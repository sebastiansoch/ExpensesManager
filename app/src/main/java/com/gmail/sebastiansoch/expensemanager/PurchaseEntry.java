package com.gmail.sebastiansoch.expensemanager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PurchaseEntry extends AppCompatActivity {

    private ExpenseManagerRepo repository;
    private List<String> purchaseProductsList = new ArrayList<>();

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
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_entry);

        purchaseCategory = getIntent().getParcelableExtra("CATEGORY_NAME");
        repository = getIntent().getParcelableExtra("REPO");

        purchaseDateTextView = findViewById(R.id.purchaseDateTV);
        purchaseDateButton = findViewById(R.id.purchaseDateBtn);
        enteredPurchasesLayout = findViewById(R.id.enteredPurchasesLayout);
        init();

        addPurchaseButton = findViewById(R.id.addPurchaseBtn);
        addPurchaseButton.setOnClickListener(addPurchaseButtonListener);
        purchasePriceEditText = findViewById(R.id.purchasePriceET);
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
            purchaseTV.setText(purchase);
            purchaseTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 4));
            enteredPurchasesLine.addView(purchaseTV);

            TextView dateTV = new TextView(this);
            dateTV.setText(date);
            dateTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            enteredPurchasesLine.addView(dateTV);

            TextView priceTV = new TextView(this);
            priceTV.setText(price);
            priceTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2));
            enteredPurchasesLine.addView(priceTV);

        } else {
            Toast.makeText(this, "Check if data are set properly", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}
