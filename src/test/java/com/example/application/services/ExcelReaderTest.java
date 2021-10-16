package com.example.application.services;

import com.example.application.data.entity.Person;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExcelReaderTest {

    private FileReader reader;

    final static String XLS_FILE = "./src/test/resources/test.xls";
    final static String XLSX_FILE = "./src/test/resources/test.xlsx";
    final static String ODS_FILE = "./src/test/resources/test.ods";
    final static String ODS_BIG_FILE = "./src/test/resources/Baza_dr.ods";

    @Test
    public void shouldReturnODSReader() {
        var reader = FileReader.getReader(ODS_FILE);
        assertTrue(reader instanceof ODSReader);
    }

  /*  @Test
    public void readXLSX() {
        List<Person> result = new ArrayList<>();
        reader = new XLSXReader();
        try {
            var inputStream = new FileInputStream(XLSX_FILE);
            result = reader.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        assertEquals(1, result.size());
        assertEquals("Doe", result.get(0).getSurname());
        assertEquals(StringUtils.EMPTY, result.get(0).getName());
    }*/

    @Test
    public void readXLS() {
        List<Person> result = new ArrayList<>();
        reader = new XLSReader();
        try {
            var inputStream = new FileInputStream(XLS_FILE);
            result = reader.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        assertEquals(1, result.size());
        assertEquals("Александръ", result.get(0).getMainName());
        assertEquals("Литовская Духовная Семинария", result.get(0).getSchool());
    }

    @Test
    public void readODS() {
        List<Person> result = new ArrayList<>();
        reader = new ODSReader();
        try {
            var inputStream = new FileInputStream(ODS_FILE);
            result = reader.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).getMainName().equals("name"));
    }

    @Test
    public void readODSBigFile() {
        List<Person> result = new ArrayList<>();
        reader = new ODSReader();
        try {
            var inputStream = new FileInputStream(ODS_BIG_FILE);
            result = reader.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        assertEquals(5275, result.size());
        assertEquals("Василій", result.get(599).getMainName());
    }
}