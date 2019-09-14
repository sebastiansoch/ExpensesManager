package com.gmail.sebastiansoch.expensemanager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    private LinearLayout enteredPurchasesLayout;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_entry);

        PurchaseCategory purchaseCategory = getIntent().getParcelableExtra("CATEGORY_NAME");
        repository = getIntent().getParcelableExtra("REPO");

        purchaseDateTextView = findViewById(R.id.purchaseDateTV);
        purchaseDateButton = findViewById(R.id.purchaseDateBtn);
        enteredPurchasesLayout = findViewById(R.id.enteredPurchasesLayout);

        setPurchaseProductsList(purchaseCategory);
        fillPurchaseProductSpinner();
        setCurrentDate();
        initDatePicker();
        fillEnteredPurchasesLayout();

        Toast.makeText(this, "Categoria: " + purchaseCategory.getName(), Toast.LENGTH_SHORT).show();
    }

    private void fillEnteredPurchasesLayout() {
        for (int i = 0; i < 10; i++) {
            TextView textView = new TextView(this);
            textView.setText("DUPA " + (i + 1));
            enteredPurchasesLayout.addView(textView);
        }
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
        Spinner spinner = findViewById(R.id.productsSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, purchaseProductsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


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
}
