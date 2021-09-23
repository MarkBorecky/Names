package com.example.application.services;

import com.example.application.data.entity.DataConverter;
import com.example.application.data.entity.DataDao;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XLSReader implements FileReader {
    @Override
    public List<DataDao> read(String fileName) throws IOException {
        var dataFormatter = new DataFormatter();
        var file = readFile(fileName);
        var values = getValues(file, dataFormatter);
        return DataConverter.getDataDaoList(values);
    }

    private Object[][] getValues(File file, DataFormatter dataFormatter) throws IOException {
        Object[][] values;
        try (var workbook = WorkbookFactory.create(file)) {
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
        } catch (InvalidFormatException e) {
            throw new IllegalArgumentException();
        }
        return values;
    }
}