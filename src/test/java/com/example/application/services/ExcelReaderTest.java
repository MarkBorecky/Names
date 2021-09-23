package com.example.application.services;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ExcelReaderTest {

    private FileReader reader;

    static String XLS_FILE = "./src/test/resources/test.xls";
    static String ODS_FILE = "./src/test/resources/test.ods";

    @Test
    public void shouldReturnODSReader() {
        var reader = FileReader.getReader(ODS_FILE);
        assertTrue(reader instanceof ODSReader);
    }

    @Test
    public void readXLS() {
        boolean result = false;
        reader = new XLSReader();
        try {
            result = reader.read(XLS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        assertTrue(result);
    }

    @Test
    public void readODS() {
        boolean result = false;
        reader = new ODSReader();
        try {
            result = reader.read(ODS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        assertTrue(result);
    }


}