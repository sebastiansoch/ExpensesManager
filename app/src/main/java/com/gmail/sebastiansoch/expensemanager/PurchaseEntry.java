package com.gmail.sebastiansoch.expensemanager;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.sebastiansoch.expensemanager.repo.ExpenseManagerRepo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PurchaseEntry extends AppCompatActivity {

    private ExpenseManagerRepo repository;
    private List<String> purchaseProductsList = new ArrayList<>();

    private TextView purchaseDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_entry);

        PurchaseCategory purchaseCategory = getIntent().getParcelableExtra("CATEGORY_NAME");
        repository = getIntent().getParcelableExtra("REPO");

        purchaseDate = findViewById(R.id.purchaseDateTV);
        setCurrentDate();
        initDatePicker();

        setPurchaseProductsList(purchaseCategory);
        fillPurchaseProductSpinner();
        Toast.makeText(this, "Categoria: " + purchaseCategory.getName(), Toast.LENGTH_SHORT).show();
    }


    private void initDatePicker() {
        purchaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
//TODO - nie dziala datepicker
                DatePickerDialog datePickerDialog = new DatePickerDialog(getApplicationContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Log.d("SSSSS: ", "onDataSet " + i + "/" + i1 + "/" + i2);
            }
        };
    }

    private void setCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(new Date());
        if (date != null) {
            purchaseDate.setText(date);
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
        purchaseProductsList.add("Choose product");
        List<PurchaseProduct> productsForCategory = repository.getProductsForCategory(categoryName);
        if (productsForCategory != null && !productsForCategory.isEmpty()) {
            for (PurchaseProduct product : productsForCategory) {
                purchaseProductsList.add(product.getName());
            }
        }
    }
}
