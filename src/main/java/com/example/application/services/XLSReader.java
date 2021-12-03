package com.example.application.services;

import com.example.application.data.entity.DataConverter;
import com.example.application.data.entity.Person;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class XLSReader implements FileReader {
    @Override
    public List<Person> read(InputStream inputStream) throws IOException {
        var dataFormatter = new DataFormatter();
        var values = getValues(inputStream, dataFormatter);
        return DataConverter.getDataDaoList(values);
    }

    private Object[][] getValues(InputStream inputStream, DataFormatter dataFormatter) {
        Object[][] values;
        try (var workbook = WorkbookFactory.create(inputStream)) {
            var sheet = workbook.getSheetAt(0);
            var rowNumber = sheet.getPhysicalNumberOfRows();
            var columnNumber = sheet.getRow(0).getPhysicalNumberOfCells();
            values = new Object[rowNumber][columnNumber];

            int irow = 0;
            for (Row row : sheet) {
                int icol = 0;
                for (Cell cell : row) {
                    values[irow][icol] = dataFormatter.formatCellValue(cell);
                    icol++;
                }
                irow++;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        return values;
    }
}