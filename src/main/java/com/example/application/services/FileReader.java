package com.example.application.services;

import com.example.application.utils.ReadPath;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;

public interface FileReader {
    default File readFile(String fileName) {
        return new File(fileName);
    }

    static FileReader getReader(String fileName) {
        String extension = ReadPath.getFileExtension(fileName);
        return switch (extension) {
            case "ods" -> new ODSReader();
            case "xls", "xlsx" -> new XLSReader();
            default -> throw new IllegalArgumentException();
        };
    }

    boolean read(String fileName) throws IOException, InvalidFormatException;
}
