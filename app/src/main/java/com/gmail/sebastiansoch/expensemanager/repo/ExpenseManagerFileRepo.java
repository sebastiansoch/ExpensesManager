package com.gmail.sebastiansoch.expensemanager.repo;

import android.widget.Toast;

import com.gmail.sebastiansoch.expensemanager.CategoryTiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ExpenseManagerFileRepo implements ExpenseManagerRepo {

    private List<CategoryTiles> categoryTilesList;

    @Override
    public List<CategoryTiles> getCatagoryTilesInfo() {
        return categoryTilesList;
    }

    public void init() {
        prepareCategoryTilesInfo();
    }
    private void prepareCategoryTilesInfo() {
        try {
            File file = new File("/resources/settings.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String settingsLine;
            while ((settingsLine = bufferedReader.readLine()) != null) {
                if (!settingsLine.isEmpty()) {
                    String[] split = settingsLine.split(";");
                    categoryTilesList.add(new CategoryTiles(split[1], split[2], split[3]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
