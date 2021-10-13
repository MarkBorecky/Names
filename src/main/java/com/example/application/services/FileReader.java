package com.example.application.services;

import com.example.application.data.entity.Person;
import com.example.application.utils.ReadPath;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileReader {

    static FileReader getReader(String fileName) {
        String extension = ReadPath.getFileExtension(fileName);
        return switch (extension) {
            case "ods" -> new ODSReader();
            case "xls" -> new XLSReader();
            case "xlsx" -> new XLSXReader();
            default -> throw new IllegalArgumentException();
        };
    }

    List<Person> read(InputStream inputStream) throws IOException, InvalidFormatException;
}
