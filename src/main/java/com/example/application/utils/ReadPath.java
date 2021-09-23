package com.example.application.utils;

public class ReadPath {
    public static String getFileExtension(String filePath) {
        String fileName = getFileName(filePath);
        if (fileName.contains(".")) {
            return fileName.split("[.]")[1];
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static String getFileName(String filePath) {
        String result;
        if (filePath.contains("/")) {
            String[] split = filePath.split("/");
            result = split[split.length - 1];
        } else {
            result =  filePath;
        }
        return result;
    }
}
