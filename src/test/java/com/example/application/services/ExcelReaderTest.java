package com.example.application.services;

import com.example.application.data.entity.DataDao;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExcelReaderTest {

    private FileReader reader;

    final static String XLS_FILE = "./src/test/resources/test.xls";
    final static String ODS_FILE = "./src/test/resources/test.ods";

    @Test
    public void shouldReturnODSReader() {
        var reader = FileReader.getReader(ODS_FILE);
        assertTrue(reader instanceof ODSReader);
    }

    @Test
    public void readXLS() {
        List<DataDao> result = new ArrayList<>();
        reader = new XLSReader();
        try {
            result = reader.read(XLS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).getName().equals("Александръ"));
    }

    @Test
    public void readODS() {
        List<DataDao> result = new ArrayList<>();
        reader = new ODSReader();
        try {
            result = reader.read(ODS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).getName().equals("name"));
    }
}