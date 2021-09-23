package com.example.application.services;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

import com.github.miachm.sods.Range;
import com.github.miachm.sods.Sheet;
import com.github.miachm.sods.SpreadSheet;

import java.util.List;


public class ODSReader implements FileReader {
    @Override
    public boolean read(String fileName) throws IOException {

        SpreadSheet spread = new SpreadSheet(readFile(fileName));
        System.out.println("Number of sheets: " + spread.getNumSheets());

        List<Sheet> sheets = spread.getSheets();

        for (Sheet sheet : sheets) {
            System.out.println("In sheet " + sheet.getName());

            Range range = sheet.getDataRange();
            System.out.println(range.toString());
        }

        return true;
    }
}
